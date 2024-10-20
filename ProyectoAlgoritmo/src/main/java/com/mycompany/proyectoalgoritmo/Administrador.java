/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoalgoritmo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Administrador {
    static boolean admin(Scanner scan, File Admin, File Categoria, File Producto) throws IOException {
        try {
            if (Admin.exists() && Admin.length() > 0) {
                System.out.println("--INICIO DE SESION--");
            } else {
                System.out.println("Cree un Usuario para el Administrador:  ");
                String usAd = scan.nextLine();
                System.out.println("Cree una contraseña:   ");
                String conAd = scan.nextLine();

                try (PrintWriter writer = new PrintWriter(Admin)) {
                    writer.println(usAd);
                    writer.println(conAd);
                }
            }

            boolean inicioCorrecto = false;
            while (!inicioCorrecto) {
                System.out.println("Ingrese el usuario: ");
                String us = scan.nextLine();
                System.out.println("Ingrese la contraseña:  ");
                String co = scan.nextLine();

                try (BufferedReader reader = new BufferedReader(new FileReader(Admin))) {
                    String usuarioReg = reader.readLine();
                    String contraReg = reader.readLine();

                    if (us.equals(usuarioReg) && co.equals(contraReg)) {
                        System.out.println("Inicio de sesion exitoso.");
                        inicioCorrecto = true;
                        menuAdmin(scan, Categoria, Producto);
                    } else {
                        System.out.println("Usuario o contraseña incorrectos. Vuelva a intentarlo.");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Se produjo un error de entrada/salida: " + e.getMessage());
        }
        return false;
    }

    public static void menuAdmin(Scanner casos, File Categoria, File Producto) throws IOException {
        try {
            System.out.println("--Bienvenido al menu de opciones--");
            System.out.println("1. *PRODUCTOS* ");
             System.out.println("2. *ETNRADA DE PRODUCTOS* ");
               System.out.println("3. *SALIDA DE PRODUCTOS* ");
            int caso = casos.nextInt();
            casos.nextLine();
            
                    Producto productoInstance = new Producto();

            switch (caso) {
                case 1:
                    productoInstance.producto(Categoria, Producto);
                    break;
                case 2:
                    productoInstance.entradaProducto(casos, Producto);
                    break;
                case 3:
                    productoInstance.registrarSalida(casos, Producto);
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        } catch (Exception e) {
            System.out.println("Se produjo un error: " + e.getMessage());
        }
    }
    
    
    
    
}

