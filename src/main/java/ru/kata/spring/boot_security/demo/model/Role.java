package ru.kata.spring.boot_security.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Getter
@Setter
@Slf4j
@NoArgsConstructor
@Table(name = "roles", catalog = "users")
public class Role implements GrantedAuthority {
    private static final long serialVersionUID = 2L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NotNull
    private String role;

    @Column
    @ManyToMany(mappedBy = "roles")
    @JsonManagedReference
    private Collection<User> users;

    @Override
    public String getAuthority() {
        return getRole();
    }
}
