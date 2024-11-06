<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.mycompany.primerservlet.model.Usuario" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="jakarta.servlet.http.HttpServletRequest" %>
<%@ page import="jakarta.servlet.http.HttpServletResponse" %>

<%
    HttpSession currentSession = request.getSession(false);
    if (currentSession == null || currentSession.getAttribute("rol") == null || !currentSession.getAttribute("rol").equals("superadmin")) {
        response.sendRedirect("accesoDenegado.jsp");
        return;
    }
    Usuario usuario = (Usuario) request.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("consultaUsuarios.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Editar Usuario</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/editarUsuario.css">
</head>
<body>
    
    <div class="container">
        <h1>Editar Usuario</h1>
        <form action="SvEditarUsuario" method="post">
            <input type="hidden" name="id" value="<%= usuario.getId() %>">
            Nombre: <input type="text" name="nombre" value="<%= usuario.getNombre() %>" required><br>
            Apellido: <input type="text" name="apellido" value="<%= usuario.getApellido() %>" required><br>
            Teléfono: <input type="tel" name="telefono" value="<%= usuario.getTelefono() %>" required><br>
            Contraseña: <input type="password" name="password" value="<%= usuario.getPassword() %>" required><br>
            <input type="submit" value="Guardar">
        </form>
    </div>
</body>
</html>
