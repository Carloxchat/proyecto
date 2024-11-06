<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/login.css">
</head>
<body>
    
    <div class="container">
        <h1>Login de Usuario</h1>
        <form action="SvLogin" method="post">
            DNI: <input type="text" name="dni" placeholder="Ingresa tu DNI" required><br>
            Contraseña: <input type="password" name="password" placeholder="Ingresa tu contraseña" required><br>
            <input type="submit" value="Login">
        </form>
    </div>
</body>
</html>
