from flask import Flask, render_template, redirect, flash
app = Flask(__name__)
app.secret = "asda"
import requests
import json

@app.route("/")
def hello():
    return "Hello World!"

@app.route("/usuarios")
def usuarios():
    req = requests.get("http://localhost:8080/api/usuarios")
    return render_template('/listado.html', usuarios=req.json())

@app.route("/usuarios/<user_id>")
def get_usuario(user_id):
    url = "http://localhost:8080/api/usuarios/%s" % user_id
    req = requests.get(url)
    print(url)
    return render_template('/show.html', usuario=req.json())

@app.route("/usuarios/delete/<user_id>")
def del_usuario(user_id):
    url = "http://localhost:8080/api/usuarios/%s" % user_id
    req = requests.delete(url)
    flash("Usuario eliminado")
    return redirect(url_for(usuarios))


if __name__ == "__main__":
    app.run(host='0.0.0.0', debug=True)
