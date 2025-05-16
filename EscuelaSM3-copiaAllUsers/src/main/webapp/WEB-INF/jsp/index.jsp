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

    <!-- Anime.js -->
    <script src="https://cdn.jsdelivr.net/npm/animejs@3.2.1/lib/anime.min.js"></script>

    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            overflow: hidden;
            background: #FF7F50;
            transition: background 6s ease-in-out;
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

        h2 {
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
            transition: transform 0.2s ease, box-shadow 0.3s ease;
        }

        .form-control:focus {
            transform: scale(1.02);
            box-shadow: 0 0 12px rgba(0, 123, 255, 0.3);
        }

        .btn-primary {
            background: linear-gradient(to right, #FF6347, #FF4500);
            border: none;
            padding: 12px;
            border-radius: 12px;
            font-weight: bold;
            width: 100%;
            transition: transform 0.3s ease-in-out, background 0.3s ease;
            animation: buttonAnimation 4s ease-in-out infinite;
        }

        @keyframes buttonAnimation {
            0% {
                transform: scale(1);
                background: linear-gradient(to right, #FF6347, #FF4500);
            }
            50% {
                transform: scale(1.1);
                background: linear-gradient(to right, #FF4500, #FF6347);
            }
            100% {
                transform: scale(1);
                background: linear-gradient(to right, #FF6347, #FF4500);
            }
        }

        .btn-primary:hover {
            transform: scale(1.2);
            background: linear-gradient(to right, #FF4500, #FF6347);
            animation: none;
        }

        .link {
            color: #FF6347;
            font-weight: bold;
            text-decoration: none;
            transition: color 0.3s ease;
        }

        .link:hover {
            text-decoration: underline;
            color: #FF4500;
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

    <!-- Formulario -->
    <div class="glass-card">
        <h2>Registro de Usuario</h2>
        <form action="/anonimo/formularioRegistro" method="post">
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email" required placeholder="Introduce tu correo electrónico">
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Contraseña</label>
                <input type="password" class="form-control" id="password" name="password" required placeholder="Crea una contraseña">
            </div>
            <button type="submit" class="btn btn-primary">Registrar</button>
        </form>

        <div class="text-center mt-3">
            <p>¿Ya tienes una cuenta? <a href="/anonimo/loginRegistro?source=registro" class="link">Inicia sesión aquí</a></p>
        </div>

        <c:if test="${not empty mensaje}">
            <div class="mensaje">
                <c:out value="${mensaje}" />
            </div>
        </c:if>
    </div>

    <script>
        anime({
            targets: 'body',
            background: [
                { value: 'linear-gradient(135deg, #FF7F50, #FF4500)', duration: 0 },
                { value: 'linear-gradient(135deg, #FF6347, #FF8C00)', duration: 3000 }
            ],
            easing: 'easeInOutSine',
            duration: 3000,
            loop: true
        });
    </script>
</body>
</html>
