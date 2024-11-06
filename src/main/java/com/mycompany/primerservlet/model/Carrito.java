package com.mycompany.primerservlet.model;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private List<Producto> productos;

    public Carrito() {
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        for (Producto p : productos) {
            if (p.getId() == producto.getId()) {
                p.setCantidad(p.getCantidad() + 1);
                return;
            }
        }
        productos.add(producto);
    }

    public void eliminarProducto(int id) {
        productos.removeIf(p -> p.getId() == id);
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public double getTotal() {
        double total = 0;
        for (Producto p : productos) {
            total += p.getTotal();
        }
        return total;
    }
}
