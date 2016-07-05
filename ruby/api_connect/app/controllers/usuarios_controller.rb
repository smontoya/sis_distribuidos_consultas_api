require 'httparty'

class UsuariosController < ApplicationController
    def index
        url = 'http://localhost:8080/api/usuarios'
        response = HTTParty.get(url)
        @data = response.parsed_response

    end
    def new
    end
    def create
        url = 'http://localhost:8080/api/usuarios'
        response = HTTParty.post(url,
            :body => {
                :email => params[:usuario]["email"],
                :nombres => params[:usuario]["nombres"],
                :apellidos => params[:usuario]["apellidos"]
                })
        redirect_to usuarios_path
    end
    def edit
        id = params[:id]
        url = 'http://localhost:8080/api/usuarios/' + id
        response = HTTParty.get(url)
        @usuario = response.parsed_response
    end
    def show
        id = params[:id]
        url = 'http://localhost:8080/api/usuarios/' + id
        response = HTTParty.get(url)
        @usuario = response.parsed_response
    end
    def destroy
        id = params[:id]
        url = 'http://localhost:8080/api/usuarios/' + id
        @result = HTTParty.delete(url)

        redirect_to usuarios_path
    end
    def update
        id = params[:id]
        url = 'http://localhost:8080/api/usuarios/' + id
        response = HTTParty.put(url,
            :body => {
                :user_id => id,
                :email => params[:usuario]["email"],
                :nombres => params[:usuario]["nombres"],
                :apellidos => params[:usuario]["apellidos"]
                })

        redirect_to usuarios_path
    end
end
