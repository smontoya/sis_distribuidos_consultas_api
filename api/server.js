var express    = require('express');        // call express
var app        = express();                 // define our app using express
var bodyParser = require('body-parser');
var mongoose   = require('mongoose');
// configure app to use bodyParser()
// this will let us get the data from a POST
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

var port = process.env.PORT || 8080;        // set our port

mongoose.connect('mongodb://localhost/sistemas_distribuidos');
//192.168.1.1111111111111
//modelos
require("./models/Usuarios");
var Usuario  = mongoose.model('Usuarios');

// ROUTES FOR OUR API
// =============================================================================
var router = express.Router();

app.use(function(req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
  next();
});

router.get('/', function(req, res) {
    res.json({ message: 'LA xulaApi!' });   
});

    

router.route('/usuarios')
  .post(function(req, res) {
    var usuario = new Usuario();
    if (req.body.email) usuario.email = req.body.email
    if (req.body.nombres) usuario.nombres = req.body.nombres
    if (req.body.apellidos) usuario.apellidos = req.body.apellidos
    if (req.body.password) usuario.password = req.body.password
    if (req.body.seccion) usuario.seccion = req.body.seccion
    if (req.body.rut) usuario.rut = req.body.rut
    if (req.body.estado) usuario.estado = req.body.estado
    usuario.save(function(err) {
      if (err)
        res.send(err);
      res.json({ message: 'Usuario created!' });
    });       
  })
  .get(function(req, res) {
    Usuario.find(function(err, usuarios) {
      if(err) res.send(err);
      res.json(usuarios);
    });
  });

router.route('/usuarios/:usuario_id')
  .get(function(req, res) {
    Usuario.findById(req.params.usuario_id, function(err, usuario) {
      if (err)
          res.send(err);
      res.json(usuario);
    });
  })
  .put(function(req, res) {
    Usuario.findById(req.params.usuario_id, function(err, usuario) {
      if (err) res.send(err);
      if (req.body.email) usuario.email = req.body.email
      if (req.body.nombres) usuario.nombres = req.body.nombres
      if (req.body.apellidos) usuario.apellidos = req.body.apellidos
      if (req.body.password) usuario.password = req.body.password
      if (req.body.seccion) usuario.seccion = req.body.seccion
      if (req.body.rut) usuario.rut = req.body.rut
      if (req.body.estado) usuario.estado = req.body.estado

      usuario.save(function(err) {
        if (err) res.send(err);
        res.json({ message: 'usuario updated!' });
      });
    });
  })
  .delete(function(req, res) {
      Usuario.remove({
          _id: req.params.usuario_id
      }, function(err, usuario) {
          if (err) res.send(err);
          res.json({ message: 'Successfully deleted' });
      });
  });



// REGISTER  ROUTES -------------------------------
// all of our routes will be prefixed with /api
app.use('/api', router);

// START THE SERVER
app.listen(port);
console.log('Magic happens on port ' + port + ' YTS');
