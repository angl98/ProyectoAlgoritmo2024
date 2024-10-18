/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoalgoritmo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author angel
 */
public class Producto {
   
        public static void producto() throws IOException{
        Scanner scan = new Scanner(System.in);
        boolean salir = false;
        
        while(!salir){
        System.out.println("[1] Crear Categorias: ");
        System.out.println("[2] Agregar producto: ");
        System.out.println("[3] Dar  de alta o baja un producto: ");
        System.out.println("[4] Modificar producto: ");
        System.out.println("[5] Salir");
        
        int Produ = scan.nextInt();
        
        switch(Produ){
            case 1:
               CrearCate(scan);
               break;
            case 2:
                AgregarProducto(scan);
            case 5:
                salir= true;
                System.out.println("Saliendo al menu principal ");
                break;
                    }
                    }
           
        Administrador.menuAdmin(scan);
    }
    public static void CrearCate(Scanner scan) throws IOException{
  
    File Categoria = new File("Categoria.txt");
    
         if (!Categoria.exists()) {
            Categoria.createNewFile();
        }
         //Quitar cualquier salto de linea
         scan.nextLine();
         
        System.out.println("Escriba el nombre de la nueva Categoria: ");
        String NombreCat = scan.nextLine();
        System.out.println("Escriba una descripcion de la categoria: ");
        String DescCat = scan.nextLine();
        
            if (categoriaExiste(Categoria, NombreCat)) {
            System.out.println("La categoria '" + NombreCat + "' ya existe. ");
        } else {
            // Concatenar nombre y descripción
            String CrearCategoria = NombreCat + " | " + DescCat + " | ";

            // Escribir en el archivo
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(Categoria, true))) {
                writer.write(CrearCategoria);
                writer.newLine();
                System.out.println("//Categoria ingresada con exito//");
                                                                                              }
                }
    }
            
            public static boolean categoriaExiste(File Categoria, String nombreCat) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(Categoria))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                
                String[] datos = linea.split("\\|"); 
                if (datos[0].trim().equalsIgnoreCase(nombreCat.trim())) {
                    return true; 
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return false;
    }

            
                public static void AgregarProducto(Scanner scan) throws IOException {
        File Producto = new File("Producto.txt");

        if (!Producto.exists()) {
            Producto.createNewFile();
        }

        // Matriz para almacenar la inf
        String[][] productoData = new String[1][6];  // 1 producto, 6 características
        scan.nextLine();
        // Leer la información del producto
        System.out.println("Escriba el Nombre del producto: ");
        productoData[0][0] = scan.nextLine();

        System.out.println("Escriba la descripcion del producto: ");
        productoData[0][1] = scan.nextLine();

        System.out.println("Seleccione una categoria para el producto: ");
        mostrarCategorias();  // Mostrar las categorías disponibles

        System.out.println("Ingrese la categoria: ");
        productoData[0][2] = scan.nextLine();

        System.out.println("Escriba el color del producto: ");
        productoData[0][3] = scan.nextLine();

        System.out.println("Escriba la talla del producto: ");
        productoData[0][4] = scan.nextLine();

        System.out.println("Escriba el precio del producto: ");
        productoData[0][5] = scan.nextLine();

          String codigoUnico;
        boolean codigoExiste;
        do {
            System.out.println("Escriba un codigo unico para el producto: ");
            codigoUnico = scan.nextLine();

            // Verificar si el code ya existe en el archivo
            codigoExiste = false;
            try (BufferedReader reader = new BufferedReader(new FileReader(Producto))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    if (linea.startsWith("Codigo: " + codigoUnico)) {
                        System.out.println("El codigo '" + codigoUnico + "' ya existe, ingrese otro.");
                        codigoExiste = true;
                        break;  // Salir del bucle si se encuentra el código
                    }
                }
            }
        } while (codigoExiste);

        System.out.println("Codigo unico asignado: " + codigoUnico);

        // Guardar en el archivo de productos
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Producto, true))) {
            writer.write("Codigo " + codigoUnico + " | Nombre: " + productoData[0][0] +
                    " | Descripcion: " + productoData[0][1] +
                    " | Categoria: " + productoData[0][2] +
                    " | Color: " + productoData[0][3] +
                    " | Talla: " + productoData[0][4] +
                    " | Precio: " + productoData[0][5]);
            writer.newLine();
            System.out.println("//Producto agregado con éxito//");
        }
        
                                                                                }
        

    // metodo para mostrar todas las categorias disponibles
    public static void mostrarCategorias() throws IOException {
        File Categoria = new File("Categoria.txt");

        if (!Categoria.exists() || Categoria.length() == 0) {
            System.out.println("--No hay categorais disponibles-- Cree una nueva .");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(Categoria))) {
            String linea;
            System.out.println("Categorias disponibles: ");
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split("\\|");
                System.out.println("- " + datos[0].trim());  // Mostrar solo el nombre de la categorias
            }
        }
    }
            
           
}
            






                    
            
        


   
    

