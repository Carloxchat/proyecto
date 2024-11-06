<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.mycompany.primerservlet.repository.ProductoRepository, com.mycompany.primerservlet.model.Producto, jakarta.servlet.http.HttpSession" %>
<jsp:include page="header.jsp" />

<%
    HttpSession currentSession = request.getSession(false);
    boolean isLoggedIn = (currentSession != null && currentSession.getAttribute("dni") != null);
    String userRole = currentSession != null ? (String) currentSession.getAttribute("rol") : null;
    ProductoRepository productoRepository = new ProductoRepository();
    List<Producto> productos = productoRepository.obtenerTodosLosProductos();
%>

<!DOCTYPE html>
<html>
<head>
    <title>Lista de Productos</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/productos.css">
</head>
<body>
    
    <h1>Productos Disponibles</h1>
    <ul>
        <% for (Producto producto : productos) {
            String nombreProducto = producto.getNombre();
            String descripcionProducto = producto.getDescripcion();
            double precioProducto = producto.getPrecio();
            String fotoProducto = producto.getFoto();
        %>
        <li>
            <img src="<%= request.getContextPath() + "/" + fotoProducto %>" alt="<%= nombreProducto %>"><br>
            <%= nombreProducto %> - $<%= precioProducto %><br>
            <%= descripcionProducto %><br>
            <% if (isLoggedIn) { %>
                <form action="SvCompra" method="post">
                    <input type="hidden" name="producto" value="<%= producto.getId() %>">
                    <input type="submit" value="Comprar">
                </form>
            <% } else { %>
                <a href="login.jsp">Iniciar Sesión para Comprar</a>
            <% } %>
        </li>
        <% } %>
    </ul>
    <% if ("admin".equals(userRole) || "superadmin".equals(userRole)) { %>
    <h2>Agregar Nuevo Producto</h2>
    <form action="SvAgregarProducto" method="post" enctype="multipart/form-data">
        Nombre: <input type="text" name="nombre" required><br>
        Descripción: <input type="text" name="descripcion" required><br>
        Precio: <input type="text" name="precio" required><br>
        Foto: <input type="file" name="foto" accept="image/*" required><br>
        <input type="submit" value="Agregar Producto">
    </form>
    <% } %>
</body>

</html>
