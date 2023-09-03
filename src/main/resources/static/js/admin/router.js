export class Routes {
    static api = window.location.origin + "/api/"
    static users = Routes.api + "users"
    static delete = Routes.api + "delete"
    static create = Routes.api + "create"
    static edit = Routes.api + "edit"
    static async query(url, data = {}, method = "post") {
        try {
            const header = new Headers()
            header.append('Content-type', 'application/json')
            header.append("Accept" , "application/json")
            header.append('X-CSRF-TOKEN', Routes.getToken())
            data['X-CSRF-TOKEN'] = Routes.getToken()
            const response = await fetch(url,{
                    method: method,
                    mode: "cors",
                    cache: "no-cache",
                    credentials: "same-origin",
                    headers: header,
                    redirect: "follow",
                    referrerPolicy: "no-referrer",
                    body: JSON.stringify(data),
                });
            if (response.ok) {
                console.log(response)
                return await response.json();
            }
        } catch (e) {
            throw new Error(e)
        }
    }
    static getToken() {
        return document.querySelector("meta[name='_csrf_token']").content;
    }
}


