#!/usr/bin/perl
use LWP::Simple;
use LWP::UserAgent;
use JSON::PP;
use HTTP::Request;

use CGI;
my $query = new CGI;
my $ua = LWP::UserAgent->new;

my $user_id = $query->param('user_id');
my $url = "http://localhost:8080/api/usuarios/".$user_id;

my $req = HTTP::Request->new(DELETE => $url);
 
my $resp = $ua->request($req);
my $message = $resp->decoded_content; 
$texto = decode_json($message);


print "Content-type: text/html\n";
print "
<html>
  <head>
    <title>Eliminar usuario</title>
    <link rel='stylesheet' type='text/css' href='./static/css/bootstrap.min.css'>
  </head>
  <body>
    $texto->{'message'}
    </br>
    <a href='index.pl' class=''>Volver al listado</a>
  </body>
</html>";