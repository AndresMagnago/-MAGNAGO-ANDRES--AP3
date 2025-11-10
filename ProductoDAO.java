package com.todosport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductoDAO {

    public ArrayList<Producto> obtenerProductos() {
        ArrayList<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM producto";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
        
                Producto p = new Indumentaria(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("stock"),
                        rs.getString("categoria"),
                        "General"
                );
                productos.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener productos: " + e.getMessage());
        }
        return productos;
    }
}

