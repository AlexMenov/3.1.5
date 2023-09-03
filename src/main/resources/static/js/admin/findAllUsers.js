import {User} from "../components/user.js";
import {Routes as rout} from "./router.js";

export const findAllUsers = async () => {
    const usersTable = document.querySelector("table.table-striped")
    const users = User.toUsers(await rout.query(rout.users))
    usersTable.appendChild(users)
}

