package com.mycompany.ejemplos1;

import java.util.Scanner;

/**
 *
 * @author angel MayCar
 * @since 8/2/2024
 * Codificacion de resolucion de problemas
 */
public class ejemplo {
    
        public static void main(String[] args){
            //crear scanner para leer las variables del problema
            Scanner scan = new Scanner(System.in);
            
            //pedir las notas para despues leerlas y guardarlas en su respectiva variables
            System.out.println("Ingrese la primera nota: ");
            double n1 = scan.nextDouble();
            System.out.println("Ingrese la segunda nota: ");
            double n2 = scan.nextDouble();
            System.out.println("Ingrese la tercera nota: ");
            double n3 = scan.nextDouble();
            System.out.println("Ingrese la cuarta nota: ");
            double n4 = scan.nextDouble();
            
                double r = (n1+n2+n3+n4)/ 4.0f;
                
                    System.out.println("El promedio de las notas es: " + r);
                    
        }
    
}
