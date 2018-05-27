/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nico
 */
public class Helper
{

    public static Indexacion index = new Indexacion();
//        public static boolean indexado=false;
    public Hashtable<String, Termino> ht;

    public static void armarVocabulario()
    {

        index.armar_vocabulario();

    }

//        public static void resetIndexado(){
//            indexado=false;
//        }
    public static int getCantDocumentos()
    {
        int aux = 0;
        aux = index.cantDocs();
        return aux;
    }

    public static Hashtable<String, Termino> getHt()
    {
        return index.getHt();
    }

    public static boolean armarPosteo()
    {
        try
        {
            index.armar_posteo();
            return true;
        }
        catch (Exception ex)
        {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static void guardar_indexacion() throws IOException
    {
        File f = new File("C:\\Users\\gasto\\Documents\\NetBeansProjects\\MotorDeBusquedaTPI\\Indexacion");
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        //oos.writeObject(crearHash());
        oos.writeObject(index);
        oos.close();
    }

    public static void leer_indexacion() throws IOException, ClassNotFoundException
    {
        File f = new File("C:\\Users\\gasto\\Documents\\NetBeansProjects\\MotorDeBusquedaTPI\\Indexacion");

        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);

        index = (Indexacion) ois.readObject();
        ois.close();
    }

    public static boolean existe_indexacion(String archivo)
    {
        File f = new File(archivo);

        return (f.exists() && f.isFile());
    }
}

