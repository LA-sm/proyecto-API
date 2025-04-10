<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Sesión</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        body {
            background-color: #f4f4f9;
            font-family: Arial, sans-serif;
        }

        .form-container {
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            max-width: 500px;
            margin: auto;
        }

        .form-container h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        .btn-primary {
            width: 100%;
            padding: 10px;
        }

        .form-control {
            border-radius: 5px;
        }

        .form-link {
            text-align: center;
            margin-top: 15px;
        }

        .alert {
            text-align: center;
            margin-top: 15px;
        }
    </style>
</head>
<body>

<div class="form-container">
    <h2>Iniciar Sesión</h2>
    <form class="row g-3" action="/anonimo/formularioLogin" method="post">
        <div class="col-md-12">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" id="email" name="email" required placeholder="Introduce tu correo electrónico">
        </div>
        <div class="col-md-12">
            <label for="password" class="form-label">Contraseña</label>
            <input type="password" class="form-control" id="password" name="password" required placeholder="Introduce tu contraseña">
        </div>
        <div class="col-12">
            <button type="submit" class="btn btn-primary">Iniciar Sesión</button>
        </div>
    </form>

    <div class="form-link">
        <p>¿No tienes cuenta? <a href="/anonimo/loginRegistro?source=login">Regístrate aquí</a></p>
    </div>
</div>

<c:if test="${not empty mensaje}">
    <div class="alert alert-warning" role="alert">
        <c:out value="${mensaje}" />
    </div>
</c:if>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
