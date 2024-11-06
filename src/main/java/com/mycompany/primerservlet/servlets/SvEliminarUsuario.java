package com.mycompany.primerservlet.servlets;

import com.mycompany.primerservlet.repository.UsuarioRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "SvEliminarUsuario", urlPatterns = {"/SvEliminarUsuario"})
public class SvEliminarUsuario extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String currentUserRole = (String) session.getAttribute("rol");
        
        if (currentUserRole == null || !currentUserRole.equals("superadmin")) {
            response.sendRedirect("accesoDenegado.jsp");
            return;
        }

        int id = Integer.parseInt(request.getParameter("id"));
        UsuarioRepository repo = new UsuarioRepository();

        try {
            repo.eliminarUsuario(id);
            response.sendRedirect("SvConsultaUsuarios");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para eliminar usuarios";
    }
}
