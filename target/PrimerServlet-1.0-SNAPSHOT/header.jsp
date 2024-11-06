<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Mi Página Web</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/styles.css">
</head>
<body>
    <header>
        <nav>
            <ul >
                <li><a  href="<%= request.getContextPath() %>/index.jsp">Inicio</a></li>
                <li><a  href="<%= request.getContextPath() %>/productos.jsp">Productos</a></li>
                <li><a  href="<%= request.getContextPath() %>/verCarrito.jsp">Carrito</a></li>
                <li><a  href="<%= request.getContextPath() %>/SvConsultaUsuarios">Usuarios</a></li>
                <li><a  href="<%= request.getContextPath() %>/contacto.jsp">Contacto</a></li>
            </ul>
        </nav>
    </header>
</body>
</html>
