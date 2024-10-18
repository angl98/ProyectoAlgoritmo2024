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
public class Administrador {
        static boolean admin(Scanner scan, File Admin) throws IOException {
        // Verificar si ya existe un usuario en el archivo
        if (Admin.exists() && Admin.length() > 0) {
            System.out.println("--INICIO DE SESION--");
        } else {
            // Si no existe un usuario, registrar uno nuevo
            System.out.println("Cree un Usuario para el Administrador:  ");
            String usAd = scan.nextLine();
            System.out.println("Cree una contraseña:   ");
            String conAd = scan.nextLine();

            // Guardar el usuario y la contraseña en el archivo
            try (PrintWriter writer = new PrintWriter(Admin)) {
                writer.println(usAd);
                writer.println(conAd);
            }
        }

        //inicio de sesión
        boolean inicioCorrecto = false;
        while (!inicioCorrecto) {
            System.out.println("Ingrese el usuario: ");
            String us = scan.nextLine();
            System.out.println("Ingrese la contrasenia:  ");
            String co = scan.nextLine();

            // Leer el archivo para verificar
            try (BufferedReader reader = new BufferedReader(new FileReader(Admin))) {
                String usuarioReg = reader.readLine();
                String contraReg = reader.readLine();

                if (us.equals(usuarioReg) && co.equals(contraReg)) {
                    System.out.println("Inicio de sesion exitoso.");
                    inicioCorrecto = true; // Salir del bucle
                    menuAdmin(scan);
                } else {
                    System.out.println("Usuario o contrasenia incorrectos. Vuelva a intentarlo.");
                }
            }
        }
        return false; 
    }
        public static void menuAdmin(Scanner casos) throws IOException{ 
            System.out.println("--Bienvendio al menu de opcciones--");
            System.out.println("1. *PRODUCTOS* ");
            int caso = casos.nextInt();
            casos.nextLine();
                    
            switch(caso){
                
                case 1:
                    Producto.producto();
                    break;
                case 2:
                    
            }
        }
}

