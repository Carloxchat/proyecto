package com.mycompany.primerservlet.servlets;

import com.mycompany.primerservlet.model.Carrito;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/SvEliminarProducto")
public class SvEliminarProducto extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("dni") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int id = Integer.parseInt(request.getParameter("id"));
        Carrito carrito = (Carrito) session.getAttribute("carrito");

        if (carrito != null) {
            carrito.eliminarProducto(id);
        }

        response.sendRedirect("verCarrito.jsp");
    }
}
