/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.*;
import java.util.LinkedList;

/**
 *
 * @author dlcusr
 */
public class Posteo
{
    
    private LinkedList<Termino>[] vector;
    
    public void añadir_a_posteo(Vocabulario v)
    {
        try
        {
            String s = "";
            File archivo = new File("/home/dlcusr/NetBeansProjects/MotorDeBusqueda/DocumentosTP1");
            File[] lista = archivo.listFiles();
            Termino t = null;
            
            FileReader fr = null;
            BufferedReader br = null;
            //ciclo for que lee los archivos
            for(int i = 0; i < lista.length; i++)
            {
                
                           
                br.close();
            }           
                
        }
        catch(IOException e)
        {
            System.out.println("Error al leer el archivo");
        }
        
    }
    
}
