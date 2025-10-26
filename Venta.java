package com.todosport;

public class Venta {
    private Inventario inventario;

    public Venta(Inventario inventario) {
        this.inventario = inventario;
    }

    public void procesarVenta(int idProducto, int cantidad) {
        Producto p = inventario.buscarPorId(idProducto);
        if (p != null && p.getStock() >= cantidad) {
            p.setStock(p.getStock() - cantidad);
            inventario.actualizarStock(idProducto, p.getStock());
            System.out.println("Venta procesada: " + cantidad + " unidades de " + p.getNombre());
        } else {
            System.out.println("Stock insuficiente o producto no encontrado.");
        }
    }
}