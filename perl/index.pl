#!/usr/bin/perl
use LWP::Simple;
use JSON;

$usuarios = decode_json(get("http://localhost:8080/api/usuarios"));


print "Content-type: text/html\n\n";
print "<html><head>\n";
print "<title>CGI Test</title>\n";
print "</head>\n";
print "<body>";
foreach my $item( @$usuarios ) { 
print $item->{email}

 
}
print "</body>";