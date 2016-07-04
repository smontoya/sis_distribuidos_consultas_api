<?php 
  include("restclient.php"); 
  $api = new RestClient(array(
     'base_url' => "localhost:8080/api/")
  );

  if(isset($_POST['id']))
  {
    echo "metodo PUT editar.php ";
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

<link rel="stylesheet" type="text/css" href="./static/css/bootstrap.min.css">

  <a href="index.php">Indice</a>
  <form method="post" name="formActualizar" class="well" />
    <input type="hidden" id="id" name="id" value="<?php $json_data->_id ?>">
    <div class="row">
      <label class="col-md-3">Email</label>
      <input class="col-md-8" type="text" name="email" value="<?php echo $json_data->email?>" />
    </div>
    
    <div class="row">
      <label class="col-md-3">Nombres</label>
      <input class="col-md-8" type="text" name="nombres" value="<?php echo $json_data->nombres?>" />
    </div>
    
    <div class="row">
      <label class="col-md-3">Apellidos</label>
      <input class="col-md-8" type="text" name="apellidos" value="<?php echo $json_data->apellidos?>" />
    </div>
    
    <button type="submit" class="btn btn-primary">Actualizar usuario</button>
  </form>
