package com.todosport;

public class Indumentaria extends Producto {
    private String talla; 

    public Indumentaria(int id, String nombre, double precio, int stock, String categoria, String talla) {
        super(id, nombre, precio, stock, categoria);
        this.talla = talla;
    }

    public String getTalla() { return talla; }
    public void setTalla(String talla) { this.talla = talla; }

    @Override
    public void mostrarInfo() {
        System.out.println("Indumentaria: " + getNombre() + ", Precio: $" + getPrecio() + ", Stock: " + getStock() + ", Talla: " + talla);
    }
}
