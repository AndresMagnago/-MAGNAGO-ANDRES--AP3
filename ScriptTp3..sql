-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS todo_sport_db;
USE todo_sport_db;

-- Crear la tabla productos
CREATE TABLE IF NOT EXISTS productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio DOUBLE NOT NULL,
    stock INT NOT NULL,
    categoria VARCHAR(50),
    tipo VARCHAR(50)  -- e.g., 'Indumentaria' o 'Calzado'
);

-- Insertar datos de ejemplo
INSERT INTO productos (nombre, precio, stock, categoria, tipo) VALUES
('Camiseta Deportiva', 25.99, 50, 'Ropa', 'Indumentaria'),
('Zapatillas Running', 89.99, 30, 'Calzado', 'Calzado');