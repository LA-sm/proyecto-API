<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro de Usuario</title>

    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Estilos personalizados -->
    <style>
        body {
            background: linear-gradient(135deg, #74ebd5, #9face6);
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0;
            animation: backgroundFade 6s infinite alternate ease-in-out;
        }

        @keyframes backgroundFade {
            0% { background: linear-gradient(135deg, #74ebd5, #9face6); }
            100% { background: linear-gradient(135deg, #9face6, #74ebd5); }
        }

        .card {
            border: none;
            border-radius: 20px;
            box-shadow: 0 15px 30px rgba(0, 0, 0, 0.1);
            animation: slideIn 0.8s ease-out;
            background: white;
        }

        @keyframes slideIn {
            from {
                transform: translateY(-50px);
                opacity: 0;
            }
            to {
                transform: translateY(0);
                opacity: 1;
            }
        }

        .card h1 {
            font-weight: 700;
            font-size: 28px;
            color: #333;
        }

        .form-control {
            border-radius: 12px;
            padding: 12px;
            border: 1px solid #ddd;
            transition: all 0.3s ease;
        }

        .form-control:focus {
            border-color: #5e60ce;
            box-shadow: 0 0 10px rgba(94, 96, 206, 0.4);
        }

        .btn-primary {
            background: linear-gradient(to right, #5e60ce, #5390d9);
            border: none;
            padding: 12px;
            border-radius: 12px;
            font-weight: bold;
            transition: all 0.3s ease-in-out;
        }

        .btn-primary:hover {
            transform: scale(1.05);
            background: linear-gradient(to right, #5390d9, #5e60ce);
        }

        .link {
            color: #5e60ce;
            font-weight: bold;
            text-decoration: none;
            transition: color 0.3s ease;
        }

        .link:hover {
            text-decoration: underline;
            color: #3f3fbd;
        }

        .mensaje {
            background-color: #28a745;
            color: white;
            border-radius: 10px;
            padding: 10px;
            text-align: center;
            margin-top: 15px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="card p-4 mx-auto" style="max-width: 420px;">
            <h1 class="text-center mb-4">Crear Cuenta</h1>

            <form action="/anonimo/formularioRegistro" method="post">
                <div class="mb-3">
                    <label for="user" class="form-label">Correo electrónico</label>
                    <input type="email" class="form-control" id="user" name="user" required placeholder="usuario@email.com">
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Contraseña</label>
                    <input type="password" class="form-control" id="password" name="password" required placeholder="••••••••">
                </div>
                <button type="submit" class="btn btn-primary w-100">Registrarse</button>
            </form>

            <div class="text-center mt-3">
                <p>¿Ya tienes una cuenta? <a href="/anonimo/loginRegistro?source=registro" class="link">Inicia sesión</a></p>
            </div>

            <!-- Mostrar mensaje si existe -->
            <c:if test="${not empty mensaje}">
                <p class="mensaje"><c:out value="${mensaje}" /></p>
            </c:if>
        </div>
    </div>
</body>
</html>
