var mongoose = require('mongoose'),  
    Schema   = mongoose.Schema;

var usuarioSchema = new Schema({  
	email:{type:'String'},
	nombres:{type:'String'},
	apellidos:{type:'String'},
	password:{type:'String'},
	seccion:{type:'String'},
	rut:{ type:'String'},
	estado: {type:'Boolean', default: false}
});

module.exports = mongoose.model('Usuarios', usuarioSchema);
