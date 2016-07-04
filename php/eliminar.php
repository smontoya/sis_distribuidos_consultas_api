<?php 
  include("restclient.php"); 
  $api = new RestClient(array(
     'base_url' => "localhost:8080/api/")
  );

  if(isset($_GET['id']))
  {
    $result = $api->delete("usuarios/".$_GET['id']);
    echo json_decode($result->response)->message;
  }
  else
  {
    echo "Error al eliminar";
  }
 ?>

<html>
  <head>
    <link rel="stylesheet" type="text/css" href="./static/css/bootstrap.min.css">
  </head>
  <body>
    </br>
    <a href="index.php" class="">Volver al listado</a>
  </body>
</html>