<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.mycompany.primerservlet.model.Carrito, com.mycompany.primerservlet.model.Producto, jakarta.servlet.http.HttpSession" %>

<%
    HttpSession currentSession = request.getSession(false);
    Carrito carrito = (Carrito) currentSession.getAttribute("carrito");
    boolean isLoggedIn = (currentSession != null && currentSession.getAttribute("dni") != null);
%>
<!DOCTYPE html>
<html>
<head>
    <title>Carrito de Compras</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/verCarrito.css">
</head>
<body>
    
    <div class="container">
        <h1>Carrito de Compras</h1>
        <ul>
            <% if (carrito != null) {
                for (Producto producto : carrito.getProductos()) {
                    String nombreProducto = producto.getNombre();
                    String descripcionProducto = producto.getDescripcion();
                    double precioProducto = producto.getPrecio();
                    int cantidadProducto = producto.getCantidad();
            %>
                <li>
                    <%= nombreProducto %> - $<%= precioProducto %> x <%= cantidadProducto %> = $<%= producto.getTotal() %><br>
                    <%= descripcionProducto %>
                    <form action="SvEliminarProducto" method="post" style="display:inline;">
                        <input type="hidden" name="id" value="<%= producto.getId() %>">
                        <input type="submit" value="Eliminar">
                    </form>
                </li>
            <% }
            } else { %>
                <li>El carrito está vacío.</li>
            <% } %>
        </ul>
        <h2>Total: $<%= carrito != null ? carrito.getTotal() : 0 %></h2>
        <% if (isLoggedIn) { %>
            <a href="checkout.jsp" class="button">Proceder al Pago</a>
        <% } else { %>
            <a href="login.jsp" class="button">Iniciar Sesión para Proceder al Pago</a>
        <% } %>
    </div>
</body>
</html>
