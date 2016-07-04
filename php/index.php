<?php
include("restclient.php");
$api = new RestClient(array(
          'base_url' => "localhost:8080/api/")
);
?>
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="./static/css/bootstrap.min.css">
  </head>
  <body>
    <?php
      $result = $api->get("usuarios");
      $json_data = json_decode($result->response);
    ?>
    <table class="table table-bordered">
      <th></th>
      <th>Email</th>
      <th>Nombres</th>
      <th>Apellidos</th>
      <th>Password</th>
      <th>Sección</th>
      <th>RUN</th>
      <th>Habilitado</th>
      <?php foreach ( $json_data as $listado) { ?>
      <tr>
        <td>
          <a href="eliminar.php?id=<?php echo $listado->_id?>" class=" delete_user">Eliminar </a>
          <a href="editar.php?id=<?php echo $listado->_id?>" class=" fa fa-pencil">Editar </a>
          <a href="ver.php?id=<?php echo $listado->_id?>" class=" fa fa-eye">Ver </a>
        </td>
        <td><?php echo $listado->email?></td>
        <td><?php echo $listado->nombres?></td>
        <td><?php echo $listado->apellidos?></td>
        <td><?php echo $listado->password?></td>
        <td><?php echo $listado->seccion?></td>
        <td><?php echo $listado->rut?></td>
        <td><?php echo $listado->estado?></td>
      </tr>
      <?php } ?>
    </table>

    <a href="crear.php" class="btn btn-primary">Crear usuario</a>
  </body>
</html>