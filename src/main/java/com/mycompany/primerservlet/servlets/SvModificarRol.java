package com.mycompany.primerservlet.servlets;

import com.mycompany.primerservlet.repository.UsuarioRepository;
import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "SvModificarRol", urlPatterns = {"/SvModificarRol"})
public class SvModificarRol extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String currentUserRole = (String) session.getAttribute("rol");
        
        if (currentUserRole == null || !currentUserRole.equals("superadmin")) {
            response.sendRedirect("accesoDenegado.jsp");
            return;
        }

        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            response.sendRedirect("consultaUsuarios.jsp"); // O redirige a una página de error apropiada
            return;
        }

        int id = Integer.parseInt(idParam);
        String nuevoRol = request.getParameter("rol");
        UsuarioRepository repo = new UsuarioRepository();

        try {
            repo.actualizarRol(id, nuevoRol);
            response.sendRedirect("SvConsultaUsuarios");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para modificar roles de usuarios";
    }
}
