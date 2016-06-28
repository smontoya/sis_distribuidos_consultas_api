#!/usr/bin/perl
use LWP::Simple;
use JSON;

# $usuario = decode_json(get("http://localhost:8080/api/usuarios/123"));

print "Content-type: text/html\n\n";
print "<html><head>\n";
print "<title>CGI Test</title>\n";
print "</head>\n";
print "<body>";
print "<table class='table table-bordered'>";
print "<th></th>";
print "<th>Email</th>";