
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author dlcusr
 */
public class main {

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
        
//        v.armar_vocabulario();
        
        System.out.println("Armando vocabulario...");        
        try
        {
            Helper.leer_indexacion();
            //System.out.println(Helper.getHt());
            System.out.println(Helper.getCantDocumentos());
        }
        catch (IOException ex)
        {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

