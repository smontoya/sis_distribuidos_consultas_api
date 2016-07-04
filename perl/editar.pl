#!/usr/bin/perl
use LWP::Simple;
use HTTP::Request::Common;
use JSON::PP;
use CGI;
my $query = new CGI;
my $user_id = $query->param('user_id');

my $url = "http://localhost:8080/api/usuarios/".$user_id;

my $mensaje = "";
if ($query->param('email')){
    my $ua = LWP::UserAgent->new;
    my $req = POST($url, ,Content => [
       'email' => $query->param('email'),
       'nombres' => $query->param('nombres'),
       'apellidos' => $query->param('apellidos')
      ]);
    $req->method("PUT");
    
    my $resp = $ua->request($req);
    
    $mensaje = $resp->content;

    # my $post_data = '{ "email": "$query->param(\'email\')", "nombres": "'.$query->param('nombres').'"}';

}


$usuario= decode_json(get($url));

print "Content-type: text/html\n
<html>
  <head>
    <title>Editar usuario</title>
    <link rel='stylesheet' type='text/css' href='/static/css/bootstrap.min.css'>
  </head>
  <body>
    <div class='alert alert-info'>$mensaje</div>
    <form method='POST'>
        <input type='hidden' name='user_id' value='$user_id'>
      <table class='table table-bordered'>
        <th>Email</th>
        <th>Nombres</th>
        <th>Apellidos</th>
        <tr>
          <td> <input type='text' value='$usuario->{email}' name='email' required=required/> </td>
          <td> <input type='text' value='$usuario->{nombres}' name='nombres'/> </td>
          <td> <input type='text' value='$usuario->{apellidos}' name='apellidos'/> </td>
        </tr>
      </table>
      <a href='/' class='btn btn-default'>Volver a listado</a>

      <button type='submit' class='btn btn-primary'>Actualizar Usuario</button> 
    </form>
  </body>
</html>";
