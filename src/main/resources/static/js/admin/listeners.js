import {findAllUsers} from "./findAllUsers.js";
import {modalsActions} from "./modals.js";
import {operator} from "./operator.js";
export function listenToTheSounds () {
    // загрузка всех пользователей при старте
    document.addEventListener("DOMContentLoaded", findAllUsers)

    // для показа и заполнения модальных окон
    document.addEventListener("click", modalsActions)

    // для отправки формы
    const actions = document.querySelectorAll(".api-action")
    actions.forEach(form => {
        form.addEventListener("submit", e => operator(e))
    })
}
