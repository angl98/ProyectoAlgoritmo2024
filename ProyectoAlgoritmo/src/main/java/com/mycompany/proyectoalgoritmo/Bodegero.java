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

/**
 *
 * @author angel
 */
public class Bodegero {
    static boolean Bodega(Scanner scan, File Bodegero, File Categoria, File Producto) throws IOException {
        try {
            if (Bodegero.exists() && Bodegero.length() > 0) {
                System.out.println("--INICIO DE SESION--");
            } else {
                System.out.println("Cree un Usuario para el Bodegero:  ");
                String usAd = scan.nextLine();
                System.out.println("Cree una contrasenia:   ");
                String conAd = scan.nextLine();

                try (PrintWriter writer = new PrintWriter(Bodegero)) {
                    writer.println(usAd);
                    writer.println(conAd);
                }
            }

            boolean inicioCorrecto = false;
            while (!inicioCorrecto) {
                System.out.println("Ingrese el usuario: ");
                String us = scan.nextLine();
                System.out.println("Ingrese la contrasena:  ");
                String co = scan.nextLine();

                try (BufferedReader reader = new BufferedReader(new FileReader(Bodegero))) {
                    String usuarioReg = reader.readLine();
                    String contraReg = reader.readLine();

                    if (us.equals(usuarioReg) && co.equals(contraReg)) {
                        System.out.println("Inicio de sesion exitoso.");
                        inicioCorrecto = true;
                        menuBodegero(scan, Categoria, Producto);
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
    public static void menuBodegero(Scanner casos, File Categoria, File Producto) throws IOException {
        try {
            System.out.println("--Bienvenido al menu de opciones--");
             System.out.println("1. *ETNRADA DE PRODUCTOS* ");
               System.out.println("2. *SALIDA DE PRODUCTOS* ");
            int caso = casos.nextInt();
            casos.nextLine();
            
                    Producto productoInstance = new Producto();

            switch (caso) {
                case 1:
                    productoInstance.entradaProducto(casos, Producto);
                    break;
                case 2:
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

