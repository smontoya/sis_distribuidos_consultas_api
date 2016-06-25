<?php include("restclient.php"); 
  $api = new RestClient(array(
     'base_url' => "localhost:8080/api/")
  );
  $result = $api->get("usuarios/".$_GET['id']);
  $json_data = json_decode($result->response);
 ?>


  <table class="table table-bordered">
  <th></th>
  <th>Email</th>
  <th>Nombres</th>
  <th>otro</th>
  <tr>
    <td><a href="/editar.php?id=<?php echo $_GET['id'] //$listado->_id ?>">Editar </a></td>
    <td><?php echo $json_data->email?></td>
    <td><?php echo $json_data->nombres?></td>
    <td><?php echo $json_data->apellidos?></td>
  </tr>
  </table>
