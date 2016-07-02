#!/usr/bin/perl
use LWP::Simple;
use JSON::PP;

use LWP::UserAgent;
my $ua = LWP::UserAgent->new;
my $servidor = "http://localhost:8080/api/usuarios";

#$usuarios = decode_json(get("http://localhost:8080/api/usuarios"));


print "Content-type: text/html\n\n";
print "<html><head>\n";
print "<title>Eliminar Usuario - PERL</title>\n";
print "</head>\n";
print "<body>";

 
# set custom HTTP request header fields
my $req = HTTP::Request->new(POST => $servidor);
$req->header('content-type' => 'application/json');
$req->header('x-auth-token' => 'kfksj48sdfj4jd9d');
 
# add POST data to HTTP request body
my $post_data = '{ "_id": "Dan", "address": "NY" }';
$req->content($post_data);
 
my $resp = $ua->request($req);
if ($resp->is_success) {
    my $message = $resp->decoded_content;
    print "Received reply: $message\n";
}
else {
    print "HTTP POST error code: ", $resp->code, "\n";
    print "HTTP POST error message: ", $resp->message, "\n";
}

print "</body>";