package com.mycompany.primerservlet.servlets;

import com.mycompany.primerservlet.model.Usuario;
import com.mycompany.primerservlet.repository.UsuarioRepository;
import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "SvUsuarios", urlPatterns = {"/SvUsuarios"})
public class SvUsuarios extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String currentUserRole = (String) session.getAttribute("rol");

        if (currentUserRole == null || (!currentUserRole.equals("admin") && !currentUserRole.equals("superadmin"))) {
            response.sendRedirect("accesoDenegado.jsp");
            return;
        }

        String dni = request.getParameter("dni");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String telefono = request.getParameter("telefono");
        String password = request.getParameter("password");
        String rol = request.getParameter("rol") != null && !request.getParameter("rol").isEmpty() ? request.getParameter("rol") : "user";

        Usuario usuario = new Usuario(0, dni, nombre, apellido, telefono, password, rol); // ID inicializado como 0, se generará en la base de datos
        UsuarioRepository repo = new UsuarioRepository();

        try {
            repo.addUsuario(usuario);
            response.sendRedirect("login.jsp");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para agregar usuarios";
    }
}
