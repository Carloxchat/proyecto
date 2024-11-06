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

@WebServlet(name = "SvEditarUsuario", urlPatterns = {"/SvEditarUsuario"})
public class SvEditarUsuario extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession currentSession = request.getSession(false);
        if (currentSession == null || !"superadmin".equals(currentSession.getAttribute("rol"))) {
            response.sendRedirect("accesoDenegado.jsp");
            return;
        }

        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            response.sendRedirect("SvConsultaUsuarios"); // Redirige a una página adecuada si el ID es nulo
            return;
        }

        int id = Integer.parseInt(idParam);
        UsuarioRepository usuarioRepository = new UsuarioRepository();
        Usuario usuario = null;

        try {
            usuario = usuarioRepository.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }

        if (usuario != null) {
            request.setAttribute("usuario", usuario);
            request.getRequestDispatcher("editarUsuario.jsp").forward(request, response);
        } else {
            response.getWriter().println("Usuario no encontrado");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession currentSession = request.getSession(false);
        if (currentSession == null || !"superadmin".equals(currentSession.getAttribute("rol"))) {
            response.sendRedirect("accesoDenegado.jsp");
            return;
        }

        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            response.sendRedirect("SvConsultaUsuarios"); // Redirige a una página adecuada si el ID es nulo
            return;
        }

        int id = Integer.parseInt(idParam);
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String telefono = request.getParameter("telefono");
        String password = request.getParameter("password");

        UsuarioRepository usuarioRepository = new UsuarioRepository();
        Usuario usuario = null;

        try {
            usuario = usuarioRepository.findById(id);
            if (usuario != null) {
                usuario.setNombre(nombre);
                usuario.setApellido(apellido);
                usuario.setTelefono(telefono);
                usuario.setPassword(password);
                usuarioRepository.update(usuario);
                response.sendRedirect("SvConsultaUsuarios");
            } else {
                response.getWriter().println("Usuario no encontrado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para editar usuarios";
    }
}
