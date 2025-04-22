<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Estilazo</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Estilos premium con animaciones y glassmorphism -->
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            height: 100vh;
            background: linear-gradient(135deg, #c9d6ff, #e2e2e2);
            overflow: hidden;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .floating-shape {
            position: absolute;
            border-radius: 50%;
            background: rgba(255, 255, 255, 0.2);
            animation: float 8s ease-in-out infinite alternate;
            z-index: 0;
        }

        .shape1 { width: 120px; height: 120px; top: 10%; left: 10%; animation-delay: 0s; }
        .shape2 { width: 200px; height: 200px; bottom: 15%; right: 12%; animation-delay: 2s; }
        .shape3 { width: 80px; height: 80px; bottom: 30%; left: 20%; animation-delay: 4s; }

        @keyframes float {
            0% { transform: translateY(0px) rotate(0deg); }
            100% { transform: translateY(-30px) rotate(360deg); }
        }

        .glass-card {
            backdrop-filter: blur(16px);
            background-color: rgba(255, 255, 255, 0.15);
            box-shadow: 0 8px 32px rgba(0, 0, 0, 0.25);
            border-radius: 20px;
            padding: 50px 40px;
            width: 100%;
            max-width: 420px;
            z-index: 2;
            animation: fadeSlideIn 1.2s ease;
        }

        @keyframes fadeSlideIn {
            0% { transform: translateY(-60px); opacity: 0; }
            100% { transform: translateY(0); opacity: 1; }
        }

        h1 {
            text-align: center;
            color: #fff;
            font-size: 32px;
            font-weight: 700;
            margin-bottom: 30px;
            animation: popIn 1s ease;
        }

        @keyframes popIn {
            0% { transform: scale(0.8); opacity: 0; }
            100% { transform: scale(1); opacity: 1; }
        }

        .form-control {
            border-radius: 12px;
            padding: 12px;
            border: none;
            margin-bottom: 20px;
            background-color: rgba(255, 255, 255, 0.8);
            transition: transform 0.2s ease;
        }

        .form-control:focus {
            transform: scale(1.02);
            box-shadow: 0 0 12px rgba(0, 123, 255, 0.3);
        }

        .btn-login {
            background: linear-gradient(to right, #667eea, #764ba2);
            color: #fff;
            padding: 12px;
            border-radius: 12px;
            border: none;
            width: 100%;
            font-weight: bold;
            transition: all 0.3s ease-in-out;
        }

        .btn-login:hover {
            background: linear-gradient(to right, #764ba2, #667eea);
            transform: scale(1.05);
        }

        .text-link {
            text-align: center;
            margin-top: 15px;
            font-size: 14px;
        }

        .text-link a {
            color: #fff;
            font-weight: bold;
            text-decoration: none;
            transition: color 0.3s ease;
        }

        .text-link a:hover {
            color: #ffdd57;
        }

        .mensaje {
            margin-top: 20px;
            text-align: center;
            padding: 12px;
            border-radius: 10px;
            background: rgba(255, 0, 0, 0.7);
            color: white;
            animation: fadeIn 0.8s ease;
        }

        @keyframes fadeIn {
            from { opacity: 0; }
            to { opacity: 1; }
        }
    </style>
</head>
<body>

    <!-- Fondos animados -->
    <div class="floating-shape shape1"></div>
    <div class="floating-shape shape2"></div>
    <div class="floating-shape shape3"></div>

    <!-- Card con glassmorphism -->
    <div class="glass-card">
        <h1>Bienvenido 👋</h1>

        <!-- Formulario de login corregido -->
        <form action="/anonimo/formularioLogin" method="post">
            <input type="text" name="username" class="form-control" placeholder="Correo o usuario" required />
            <input type="password" name="password" class="form-control" placeholder="Contraseña" required />
            <button type="submit" class="btn btn-login">Iniciar sesión</button>
        </form>

        <!-- Mensaje en caso de error al iniciar sesión -->
        <c:if test="${param.error == 'true'}">
            <p class="mensaje">Usuario o contraseña incorrectos. Intenta nuevamente.</p>
        </c:if>

        <!-- Redirección al registro -->
        <div class="text-link">
            ¿No tienes cuenta? <a href="/anonimo/loginRegistro?source=login">Regístrate aquí</a>
        </div>

        <!-- Otros mensajes del controlador -->
        <c:if test="${not empty mensaje}">
            <p class="mensaje"><c:out value="${mensaje}" /></p>
        </c:if>
    </div>

</body>
</html>
