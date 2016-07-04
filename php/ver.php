<?php 
  include("restclient.php"); 
  $api = new RestClient(array(
     'base_url' => "localhost:8080/api/")
  );
  $result = $api->get("usuarios/".$_GET['id']);
  $json_data = json_decode($result->response);
 ?>

<link rel="stylesheet" type="text/css" href="./static/css/bootstrap.min.css">

  <a href="index.php">Indice</a>

  <table class="table table-bordered">
  <th></th>
  <th>Email</th>
  <th>Nombres</th>
  <th>Apellidos</th>
  <tr>
    <td><a href="editar.php?id=<?php echo $_GET['id'] //$listado->_id ?>" class=" fa fa-pencil">Editar </a></td>
    <td><?php echo $json_data->email?></td>
    <td><?php echo $json_data->nombres?></td>
    <td><?php echo $json_data->apellidos?></td>
  </tr>
  </table>
