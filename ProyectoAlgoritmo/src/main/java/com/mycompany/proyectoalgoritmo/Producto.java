/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoalgoritmo;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author angel
 */
public class Producto {
    public static void producto(){
        Scanner scan = new Scanner(System.in);
        File Productos = new File("Producto.txt");
        System.out.println("[1] Crear Categorias: ");
        System.out.println("[2] Caracteristicas de productos: ");
        System.out.println("[3] Agregar producto: ");
        System.out.println("[4] Dar  de alta o baja un producto: ");
        System.out.println("[5] Modificar producto: ");
        int Produ = scan.nextInt();
        
        switch(Produ){
            case 1:
               
            
        }
    }
    
}
