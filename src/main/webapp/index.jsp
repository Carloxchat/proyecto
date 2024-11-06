<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Registro de Usuario</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/index.css">
</head>
<body>
    
    <div class="container">
        <h1>Registrar Usuario</h1>
        <form action="SvUsuarios" method="post">
            DNI: <input type="text" name="dni" required><br>
            Nombre: <input type="text" name="nombre" required><br>
            Apellido: <input type="text" name="apellido" required><br>
            Teléfono: <input type="tel" name="telefono" required><br>
            Contraseña: <input type="password" name="password" required><br>
            <input type="submit" value="Registrar">
        </form>
    </div>
</body>
</html>
