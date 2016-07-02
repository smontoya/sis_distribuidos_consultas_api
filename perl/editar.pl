#!/usr/bin/perl
use LWP::Simple;
use JSON::PP;
use CGI;
my $query = new CGI;
my $user_id = $query->param('user_id');

$usuario= decode_json(get("http://localhost:8080/api/usuarios"."/".$user_id));

print "Content-type: text/html\n\n";
print "<html><head>\n";
print "<title>CGI Test</title>\n";
print "</head>\n
  <body>
	<form method='POST' name='formulario'>";

print "<table class='table table-bordered'>";
print "<th>Email</th>";
print "<th>Nombres</th>";
print "<th>Apellidos</th>
<tr>
 		<td> <input type='text' value='$usuario->{email}' name='email' /> </td>
 		<td> <input type='text' value='$usuario->{nombres}' value='nombres'/> </td>
 		<td> <input type='text' value='$usuario->{apellidos}' value='apellidos'/> </td>
	</tr>";
 
print "</table>
		<input type='submit' value='Actualizar Usuario' /> 
	</form>";
print "</body>";
