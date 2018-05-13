/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.List;
import java.util.ArrayList;


/**
 *
 * @author dlcusr
 */
public class main extends Thread 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        /*
        ArrayList al = new ArrayList();
        
        Termino t1 = new Termino();
        Termino t2 = new Termino();
        Termino t3 = null;
        
        t1.setPalabra("Hola");
        t2.setPalabra("Puta");
        
        al.add(t1);
        al.add(t2);
        
        System.out.println(al.toString());
        
        t3 = (Termino)al.get(0);
        
        t3.setPalabra("Chau");
        System.out.println(al.toString());
        */
        
        //v.armar_vocabulario();
        Indexacion v1 = new Indexacion();    
        Indexacion v2 = new Indexacion();
        System.out.println("Armando vocabulario...");
        v1.armar_vocabulario();
        v2.setLista(v1.getLista());
        v2.armar_vocabulario();
        
        System.out.println("Armando posteo...");
              
        Hilo1 h1 = new Hilo1(v1);
        Hilo2 h2 = new Hilo2(v2);
        
        h1.start();               
        h2.start();
    } 
}
