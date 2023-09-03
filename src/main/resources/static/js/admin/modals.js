const searchedElements = ["#v-pills-tabContent", "#editOrDeleteModal"];

const [usersContainer, editOrDeleteUserContainer]
    = searchedElements.map(selector => document.querySelector(selector));

const editOrDeleteUserForm = usersContainer.querySelector("#editOrDeleteModal form");

const showModal = (element = editOrDeleteUserContainer) => {
    element.style.display = 'block';
    element.classList.add("show");
}

export const hideModal = (element = editOrDeleteUserContainer) => {
    element.style.display = 'none';
    element.classList.remove("show");
}

export const modalsActions = e => {
    const action = e.target;
    if (action.tagName === "BUTTON" && action.dataset.toggle === "modal") {
        const actionButton = editOrDeleteUserContainer.querySelector(".modal-footer button[type=submit]")
        const actionParent = usersContainer.querySelector(`#${action.name}`);
        editOrDeleteUserForm.querySelectorAll("input.editable").forEach(input => {
            input.value = actionParent.querySelector(`.${input.id}`).textContent;
            editOrDeleteUserForm.dataset.action = `${action.dataset.action}`;
        })
        actionButton.textContent = action.dataset.action.toUpperCase();
        showModal()
        editOrDeleteUserContainer.querySelectorAll(".close-modal").forEach(closer => {
            closer.addEventListener("click", () => {
                hideModal()
            })
        })
    }
}

