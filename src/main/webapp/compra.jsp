<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
    HttpSession currentSession = request.getSession(false);
    if (currentSession == null || currentSession.getAttribute("dni") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Comprar Producto</title>
</head>
<body>
    
    <h1>Compra de Producto</h1>
    <p>¡Gracias por tu compra, <%= currentSession.getAttribute("dni") %>!</p>
</body>
</html>
