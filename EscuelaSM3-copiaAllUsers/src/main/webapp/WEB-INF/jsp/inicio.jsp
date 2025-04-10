<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Perfil de Usuario</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background: linear-gradient(to right, #667eea, #764ba2);
            color: white;
            margin: 0;
            padding: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .container {
            background: rgba(0, 0, 0, 0.8);
            padding: 20px;
            border-radius: 10px;
            width: 80%;
            text-align: center;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
        }

        img {
            width: 10%;
            border-radius: 10px;
            margin-bottom: 10px;
        }

        .btn-custom {
            background: #00ffcc;
            color: black;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            margin: 10px;
            transition: 0.3s;
        }

        .btn-custom:hover {
            background: #009977;
        }

    </style>
</head>
<body>

<div class="container">
    <h1>Bienvenido, ${usuario.firstName}!</h1>
    <img src="${usuario.image}" alt="Imagen de perfil">
    <h3>${usuario.firstName} ${usuario.lastName}</h3>

    <button class="btn btn-custom" data-bs-toggle="modal" data-bs-target="#editarModal">✏️ Editar perfil</button>

    <form action="/logout" method="post">
        <button type="submit" class="btn btn-danger">Cerrar sesión</button>
    </form>
</div>

<!-- Modal -->
<div class="modal fade" id="editarModal" tabindex="-1" aria-labelledby="editarModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content text-black">
      <div class="modal-header">
        <h5 class="modal-title" id="editarModalLabel">Editar Usuario</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
      </div>
      <div class="modal-body">
        <form id="formUsuario">
            <input type="hidden" name="id" value="${usuario.id}">
            <div class="mb-3">
                <label class="form-label">Nombre</label>
                <input type="text" class="form-control" name="firstName" value="${usuario.firstName}">
            </div>
            <div class="mb-3">
                <label class="form-label">Apellido</label>
                <input type="text" class="form-control" name="lastName" value="${usuario.lastName}">
            </div>
            <div class="mb-3">
                <label class="form-label">URL Imagen</label>
                <input type="text" class="form-control" name="image" value="${usuario.image}">
            </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
        <button type="button" class="btn btn-primary" onclick="guardarCambios()">Guardar Cambios</button>
      </div>
    </div>
  </div>
</div>

<!-- Scripts -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
    function guardarCambios() {
        const formData = $("#formUsuario").serialize();

        $.ajax({
            url: "/usuario/updateUsuario",
            type: "POST",
            data: formData,
            success: function () {
                alert("Datos actualizados correctamente.");
                location.reload(); // Recargar para ver los cambios
            },
            error: function () {
                alert("Error al actualizar los datos.");
            }
        });

        $("#editarModal").modal("hide");
    }
</script>

</body>
</html>
