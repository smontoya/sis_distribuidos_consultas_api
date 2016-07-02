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
    <link rel='stylesheet' type='text/css' href='/static/css/bootstrap.min.css'>
  </head>\n
  <body>
	<form method='POST'>
	  <table class='table table-bordered'>
	  	<th>Email</th>
	  	<th>Nombres</th>
	  	<th>Apellidos</th>
	    <tr>
 		  <td> <input type='text' value='$usuario->{email}' name='email' /> </td>
 		  <td> <input type='text' value='$usuario->{nombres}' name='nombres'/> </td>
 		  <td> <input type='text' value='$usuario->{apellidos}' name='apellidos'/> </td>
	    </tr>
      </table>
	  <button type='submit' class='btn btn-primary'>Actualizar Usuario</button> 
	</form>
  </body>
</html>";
