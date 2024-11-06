<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mycompany.primerservlet.model.Usuario" %>

<%
    HttpSession currentSession = request.getSession(false);
    if (currentSession == null || currentSession.getAttribute("rol") == null || !currentSession.getAttribute("rol").equals("superadmin")) {
        response.sendRedirect("accesoDenegado.jsp");
        return;
    }
    List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Consulta de Usuarios</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/consultaUsuarios.css">
</head>
<body>
    
    <div class="container">
        <h1>Lista de Usuarios</h1>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>DNI</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Teléfono</th>
                    <th>Rol</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <% if (usuarios != null) {
                    for (Usuario usuario : usuarios) { %>
                        <tr>
                            <td><%= usuario.getId() %></td>
                            <td><%= usuario.getDni() %></td>
                            <td><%= usuario.getNombre() %></td>
                            <td><%= usuario.getApellido() %></td>
                            <td><%= usuario.getTelefono() %></td>
                            <td>
                                <% if (!"superadmin".equals(usuario.getRol())) { %> <!-- Asegurarse de que el rol de superadmin no sea modificable -->
                                    <form action="SvModificarRol" method="post" style="display:inline;">
                                        <input type="hidden" name="id" value="<%= usuario.getId() %>">
                                        <select name="rol">
                                            <option value="user" <% if("user".equals(usuario.getRol())) { %>selected<% } %>>Usuario</option>
                                            <option value="admin" <% if("admin".equals(usuario.getRol())) { %>selected<% } %>>Administrador</option>
                                        </select>
                                        <input type="submit" value="Cambiar Rol">
                                    </form>
                                <% } else { %>
                                    Superadmin
                                <% } %>
                            </td>
                            <td>
                                <a href="SvEditarUsuario?id=<%= usuario.getId() %>">Editar</a>
                                <form action="SvEliminarUsuario" method="post" style="display:inline;">
                                    <input type="hidden" name="id" value="<%= usuario.getId() %>">
                                    <input type="submit" value="Eliminar">
                                </form>
                            </td>
                        </tr>
                    <% }
                } %>
            </tbody>
        </table>
        <a href="productos.jsp">Volver a Productos</a>
    </div>
</body>
</html>
