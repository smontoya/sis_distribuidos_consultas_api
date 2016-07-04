#!/usr/bin/perl
use LWP;
use CGI;
use JSON::PP;
my $query = new CGI;


my $mensaje = "";
if ($query->param('email')){
    my $browser = LWP::UserAgent->new;
    my $url = "http://localhost:8080/api/usuarios";

    my $response = $browser->post($url,
    [
       'email' => $query->param('email'),
       'nombres' => $query->param('nombres'),
       'apellidos' => $query->param('apellidos')
      ],
    );

    $mensaje = decode_json($response->content)->{"message"} ;

}



print "Content-type: text/html\n";
print "<html><head>\n
    <title>Agregar usuario</title>
    <link rel='stylesheet' type='text/css' href='/static/css/bootstrap.min.css'>
  </head>
  <body>
    <div class='jumbotron'>
      <h1>Crear usuario</h1>
    </div>
    <div class='alert alert-info'>$mensaje</div>
    <form method='post' class='well' />
      <div class='row'>
        <label class='col-md-3'>Email</label>
        <input class='col-md-8' type='text' name='email'  />
      </div>
      
      <div class='row'>
        <label class='col-md-3'>Nombres</label>
        <input class='col-md-8' type='text' name='nombres'  />
      </div>
      
      <div class='row'>
        <label class='col-md-3'>Apellidos</label>
        <input class='col-md-8' type='text' name='apellidos'  />
      </div>
      
      <button type='submit' class='btn btn-primary'>Crear usuario </button>
    </form>
    <a href='index.pl' class='btn btn-primary'>Volver a listado</a>
  </body>
</html>";