package main

import (
    "fmt"
    "encoding/json"
    "net/http"
    "log"
)

type User struct {
    id string `json:"_id"`
    email string `json:"email"`
    nombres string `json:"nombres"`
    apellidos string `json:"apellidos"`
    
}
type UserApiResp struct {
    
    Users []User `json:"users"`
}

func getJson(url string, target interface{}) error {
    r, err := http.Get(url)
    if err != nil {
        return err
    }
    defer r.Body.Close()
    fmt.Println("data:", r.Body);
    return json.NewDecoder(r.Body).Decode(target)
}

func index(w http.ResponseWriter, r *http.Request) {
    /*var usersMap map[string]*respUser*/
    url := "http://localhost:8080/api/usuarios"
    
    users := []*User{}
    getJson(url, users)

    for user := range users {
        println(user)
        // Create a new pointer to response Struct
        /*r := new(respStruct)*/

        // Get user with id i into the newly created response struct
        
    }
    /*//resp := &respUser{}
    users, err := api.Res("usuarios").Get()
    if err != nil {
        print(err)
        print(users)
        return
    }
    print(users)*/



    fmt.Fprintf(w, "<h1>Usuarios</h1>") // send data to client side
    fmt.Fprintf(w, "<table >")
    fmt.Fprintf(w, "<th>Email</th>")
    fmt.Fprintf(w, "<th>nombres</th>")
    fmt.Fprintf(w, "<th>Apellidos</th>")

    
    //for user := range users {
        // Create a new pointer to response Struct
        /*r := new(respStruct)*/

        // Get user with id i into the newly created response struct
        
    //}


    fmt.Fprintf(w, "</table >")
}

func show_user(w http.ResponseWriter, r *http.Request) {
    /*var usersMap map[string]*respUser*/
    url := "http://localhost:8080/api/usuarios/" + r.URL.Query().Get("user_id")


    fmt.Fprintf(w, "<h1>Ver usuario</h1>") // send data to client side
    
    user := User{}
    getJson(url, user)
    fmt.Println("user:", user);
    fmt.Fprintf(w, user.email)


    


}

func main() {
    http.HandleFunc("/", index)
    http.HandleFunc("/usuarios/ver", show_user)
    

    println("the magic run 9090") 
    err := http.ListenAndServe("0.0.0.0:9090", nil) // set listen port
    if err != nil {
        log.Fatal("ListenAndServe: ", err)
    }
}