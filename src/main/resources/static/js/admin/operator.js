import {Routes as rout} from "./router.js";
import {hideModal} from "./modals.js";
import {User as user} from "../components/user.js";

const prepareDataToSend = (e) => {
    const formData = new FormData(e.target)
    const data = {}
    const roles = []
    for (const [key, value] of formData.entries()) {
        key.startsWith("roles") ? roles.push(value) : data[key] = value;
    }
    data.roles = roles
    return data
}
const sender = async (e, action) => {
    try {
        e.preventDefault();
        const result = await rout.query(rout[action], prepareDataToSend(e));
        if (result.id) {
            alert("пользователь добавлен/обновлен/удален");
            hideModal();
            return result; 
        }
    } catch (error) {
        console.error("Error:", error);
    }
}

export const operator = async (e) => {
    const action = e.target.dataset.action;
    try {
        const result = await sender(e, action);
        const tbody = document.querySelector(".table.table-striped tbody")
        let tr = tbody.querySelector('#parent-' + result.id);
        switch (action) {
            case "delete":
                tr.remove();
                break;
            case "edit":
                tr.innerHTML = user.toUser(result);
                break;
            case "create":
                tr = document.createElement("tr")
                tr.id = 'parent-' + result.id
                tr.innerHTML = user.toUser(result);
                tbody.appendChild(tr)
                break;
        }
    } catch (error) {
        console.error("Error:", error);
    }
}

