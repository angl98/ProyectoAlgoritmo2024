

package com.mycompany.proyectoalgoritmo;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author angel
 */
public class ProyectoAlgoritmo {

    public static void main(String[] args) throws IOException {
        File Admin = new File("Admin.txt");
        File Vendedor = new File("Vendedor.txt");
        File Bodegero = new File("Bodegero.txt");
        
        Scanner scan = new Scanner(System.in);
        System.out.println("--Bienvenido Usuario--");
        System.out.print("Seleccione en donde desea ingresar segun su rol: \n");
        
        System.out.print("[1] Administrador. \n");
        System.out.print("[2] Vendedor. \n");
        System.out.print("[3] Adm. Bodega. \n");
          
        int Roles = scan.nextInt();
        scan.nextLine();
     
            switch (Roles){
                
                case 1 -> {
                    
                Administrador.admin(scan, Admin);
            }
                    
            }

        
        
    }
}
