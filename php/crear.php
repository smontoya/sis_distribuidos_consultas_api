<?php 
  include("restclient.php"); 
  $api = new RestClient(array(
     'base_url' => "localhost:8080/api/")
  );

  if(isset($_POST['email']))
  {
    echo "metodo POST crear.php";
    $result = $api->post("usuarios", 
	array(
	  'email' => $_POST['email'],
	  'nombres' => $_POST['nombres'],
	  'apellidos' => $_POST['apellidos']
	)
      );
    echo json_decode($result->response)->message;
  }

 ?>

  <a href="index.php">Indice</a>

  <form method="post" name="formActualizar" />
    <table class="table table-bordered">
    <th>Email</th>
    <th>Nombres</th>
    <th>Apellidos</th>
    <tr>
      <td><input type="text" name="email"  /></td>
      <td><input type="text" name="nombres"  /></td>
      <td><input type="text" name="apellidos"  /></td>
    </tr>
    </table>
    <input type="submit" value="Crear usuario">
  </form>
