<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<jsp:include page="header.jsp" />
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
    <title>Confirmación de Compra</title>
</head>
<body>
    
    <h1>Compra Confirmada</h1>
    <p>¡Gracias por tu compra, <%= currentSession.getAttribute("dni") %>!</p>
    <a href="productos.jsp">Seguir Comprando</a>
</body>
</html>

