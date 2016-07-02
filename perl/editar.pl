#!/usr/bin/perl
use LWP::Simple;
use JSON::PP;

$usuarios = decode_json(get("http://localhost:8080/api/usuarios"));


print "Content-type: text/html\n\n";
print "<html><head>\n";
print "<title>CGI Test</title>\n";
print "</head>\n";
print "<body>
	<form method='POST' name='formulario'>";
print "<table class='table table-bordered'>";
print "<th>Email</th>";
print "<th>Nombres</th>";
print "<th>Apellidos</th>";
foreach my $item( @$usuarios ) { 
 print "
 	<tr>
 		<td> <input type='text' value='$item->{email}' /> </td>
 		<td> <input type='text' value='$item->{nombres}' /> </td>
 		<td> <input type='text' value='$item->{apellidos}' /> </td>
	</tr>
 	";
 
}
print "</table>
		<input type='submit' value='Actualizar Usuario' /> 
	</form>";
print "</body>";