const searchedElements = [
    "#v-pills-admin-tab",
    "#v-pills-user-tab",
    "#v-pills-user",
    "#v-pills-tabContent",
    "#editOrDeleteModal"];

const [
    adminButton,
    userButton,
    userPersonalContainer,
    usersContainer,
    editOrDeleteUserContainer
] = searchedElements.map(selector => document.querySelector(selector));

// для панелей админа и пользователя
adminButton?.classList.add("active");

if (!adminButton && userButton) {
    userButton.classList.add("active");
    userPersonalContainer.classList.add("active", "show");
}

// для показа и заполнения модального окна
usersContainer.addEventListener("click", (e) => {
    const actionElement = e.target;
    if (actionElement.tagName === "BUTTON" && actionElement.dataset.toggle === "modal") {
        const actionElementParent = usersContainer.querySelector(`#${actionElement.name}`);
        const editOrDeleteUserForm = usersContainer.querySelector("#editOrDeleteModal form");
        const urlAction = `${window.location.origin}/admin/${actionElement.dataset.action}`;
        editOrDeleteUserForm.querySelectorAll("input.editable").forEach(input => {
            input.value = actionElementParent.querySelector(`.${input.id}`).textContent;
            editOrDeleteUserForm.action = urlAction;
        })
        editOrDeleteUserContainer.querySelector(".modal-footer button[type=submit]").textContent = actionElement.dataset.action.toUpperCase();
        editOrDeleteUserContainer.style.display = 'block';
        editOrDeleteUserContainer.classList.add("show");
        document.body.overflow = 'hidden';
        editOrDeleteUserContainer.querySelectorAll(".close-modal").forEach(element => {
            element.addEventListener("click", e => {
                editOrDeleteUserContainer.style.display = 'none';
                editOrDeleteUserContainer.classList.remove("show");
                document.body.overflow = '';
            })
        })
    }
})