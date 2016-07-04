#!/usr/bin/perl
use LWP::Simple;
use JSON::PP;
use HTTP::Request;
# use Data::Dumper;
use CGI;
my $query = new CGI;
my $user_id = $query->param('user_id');

use LWP::UserAgent;
my $ua = LWP::UserAgent->new;
my $servidor = "http://localhost:8080/api/usuarios/".$user_id;

 
# set custom HTTP request header fields
my $req = HTTP::Request->new(DELETE => $servidor);
# $req->header('content-type' => 'application/json');
# $req->header('x-auth-token' => 'kfksj48sdfj4jd9d');

# add POST data to HTTP request body
my $post_data = '{ "_id": "'.$user_id.'" }';
# print $req->message( );
 
 
my $resp = $ua->request($req);
# print $resp->message;

print "Content-type: text/html\n";
print "
  <html>
  <head>
    <title>Eliminar usuario</title>
    <link rel='stylesheet' type='text/css' href='./static/css/bootstrap.min.css'>
  </head>
  <body>";
my $message = $resp->decoded_content; 
# print '$resp->message: '.$resp->message."\n"; 
# print "Received reply: $message";	

$texto = decode_json($message);
# print  Dumper($texto);
print $texto->{'message'};
	
print "
    </br>
    <a href='index.pl' class=''>Volver al listado</a>
    </body>";

# if ($resp->is_success) {
#     my $message = $resp->decoded_content;
#     print "Received reply: $message\n";
# }
# else {
#     print "HTTP POST error code: ", $resp->code, "\n";
#     print "HTTP POST error message: ", $resp->message, "\n";
# }
