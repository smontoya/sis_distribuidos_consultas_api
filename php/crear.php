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

<link rel="stylesheet" type="text/css" href="./static/css/bootstrap.min.css">

<div class="jumbotron">
<h1>Crear usuario</h1>  
</div>
  <form method="post" name="formActualizar" class="well" />
    <div class="row">
      <label class="col-md-3">Email</label>
      <input class="col-md-8" type="text" name="email"  />
    </div>
    
    <div class="row">
      <label class="col-md-3">Nombres</label>
      <input class="col-md-8" type="text" name="nombres"  />
    </div>
    
    <div class="row">
      <label class="col-md-3">Apellidos</label>
      <input class="col-md-8" type="text" name="apellidos"  />
    </div>
    
    <button type="submit" class="btn btn-primary">Crear usuario </button>
  </form>
  <a href="index.php" class="btn btn-primary">Volver a listado</a>
