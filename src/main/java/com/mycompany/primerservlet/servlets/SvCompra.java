package com.mycompany.primerservlet.servlets;

import com.mycompany.primerservlet.model.Carrito;
import com.mycompany.primerservlet.model.Producto;
import com.mycompany.primerservlet.repository.ProductoRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/SvCompra")
public class SvCompra extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("dni") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        int productoId = Integer.parseInt(request.getParameter("producto"));
        ProductoRepository productoRepository = new ProductoRepository();
        Producto producto = productoRepository.findById(productoId);

        Carrito carrito = (Carrito) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new Carrito();
            session.setAttribute("carrito", carrito);
        }

        if (producto != null) {
            carrito.agregarProducto(producto);
        }

        response.sendRedirect("verCarrito.jsp");
    }
}
