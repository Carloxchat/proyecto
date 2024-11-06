package com.mycompany.primerservlet.servlets;

import com.mycompany.primerservlet.model.Producto;
import com.mycompany.primerservlet.repository.ProductoRepository;  // Asegúrate de que el paquete sea correcto
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet("/SvAgregarProducto")
@MultipartConfig
public class SvAgregarProducto extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String currentUserRole = (String) session.getAttribute("rol");
        
        if (currentUserRole == null || (!currentUserRole.equals("admin") && !currentUserRole.equals("superadmin"))) {
            response.sendRedirect("accesoDenegado.jsp");
            return;
        }

        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String precioStr = request.getParameter("precio");
        double precio;
        
        try {
            precio = Double.parseDouble(precioStr);
        } catch (NumberFormatException e) {
            response.getWriter().println("Formato de precio inválido.");
            return;
        }

        Part filePart = request.getPart("foto");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
        
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        
        String filePath = uploadPath + File.separator + fileName;
        filePart.write(filePath);
        
        String fotoPath = "uploads/" + fileName;
        
        ProductoRepository productoRepository = new ProductoRepository();
        Producto producto = new Producto(0, nombre, descripcion, precio, fotoPath);  // Usar el constructor correcto
        productoRepository.addProducto(producto);
        
        response.sendRedirect("productos.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Servlet para agregar productos";
    }
}
