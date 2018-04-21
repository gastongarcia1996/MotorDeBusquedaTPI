/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.Hashtable;

/**
 *
 * @author dlcusr
 */
public abstract class Hardcodeo 
{
    private static Hashtable alumnos = new Hashtable();
    
    public static void populateAlumnos() throws Exception
    {
        
        if (alumnos.isEmpty()) 
        {
            alumnos.put(1, (new Documento()));
            alumnos.put(2,(new Documento()));
            alumnos.put(3,(new Documento()));
            alumnos.put(4,(new Documento()));
            alumnos.put(5,(new Documento()));
            alumnos.put(6,(new Documento()));
            alumnos.put(7,(new Documento()));
            alumnos.put(8,(new Documento()));
            alumnos.put(9,(new Documento()));
            alumnos.put(10,(new Documento()));
            alumnos.put(11,(new Documento()));
            alumnos.put(12,(new Documento()));
            alumnos.put(13,(new Documento()));
            alumnos.put(14,(new Documento()));
            alumnos.put(15,(new Documento()));
            alumnos.put(16,(new Documento()));
            alumnos.put(17,(new Documento()));
            alumnos.put(18,(new Documento()));
            alumnos.put(19,(new Documento()));
            alumnos.put(20,(new Documento()));
            alumnos.put(21,(new Documento()));
        }
    }

    public static Hashtable getAlumnos()
    {
        return alumnos;
    }

    public static void setAlumnos(Hashtable alumnos) 
    {
        Hardcodeo.alumnos = alumnos;
    }
  
    
}
