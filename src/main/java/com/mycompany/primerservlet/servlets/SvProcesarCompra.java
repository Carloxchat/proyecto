package com.mycompany.primerservlet.servlets;

import com.mycompany.primerservlet.model.Carrito;
import com.mycompany.primerservlet.model.Producto;
import com.mycompany.primerservlet.util.DatabaseConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/SvProcesarCompra")
public class SvProcesarCompra extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("dni") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Carrito carrito = (Carrito) session.getAttribute("carrito");
        if (carrito == null || carrito.getProductos().isEmpty()) {
            response.sendRedirect("productos.jsp");
            return;
        }

        int usuarioId = (int) session.getAttribute("id");
        guardarComprasEnBaseDeDatos(usuarioId, carrito);

        session.removeAttribute("carrito");
        response.sendRedirect("confirmacion.jsp");
    }

    private void guardarComprasEnBaseDeDatos(int usuarioId, Carrito carrito) {
        String sql = "INSERT INTO compras (usuario_id, producto_id, cantidad) VALUES (?, ?, ?)";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            for (Producto producto : carrito.getProductos()) {
                preparedStatement.setInt(1, usuarioId);
                preparedStatement.setInt(2, producto.getId());
                preparedStatement.setInt(3, producto.getCantidad());
                preparedStatement.addBatch();
            }
            
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
