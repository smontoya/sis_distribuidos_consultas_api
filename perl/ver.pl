#!/usr/bin/perl
use LWP::Simple;
use JSON::PP;
use CGI;
my $query = new CGI;
my $user_id = $query->param('user_id');

$usuario= decode_json(get("http://localhost:8080/api/usuarios"."/".$user_id));

print "Content-type: text/html\n
<html>
  <head>
    <title>Editar usuario</title>
  </head>\n
  <body>
	  <table class='table table-bordered'>
	  	<th>Email</th>
	  	<th>Nombres</th>
	  	<th>Apellidos</th>
	    <tr>
 		  <td> $usuario->{email}</td>
 		  <td> $usuario->{nombres} </td>
 		  <td> $usuario->{apellidos}</td>
	    </tr>
      </table>
      <a href='/editar.pl?user_id=$usuario->{_id}'>Actualizar Usuario</a> 
  </body>
</html>";
