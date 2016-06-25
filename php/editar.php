<?php include("restclient.php"); 
  $api = new RestClient(array(
     'base_url' => "localhost:8080/api/")
  );

  if(isset($_POST['id'])){
  	$result = $api->put("usuarios/".$_GET['id'], 
	  										array(
		  										'email' => $_POST['email'],
		  										'nombres' => $_POST['nombres'],
		  										'apellidos' => $_POST['apellidos']
		  									)
		  							);
  	echo json_decode($result->response)->message;
  }

  $result = $api->get("usuarios/".$_GET['id']);
  $json_data = json_decode($result->response);

 ?>

	<form method="post" name="formActualizar" />
		<input type="hidden" id="id" name="id" value="<?php $json_data->_id ?>">
	  <table class="table table-bordered">
	  <th>Email</th>
	  <th>Nombres</th>
	  <th>otro</th>
	  <tr>
	    <td><input type="text" name="email" value="<?php echo $json_data->email?>" /></td>
	    <td><input type="text" name="nombres" value="<?php echo $json_data->nombres?>" /></td>
	    <td><input type="text" name="apellidos" value="<?php echo $json_data->apellidos?>" /></td>
	  </tr>
	  </table>
	  <input type="submit" value="Actualizar datos">
  </form>
