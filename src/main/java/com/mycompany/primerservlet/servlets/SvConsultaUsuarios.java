package com.mycompany.primerservlet.servlets;

import com.mycompany.primerservlet.model.Usuario;
import com.mycompany.primerservlet.repository.UsuarioRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "SvConsultaUsuarios", urlPatterns = {"/SvConsultaUsuarios"})
public class SvConsultaUsuarios extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession currentSession = request.getSession(false);
        if (currentSession == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String role = (String) currentSession.getAttribute("rol");
      

        UsuarioRepository repo = new UsuarioRepository();
        List<Usuario> usuarios = null;
        try {
            usuarios = repo.obtenerTodosLosUsuarios();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
        request.setAttribute("usuarios", usuarios);
        request.getRequestDispatcher("consultaUsuarios.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet para consultar todos los usuarios";
    }
}
