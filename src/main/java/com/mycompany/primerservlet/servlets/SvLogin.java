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

@WebServlet(name = "SvLogin", urlPatterns = {"/SvLogin"})
public class SvLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dni = request.getParameter("dni");
        String password = request.getParameter("password");
        UsuarioRepository repo = new UsuarioRepository();
        boolean isValidUser = false;
        Usuario usuario = null;

        try {
            isValidUser = repo.validateUser(dni, password);
            if (isValidUser) {
                usuario = repo.findByDni(dni);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }

        if (isValidUser) {
            // Crear una sesión y guardar el ID, DNI y rol del usuario
            HttpSession session = request.getSession();
            if (usuario != null) {
                session.setAttribute("id", usuario.getId());
                session.setAttribute("dni", dni);
                session.setAttribute("rol", usuario.getRol());
            }
            response.sendRedirect("productos.jsp");
        } else {
            response.sendRedirect("loginFallido.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
