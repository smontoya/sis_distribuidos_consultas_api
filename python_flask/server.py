from flask import Flask, render_template, redirect, flash, request, url_for
app = Flask(__name__)
import requests
import json

@app.route("/")
def hello():
    return "Utilice <a href='/usuarios'>/usuarios</a>"

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
    flash(req.json().get("message"))
    return redirect(url_for('usuarios'))

@app.route("/usuarios/update/<user_id>", methods=['GET', 'POST'])
def update_usuario(user_id):
    if request.method == 'POST':
        parametros = {
            'email': request.form.get("email"),
            'nombres': request.form.get("nombres"),
            'apellidos': request.form.get("apellidos"),
            'seccion': request.form.get("seccion"),
            'rut': request.form.get("rut")
        }
        url = "http://localhost:8080/api/usuarios/%s" % user_id
        req = requests.put(url, data=parametros)
        flash(req.json().get("message"))
    

    url = "http://localhost:8080/api/usuarios/%s" % user_id
    req = requests.get(url)
    return render_template('/edit.html', usuario=req.json())    

@app.route("/usuarios/create", methods=['GET', 'POST'])
def create_usuario():
    if request.method == 'POST':
        parametros = {
            'email': request.form.get("email"),
            'nombres': request.form.get("nombres"),
            'apellidos': request.form.get("apellidos"),
            'seccion': request.form.get("seccion"),
            'rut': request.form.get("rut")
        }
        url = "http://localhost:8080/api/usuarios/"
        req = requests.post(url, data=parametros)
        flash(req.json().get("message"))
    return render_template('/crear.html')


if __name__ == "__main__":
    app.secret_key = 'super_secret_key'
    app.run(host='0.0.0.0', debug=True)
