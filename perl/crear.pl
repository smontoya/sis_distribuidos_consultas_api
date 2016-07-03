print <<IMPRIMIR
<link rel="stylesheet" type="text/css" href="/static/css/bootstrap.min.css">

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
IMPRIMIR;