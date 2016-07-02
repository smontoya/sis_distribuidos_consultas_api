#!/usr/bin/perl
use LWP::Simple;
use JSON::PP;

$usuarios = decode_json(get("http://localhost:8080/api/usuarios"));


print "Content-type: text/html\n";
print "<html><head>\n
           <title>Listado de usaurios</title>
           <link rel='stylesheet' type='text/css' href='/static/css/bootstrap.min.css'>
         </head>\n";
print "<body>";
print "<table class='table table-bordered'>";
print "<th></th>";
print "<th>Email</th>";
print "<th>Nombres</th>";
print "<th>Apellidos</th>";
foreach my $item( @$usuarios ) { 
 print "
 	<tr>
 		<td> 
      <a href='/eliminar.pl?user_id=$item->{_id}'>Eliminar</a>
      <a href='/editar.pl?user_id=$item->{_id}'>Editar</a>
      <a href='/ver.pl?user_id=$item->{_id}'>Ver</a>
    </td>
 		<td> $item->{email} </td>
 		<td> $item->{nombres} </td>
 		<td> $item->{apellidos} </td>
	</tr>
 	";
 
}
print "</table>";
print "</body>";
