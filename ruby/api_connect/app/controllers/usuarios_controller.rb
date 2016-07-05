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
  	def update
  	end
end
