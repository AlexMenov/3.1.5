package ru.kata.spring.boot_security.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.slf4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

@Entity
@Table(name = "users", catalog = "users")
public class User implements UserDetails, Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(User.class);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Size(max = 30, min = 3)
    @NotNull
    private String username;

    @Column(name = "last_name")
    @Size(max = 58)
    private String lastName;

    @Column
    @Min(0)
    private Integer age;

    @Column
    @Email
    @NotNull
    private String email;

    @Column
    @Min(8)
    @NotNull
    private String password;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns =
            @JoinColumn(name = "users_id"),
            inverseJoinColumns =
            @JoinColumn(name = "role_id"))
    @JsonBackReference
    private Collection<Role> roles;

    public User(Long id, @Size(max = 30, min = 3) @NotNull String username, @Size(max = 58) String lastName, @Min(0) Integer age, @Email @NotNull String email, @Min(8) @NotNull String password, @NotNull Collection<Role> roles) {
        this.id = id;
        this.username = username;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public User() {
    }

    public Collection<String> getUserRoles() {
        return this
                .getRoles()
                .stream()
                .sorted(Comparator.comparing(Role::getRole))
                .map(role -> role.getRole().toLowerCase())
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this
                .getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.toString()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return this.id;
    }

    public @Size(max = 58) String getLastName() {
        return this.lastName;
    }

    public @Min(0) Integer getAge() {
        return this.age;
    }

    public @Email @NotNull String getEmail() {
        return this.email;
    }

    public @NotNull Collection<Role> getRoles() {
        return this.roles;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(@Size(max = 30, min = 3) @NotNull String username) {
        this.username = username;
    }

    public void setLastName(@Size(max = 58) String lastName) {
        this.lastName = lastName;
    }

    public void setAge(@Min(0) Integer age) {
        this.age = age;
    }

    public void setEmail(@Email @NotNull String email) {
        this.email = email;
    }

    public void setPassword(@Min(8) @NotNull String password) {
        this.password = password;
    }

    public void setRoles(@NotNull Collection<Role> roles) {
        this.roles = roles;
    }
}
