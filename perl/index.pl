#!/usr/bin/perl
use LWP::Simple;
use JSON::PP;

$usuarios = decode_json(get("http://localhost:8080/api/usuarios"));


print "Content-type: text/html\n\n";
print "<html><head>\n";
print "<title>CGI Test</title>\n";
print "</head>\n";
print "<body>";
print "<table class='table table-bordered'>";
print "<th></th>";
print "<th>Email</th>";
print "<th>Nombres</th>";
print "<th>Apellidos</th>";
foreach my $item( @$usuarios ) { 
 print "
 	<tr>
 		<td> <a href='/ver.pl?user_id=$item->{_id}'>Ver</a></td>
 		<td> $item->{email} </td>
 		<td> $item->{nombres} </td>
 		<td> $item->{apellidos} </td>
	</tr>
 	";
 
}
print "</table>";
print "</body>";