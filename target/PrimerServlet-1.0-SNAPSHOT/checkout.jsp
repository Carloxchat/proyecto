<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.mycompany.primerservlet.model.Carrito, jakarta.servlet.http.HttpSession" %>

<%
    HttpSession currentSession = request.getSession(false);
    if (currentSession == null || currentSession.getAttribute("dni") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    Carrito carrito = (Carrito) currentSession.getAttribute("carrito");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Checkout</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/checkout.css">
</head>
<body>
    
    <div class="container">
        <h1>Checkout</h1>
        <form action="SvProcesarCompra" method="post">
            <h2>Total: $<%= carrito != null ? carrito.getTotal() : 0 %></h2>
            <input type="submit" value="Confirmar Compra">
        </form>
    </div>
</body>
</html>
