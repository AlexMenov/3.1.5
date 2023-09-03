export class User {
    static toUser(user) {
        return `
                <td class="userid">${user.id}</td>
                <td class="username">${user.username}</td>
                <td class="userlastname">${user.lastName}</td>
                <td class="userage">${user.age}</td>
                <td class="useremail">${user.email}</td>
                <td>
                    ${user.userRoles?.map(role => `<span style="display: block">${role}</span>`)}
                </td>
                <td>
                    <button type="button" class="btn btn-info" name="${'parent-' + user.id}" data-toggle="modal" data-action="edit">Edit</button>
                </td>
                <td>
                    <button type="button" class="btn btn-danger" name="${'parent-' + user.id}" data-toggle="modal" data-action="delete">Delete</button>
                </td>
        `
    }

    static toUsers(users) {
        const tbody = document.createElement("tbody")
        users?.forEach(user => {
            const tr = document.createElement("tr")
            tr.id = 'parent-' + user.id
            tr.innerHTML = User.toUser(user)
            tbody.appendChild(tr)
        })
        return tbody
    }
}

