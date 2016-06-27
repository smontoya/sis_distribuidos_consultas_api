<?php 
  include("restclient.php"); 
  $api = new RestClient(array(
     'base_url' => "localhost:8080/api/")
  );
?>

<html>
<head>
</head>
<body>

  <a href="crear.php">Crear</a>


 <?php
  $result = $api->get("usuarios");
  $json_data = json_decode($result->response);
  ?>
  <table class="table table-bordered">
  <th></th>
  <th>Email</th>
  <th>Nombres</th>
  <th>Apellidos</th>
  <?php foreach ( $json_data as $listado) { ?>
  <tr>
    <td><a href="ver.php?id=<?php echo $listado->_id?>">Ver </a></td>
    <td><?php echo $listado->email?></td>
    <td><?php echo $listado->nombres?></td>
    <td><?php echo $listado->apellidos?></td>
  </tr>
  <?php } ?>
  </table>
</body>