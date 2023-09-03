const elements = [
    "#v-pills-admin-tab",
    "#v-pills-user-tab",
    "#v-pills-user",
];

const [
    adminButton,
    userButton,
    userPersonalContainer,
] = elements.map(selector => document.querySelector(selector));

// для панелей админа и пользователя
adminButton?.classList.add("active");

if (!adminButton && userButton) {
    userButton.classList.add("active");
    userPersonalContainer.classList.add("active", "show");
}