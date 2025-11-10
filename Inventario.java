package com.todosport;

import java.sql.*;
import java.util.ArrayList;

public class Inventario {
    private ArrayList<Producto> productos = new ArrayList<>();
    private Connection conn;

    public Inventario() {
        conectarDB();
        cargarProductosDesdeDB();
    }

    private void conectarDB() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo_sport_db", "root", "");  // Cambia credenciales
            System.out.println("Conexión a DB exitosa.");
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
    }

    public void agregarProducto(Producto p) {
        productos.add(p);
        guardarProductoEnDB(p);
    }

    public void mostrarInventario() {
        for (Producto p : productos) {
            p.mostrarInfo();
        }
    }

    public Producto buscarPorId(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    // CRUD: Guardar en DB
    private void guardarProductoEnDB(Producto p) {
        String sql = "INSERT INTO productos (nombre, precio, stock, categoria, tipo) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, p.getNombre());
            stmt.setDouble(2, p.getPrecio());
            stmt.setInt(3, p.getStock());
            stmt.setString(4, p.getCategoria());
            stmt.setString(5, p instanceof Indumentaria ? "Indumentaria" : "Calzado");
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) p.setId(rs.getInt(1));
        } catch (SQLException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    // Cargar desde DB
    private void cargarProductosDesdeDB() {
        String sql = "SELECT * FROM productos";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                int stock = rs.getInt("stock");
                String categoria = rs.getString("categoria");
                String tipo = rs.getString("tipo");
                Producto p;
                if (tipo.equals("Indumentaria")) {
                    p = new Indumentaria(id, nombre, precio, stock, categoria, "M");  // Talla por defecto
                } else {
                    p = new Calzado(id, nombre, precio, stock, categoria, 42);  // Número por defecto
                }
                productos.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar: " + e.getMessage());
        }
    }

    // Actualizar stock
    public void actualizarStock(int id, int nuevoStock) {
        String sql = "UPDATE productos SET stock = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, nuevoStock);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            Producto p = buscarPorId(id);
            if (p != null) p.setStock(nuevoStock);
        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
        }
    }
}
