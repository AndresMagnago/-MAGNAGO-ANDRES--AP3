package com.todosport;

public class Calzado extends Producto {
    private int numero;  

    public Calzado(int id, String nombre, double precio, int stock, String categoria, int numero) {
        super(id, nombre, precio, stock, categoria);
        this.numero = numero;
    }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    @Override
    public void mostrarInfo() {
        System.out.println("Calzado: " + getNombre() + ", Precio: $" + getPrecio() + ", Stock: " + getStock() + ", Número: " + numero);
    }
}
