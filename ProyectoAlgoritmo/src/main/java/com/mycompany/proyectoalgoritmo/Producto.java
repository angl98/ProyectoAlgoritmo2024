
package com.mycompany.proyectoalgoritmo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/**
 *
 * @author angel
 */
public class Producto {
   
      public static void producto(File Categoria, File Producto) throws IOException{
        Scanner scan = new Scanner(System.in);
        boolean salir = false;
        
        while(!salir){
        System.out.println("[1] Crear Categorias: ");
        System.out.println("[2] Agregar producto: ");
        System.out.println("[3] Dar baja un producto: ");
        System.out.println("[4] Modificar producto: ");
        System.out.println("[5] Salir");
        
        int Produ = scan.nextInt();
        switch(Produ){
            case 1:
               CrearCate(scan, Categoria);
               break;
            case 2:
                AgregarProducto(scan, Categoria, Producto);
                break;
            case 3:
                DarDeBaja(scan, Producto);
                break;
            case 4: 
                ModProducto(scan, Producto);
                break;
            case 5:
                salir= true;
                System.out.println("Saliendo al menu principal ");
                break;
                    }
                    }
           
        Administrador.menuAdmin(scan, Categoria, Producto);
    }
        
        
        
        
        
        
    public static void CrearCate(Scanner scan, File Categoria) throws IOException{
    
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

            
            
            
            
            
            
                public static void AgregarProducto(Scanner scan, File Categoria, File Producto) throws IOException {

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
        mostrarCategorias(Categoria);  

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
            writer.write("Codigo: "+ codigoUnico + " | Nombre: " + productoData[0][0] +
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
    public static void mostrarCategorias(File Categoria) throws IOException {

        if (!Categoria.exists() || Categoria.length() == 0) {
            System.out.println("--No hay categorais disponibles-- Cree una nueva .");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(Categoria))) {
            String linea;
            System.out.println("Categorias disponibles: ");
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split("\\|");
                System.out.println("- " + datos[0].trim());  // Mostrar solo el nombre de la categorias y eliminar los espacios en blanco
            }
        }
    }
    
    
    
            
     public static void DarDeBaja(Scanner scan, File Producto) throws IOException {
    File ArchTemp = new File("ArchTemp.txt");

    if (!Producto.exists() && Producto.length() == 0) {
        System.out.println("No hay Productos Disponibles.");
        return;
    }

    System.out.println("Ingrese el codigo del producto que quiere dar de baja: ");
    String codeInput = scan.nextLine().trim();

    // Verificar si el code ingresado es un numero entero
    int code;
    try {
        code = Integer.parseInt(codeInput);
    } catch (NumberFormatException a) {
        System.out.println("El código ingresado no es un numero entero.");
        return;
    }

    boolean buscado = false;

    try (BufferedReader reader = new BufferedReader(new FileReader(Producto));
         BufferedWriter writer = new BufferedWriter(new FileWriter(ArchTemp))) {

        String linea;
        while ((linea = reader.readLine()) != null) {
            // Comparar el codigo
            if (linea.toLowerCase().contains("codigo: " + code)) {
                System.out.println("Producto con codigo: " + code + " --Está dado de baja--");
                buscado = true;
            } else {
                writer.write(linea);
                writer.newLine();
            }
        }
    }

    if (!buscado) {
        System.out.println("--Producto no encontrado--");
        ArchTemp.delete(); // Eliminar el archivo temporal
    } else {
        // Reemplaza el archivo original con el temporal
        if (Producto.delete() && ArchTemp.renameTo(Producto)) {
            System.out.println("--Producto dado de baja exitosamente--");
        } else {
            System.out.println("--Error al dar de baja el producto--");
        }
    }
}
         
         
    
     
     
     
       public static void ModProducto(Scanner scan, File Producto) throws IOException {
    // Verificar si el archivo de productos existe
    if (!Producto.exists()) {
        System.out.println("No hay productos disponibles.");
        return;
    }
    
    // Mostrar productos disponibles
    System.out.println("Productos disponibles:");
    try (BufferedReader reader = new BufferedReader(new FileReader(Producto))) {
        String linea;
        while ((linea = reader.readLine()) != null) {
            System.out.println(linea);
        }
    }

    // Solicitar el código del producto a modificar
    System.out.println("Ingrese el codigo del producto que desea modificar:");
    String codeProdu = scan.nextLine().trim();

    // Archivo temporal para las modificaciones
    File ArchTemp = new File("ArchTemp.txt");
    boolean buscado = false;

    // Abrir el archivo original y el archivo temporal
    try (BufferedReader reader = new BufferedReader(new FileReader(Producto));
         BufferedWriter writer = new BufferedWriter(new FileWriter(ArchTemp))) {
        
        String linea;
        // Leer cada línea del archivo
        while ((linea = reader.readLine()) != null) {
            // Si la línea contiene el código del producto a modificar
            if (linea.startsWith("Codigo: " + codeProdu)) {
                buscado = true;  // Producto encontrado
                
                // Mostrar los datos actuales del producto
                System.out.println("Producto encontrado: " + linea);
                System.out.println("Ingrese los nuevos datos del producto:");

                System.out.print("Nuevo nombre: ");
                String nuevoNombre = scan.nextLine();
                System.out.print("Nueva descripcion: ");
                String nuevaDesc = scan.nextLine();
                System.out.print("Nueva categoria: ");
                String nuevaCategoria = scan.nextLine();
                System.out.print("Nuevo color: ");
                String nuevoColor = scan.nextLine();
                System.out.print("Nueva talla: ");
                String nuevaTalla = scan.nextLine();
                System.out.print("Nuevo precio: ");
                String nuevoPrecio = scan.nextLine();

                // Reescribir la línea del producto con los nuevos datos
                String nuevaLinea = "| Codigo: " + codeProdu + " | Nombre: " + nuevoNombre + " | Descripcion: " + nuevaDesc +
                        " | Categoria: " + nuevaCategoria + " | Color: " + nuevoColor + " | Talla: " + nuevaTalla + " | Precio: " + nuevoPrecio;
                writer.write(nuevaLinea);
                writer.newLine();
            } else {
                // Escribir la línea sin cambios si no es el producto a modificar
                writer.write(linea);
                writer.newLine();
            }
        }
    }

    if (!buscado) {
        System.out.println("--Producto no encontrado--");
        ArchTemp.delete();  // Borrar el archivo temporal si no se encontró el producto
    } else {
        // Reemplazar el archivo original con el archivo temporal
        if (Producto.delete() && ArchTemp.renameTo(Producto)) {
            System.out.println("--El producto fue modificado exitosamente--");
        } else {
            System.out.println("--Error al modificar el producto--");
        }
    }
}
         
         
         
         
         
         public static void entradaProducto(Scanner scan, File Producto) throws IOException {
    System.out.println("Ingrese el codigo del producto para registrar su entrada:");
    String codeInput = scan.nextLine().trim();
    
    int code;
    try {
        code = Integer.parseInt(codeInput);
    } catch (NumberFormatException e) {
        System.out.println("El codigo ingresado no es un numero entero.");
        return;
    }
    
    boolean productoEncontrado = false;
    File ArchTemp = new File("ArchTemp.txt");

    try (BufferedReader reader = new BufferedReader(new FileReader(Producto));
         BufferedWriter writer = new BufferedWriter(new FileWriter(ArchTemp))) {
        String linea;
        while ((linea = reader.readLine()) != null) {
            if (linea.contains("Codigo: " + code)) {
                productoEncontrado = true;
                System.out.println("Producto encontrado: " + linea);
                System.out.println("Ingrese la cantidad de productos a agregar al stock:");
                int cantidadAgregar = scan.nextInt();
                scan.nextLine();  // Limpiar el buffer

                // Actualizar la cantidad en la línea
                String[] partes = linea.split("\\|");
                
                // Buscar el campo "Stock:" para actualizar su valor
                for (int i = 0; i < partes.length; i++) {
                    if (partes[i].trim().startsWith("Stock:")) {
                        int stockActual = Integer.parseInt(partes[i].replace("Stock:", "").trim());
                        int nuevoStock = stockActual + cantidadAgregar;
                        partes[i] = " Stock: " + nuevoStock;  // Actualizar solo el stock
                    }
                }

                // Reescribir la línea con el stock actualizado
                String nuevaLinea = String.join(" | ", partes);
                writer.write(nuevaLinea);
                writer.newLine();
                System.out.println("Stock actualizado correctamente.");
            } else {
                writer.write(linea);
                writer.newLine();
            }
        }
    }

    if (!productoEncontrado) {
        System.out.println("Producto no encontrado.");
        ArchTemp.delete();
    } else {
        // Reemplazar el archivo original con el actualizado
        if (Producto.delete() && ArchTemp.renameTo(Producto)) {
            System.out.println("Registro de entrada completado.");
        } else {
            System.out.println("Error al actualizar el archivo de productos.");
        }
    }
}
               
public static void registrarSalida(Scanner scan, File Producto) throws IOException {
    System.out.println("Ingrese el codigo del producto para registrar la salida:");
    String codeInput = scan.nextLine().trim();
    
    int code;
    try {
        code = Integer.parseInt(codeInput);
    } catch (NumberFormatException e) {
        System.out.println("El codigo ingresado no es un numero entero.");
        return;
    }
    
    boolean productoEncontrado = false;
    File ArchTemp = new File("ArchTemp.txt");

    try (BufferedReader reader = new BufferedReader(new FileReader(Producto));
         BufferedWriter writer = new BufferedWriter(new FileWriter(ArchTemp))) {
        String linea;
        while ((linea = reader.readLine()) != null) {
            if (linea.contains("Codigo: " + code)) {
                productoEncontrado = true;
                System.out.println("Producto encontrado: " + linea);
                System.out.println("Ingrese la cantidad de productos a vender:");
                int cantidad = scan.nextInt();
                scan.nextLine();  // Limpiar el buffer

                // Actualizar la cantidad en la linea
                String[] partes = linea.split("\\|");
                int stockAct = Integer.parseInt(partes[partes.length - 1].trim().replace("Stock: ", ""));
                
                if (cantidad > stockAct) {
                    System.out.println("No hay suficiente stock para realizar la venta.");
                    writer.write(linea);  // Escribir la linea sin cambios
                } else {
                    int nuevoStock = stockAct - cantidad;
                    partes[partes.length - 1] = " Stock: " + nuevoStock;

                    // Reescribir la linea con el stock actualizado
                    String nuevaLinea = String.join(" | ", partes);
                    writer.write(nuevaLinea);
                    writer.newLine();
                    System.out.println("Salida del producto realizada correctamente.");
                    
                    // Aqui puedes añadir codigo para generar un registro de la venta con fecha y hora.
                }
            } else {
                writer.write(linea);
                writer.newLine();
            }
        }
    }

    if (!productoEncontrado) {
        System.out.println("Producto no encontrado.");
        ArchTemp.delete();
    } else {
        // Reemplazar el archivo original con el actualizado
        if (Producto.delete() && ArchTemp.renameTo(Producto)) {
            System.out.println("Registro de salida completado.");
        } else {
            System.out.println("Error al actualizar el archivo de productos.");
        }
    }
    }

   
}
                                                                        

            






                    
            
        


   
    

