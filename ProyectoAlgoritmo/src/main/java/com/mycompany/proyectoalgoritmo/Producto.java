
package com.mycompany.proyectoalgoritmo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import static java.util.stream.Gatherers.scan;

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
        System.out.println("[3] Dar baja un producto: ");
        System.out.println("[4] Modificar producto: ");
        System.out.println("[5] Salir");
        
        int Produ = scan.nextInt();
        scan.nextLine(); //quitar cualquier salto de linea
        
        switch(Produ){
            case 1:
               CrearCate(scan);
               break;
            case 2:
                AgregarProducto(scan);
                break;
            case 3:
                DarDeBaja(scan);
                break;
            case 4: 
                ModProducto(scan);
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
        // Mostrar las categorías disponibles
        mostrarCategorias();  

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
            writer.write(codigoUnico + " | Nombre: " + productoData[0][0] +
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
    
    
    
            
         public static void DarDeBaja(Scanner scan) throws FileNotFoundException, IOException{
             File Producto = new File("Producto.txt");
             File ArchTemp = new File("ArchTemp.txt");
             
             if (!Producto.exists()){
                System.out.println("No hay Productos Disponibles.");
                return;
                                     }  
             System.out.println("Ingrese el codigo del producto que quiere dar de baja: ");
             String code = scan.nextLine().trim();
             
                try (BufferedReader reader = new BufferedReader(new FileReader(Producto))){
                    BufferedWriter writer = new BufferedWriter(new FileWriter(ArchTemp));
                        
                    String linea;
                    
                    boolean buscado = false;
                    
                    while((linea = reader.readLine()) != null){
                        if(linea.startsWith("Codigo: "+ code)){
                            System.out.println("Producto con codigo: "+code + "--Esta dado de baja--");
                            buscado = true;
                        }
                        else{
                            writer.write(linea);
                            writer.newLine();
                        }
                }
                    if (!buscado) {
        System.out.println("--Producto no encontrado--");
        // Eliminar el archivo temporal porque no se usará
        ArchTemp.delete();
    } else {
        // Reemplazar el archivo original por el temporal
        if (Producto.delete() && ArchTemp.renameTo(Producto)) {
            System.out.println("--Producto dado de baja exitosamente--");
        } else {
            System.out.println("--Error al dar de baja el producto--");
        }
    }
         }
}                
         
         
         
         
         public static void ModProducto (Scanner scan) throws IOException{
             
                File Producto = new File("Producto.txt");
                File ArchTemp = new File("ArchTemp");
                
            if(!Producto.exists()){
                System.out.println("No hay producto dispobible");
                return;
            }
            System.out.println("Productos disponibles: ");
              try (BufferedReader reader = new BufferedReader(new FileReader(Producto))){
                  String linea;
                  while((linea= reader.readLine()) !=null){
                      System.out.println(linea);
                  }
              }
            
            System.out.println("Ingrese el codigo del producto que desea Modificar: ");
                String CodeProdu = scan.nextLine();
                
                //almacenar 1 producto como maximo de 6 caracteristicas(digitos)
                
                String[][] productoInf = new String[1][6];
                
               try (BufferedReader reader = new BufferedReader(new FileReader(Producto))){
                    BufferedWriter writer = new BufferedWriter(new FileWriter(ArchTemp));
                    
                    String linea;
                    boolean buscado = false;
                    
                        while((linea= reader.readLine()) !=null){
                            if(linea.startsWith("El Codigo: "+ CodeProdu)){
                                buscado = false;
                                System.out.println("Ingrese los nuevos datos del producto: ");
                                
                                System.out.println("Nuevo nombre: ");
                                productoInf[0][0]= scan.nextLine();
                                System.out.println("Nueva Desc: ");
                                productoInf[0][1]= scan.nextLine();
                                System.out.println("Nueva categoria: ");
                                productoInf[0][2]= scan.nextLine();
                                System.out.println("Nuevo color: ");
                                productoInf[0][3]= scan.nextLine();
                                System.out.println("Nueva talla: ");
                                productoInf[0][4]= scan.nextLine();
                                System.out.println("Nuevo precio: ");
                                productoInf[0][5]= scan.nextLine();
                                
                                //Escribir lo leido para el archivo temporal
                                writer.write("| Codigo: "+ CodeProdu+ "| Nombre: "+ productoInf[0][0]+ "| Descripcion: "+ productoInf[0][1]+ "|Categoria: "+ productoInf[0][2]+"| Color: "+ productoInf[0][3]+"| Talla: "+ productoInf[0][4]+"| Precio: "+ productoInf[0][5]);
                                writer.newLine();
                            } else{
                                writer.write(linea);
                                writer.newLine();
                            }
                        }
                                if(!buscado){
                                    System.out.println("--Producto no encontrado--");
                                }
               }
               
               //cambiar el archivo temporal por el original.
               
               if(Producto.delete()&& ArchTemp.renameTo(Producto)){
                   System.out.println("--El producto fue modificado exitosamente--");                   
               } else {
                   System.out.println("--Error al modificar el producto--");

               }

            
         }
               
}
            






                    
            
        


   
    

