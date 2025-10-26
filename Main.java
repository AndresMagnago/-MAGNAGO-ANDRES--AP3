package com.todosport;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Inventario inventario = new Inventario();
        Venta venta = new Venta(inventario);
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 4) {
            System.out.println("\nMenú:");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Visualizar Inventario");
            System.out.println("3. Procesar Venta");
            System.out.println("4. Salir");
            System.out.print("Elija opción: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine();  // Limpiar buffer

                switch (opcion) {
                    case 1:
                        // Agregar ejemplo (puedes expandir con inputs)
                        Producto nuevo = new Indumentaria(0, "Pantalón Deportivo", 35.99, 20, "Ropa", "L");
                        inventario.agregarProducto(nuevo);
                        break;
                    case 2:
                        inventario.mostrarInventario();
                        break;
                    case 3:
                        System.out.print("ID Producto: ");
                        int id = scanner.nextInt();
                        System.out.print("Cantidad: ");
                        int cant = scanner.nextInt();
                        venta.procesarVenta(id, cant);
                        break;
                    case 4:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un número válido.");
                scanner.nextLine();  // Limpiar input inválido
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
        }
        scanner.close();
    }
}