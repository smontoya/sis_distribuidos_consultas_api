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
    usuario.name = req.body.name;
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
      usuario.name = req.body.name;  // update the usuarios info
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
console.log('Magic happens on port ' + port);




/*var express = require('express')
  , app = express()
  , http = require('http')
  , server = http.createServer(app)
  , mongoose = require('mongoose')
  , io = require('socket.io').listen(server)
  , bodyParser = require('body-parser')
  , engine = require('ejs-locals')
  , session = require('client-sessions');

var port = 8080;


// set layout manager
app.set('view engine', 'ejs');
app.set('view options', { layout:'layout' });
var EJSLayout = require('express-ejs-layouts');
app.use(EJSLayout);

//session config
app.use(session({
  cookieName: 'session',
  secret: '3pFBgi3oG2j6LyWQAMIBZLUHT3FW0lAVZEkKf11j',
  duration: 3600* 1000,
  activeDuration: 3600 * 1000,
}));

app.use(bodyParser.urlencoded({
    extended: true
}))


//modelos
require("./models/Usuarios");
var Usuario  = mongoose.model('Usuarios');

//Path de los CSS que utilizarán para el estilo de la página
app.use("/assets/css", express.static(__dirname + '/assets/css'));

//Path de funciones en Javascript que podrían utilizar
app.use("/function", express.static(__dirname + '/function'));
app.use("/assets/js", express.static(__dirname + '/assets/js'));
app.use("/assets/fonts", express.static(__dirname + '/assets/fonts'));
app.use("/assets/images", express.static(__dirname + '/assets/images'));
//app.use("/assets/js/plugins", express.static(__dirname + '/assets/js/plugins'));




app.use(function(req, res, next) {
  if (req.session && req.session.user) {
    Usuario.findOne({ email: req.session.user.email }, function(err, user) {
      if (user) {
        req.user = user;
        delete req.user.password; // delete the password from the session
        req.session.user = user;  //refresh the session value
        res.locals.user = user;
      }
      // finishing processing the middleware and run the route
      next();
    });
  } else {
    next();
  }
});

//login
app.get('/login', function(req, res){
    res.render('login', {layout: 'blank'});
})

app.post('/login', function(req, res) {
  Usuario.findOne({ email: req.body.email }, function(err, user) {
    if (!user) {
      res.send('Usuario o password no valida');
    } else {
      if (req.body.password === user.password) {
        req.session.user = user;
        res.send('ok');
      } else {
        res.send('Usuario o password no valida');
      }
    }
  });
});

app.get('/logout', function(req, res) {
  req.session.reset();
  res.redirect('/login');
});

function requireLogin (req, res, next) {
  if (!req.user) {
    res.redirect('/login');
  } else {
    next();
  }
};

//Routing
app.get('/', function (req, res) {
  res.redirect('/dashboard');
});

app.get('/dashboard',requireLogin, function(req, res) {
  if (req.session && req.session.user) { // Check if session exists
    // lookup the user in the DB by pulling their email from the session
    Usuario.findOne({ email: req.session.user.email }, function (err, user) {
      if (!user) {
        // if the user isn't found in the DB, reset the session info and redirect the user to the login page
        req.session.reset();
        res.redirect('/login');
      } else {
        res.locals.user = user;
        res.render('dashboard');
      }
    });
  } else {
    res.redirect('/login');
  }
});


//Routing
//LIST AND VIEW
app.get('/usuarios', requireLogin, function (req, res) {
    Usuario.find(function(err, usuarios) {
        if(err) res.send(500, err.message);
        res.render('usuarios/index', {users: usuarios});
    });
});
app.get('/usuarios/:user_id', requireLogin, function (req, res) {
    var user_id = req.params.user_id;
    Usuario.findOne({_id:user_id}).exec(function(err, user){
        res.render( 'usuarios/view', {'model':user});
    });
});

//UPDATE
app.get('/usuarios/update/:user_id', requireLogin, function (req, res) {
    var user_id = req.params.user_id;
    Usuario.findOne({_id:user_id}).exec(function(err, user){
        res.render( 'usuarios/update', {'model':user});
    });
});
app.post('/usuarios/update/:user_id', requireLogin, function (req, res) {
    var user = req.body.User
    var user_id = req.params.user_id;
    if(user != null){
        user['estado'] = user['estado'] == 'on'
        Usuario.update(user_id, user, function(errmodel){
            if (errmodel) {
                console.log(errmodel);
                res.send("Error:Error al actualizar el usuario");
            }else {
                res.redirect('/usuarios');
            }
        });
    }else{
        Usuario.findOne({_id:user_id}).exec(function(err, update_user){
            res.render( 'usuarios/update', {'model': update_user});
        });
    }
});

//CREATE
app.post('/usuarios/create', requireLogin, function (req, res) {
    var user = req.body.User
    if(user != null){
        user['estado'] = user['estado'] == 'on'
        Usuario.create(user, function(errmodel){
            if (errmodel) {
                console.log(errmodel);
                res.send("Error:Error al crear el usuario");
            }else {
                res.redirect('/usuarios');
            }
        });
    }else{
        res.render( "user/create");
    }
});
app.get('/usuarios/create', requireLogin, function (req, res) {
    res.render( 'usuarios/create');
});
//DELETE
app.post('/usuarios/delete/:user_id', function (req, res) {
    var user_id = req.params.user_id;
    console.log(user_id)
    Usuario.findOne({_id:user_id}).exec(function(err, user){
        user.remove();
        res.send('{"ok": "ok"}')
    });
});


mongoose.connect('mongodb://192.168.1.1/sistemas_distribuidos', function(err, res) {  
  if(err) {
    console.log('ERROR: connecting to Database. ' + err);
  }
    console.log('OK', 'DataBase MONGO ');
});

server.listen(port);
console.log('OK', 'Web Services NodeJS in Port ' + port);

io.sockets.on('connection', function (socket) {
    
    socket.on('initRoom', function (data) {
        console.log("Un usuario entró al chat de la sala " + data.room);
        socket.join(data.room);
    });

    socket.on('exitRoom', function (data) {
        console.log("Un usuario se salió del chat de la sala " + data.room);
        socket.leave(data.room);
    });

    socket.on('disconnect', function () {
        console.log("Usuario desconectado");
    });
    
    socket.on('broadcast', function (data) {
        socket.broadcast.emit('broadcastCallback', { text:data.text, color: data.color || null});
    });

    socket.on('multicast', function (data) {
        console.log("Se envió el mensaje " + data.text + " a la sala " + data.room);
        io.sockets.in(data.room).emit('multicastCallback', {text:data.text});
    });
});

*/