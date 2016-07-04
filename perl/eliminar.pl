#!/usr/bin/perl
use LWP::Simple;
use JSON::PP;
use HTTP::Request;
use CGI;
my $query = new CGI;
my $user_id = $query->param('user_id');

use LWP::UserAgent;
my $ua = LWP::UserAgent->new;
my $servidor = "http://localhost:8080/api/usuarios/".$user_id;

print $servidor;
 
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
print "<html><head>\n
           <title>Listado de usaurios</title>
           <link rel='stylesheet' type='text/css' href='/static/css/bootstrap.min.css'>
         </head>
	<body>
	$resp
	</body>";

# if ($resp->is_success) {
#     my $message = $resp->decoded_content;
#     print "Received reply: $message\n";
# }
# else {
#     print "HTTP POST error code: ", $resp->code, "\n";
#     print "HTTP POST error message: ", $resp->message, "\n";
# }
