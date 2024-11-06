<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Acceso Denegado</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/accesoDenegado.css">
</head>
<body>
    
    <div class="container">
        <h1>Acceso Denegado</h1>
        <p>No tienes permiso para realizar esta acción.</p>
        <a href="productos.jsp">Volver a Productos</a>
    </div>
</body>
</html>
