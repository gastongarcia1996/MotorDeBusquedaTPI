
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import Database.DBDocumento;
import Database.DBManager;
import Database.DBTermino;
import Database.DBTerminoXDocumento;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author dlcusr
 */
public class Indexacion implements Serializable
{
    private Hashtable<String, Termino> ht = new Hashtable<>();
    private ArrayList<Documento> al = new ArrayList<>();
    private LinkedList<Termino> ll = new LinkedList<>();
    private int cantidad = 0;
    private StringTokenizer st = null;
    private File[] lista = null;
    DBManager db = null;
    
    /**
     * Arma el vocabulario del motor;
     */
    public void armar_vocabulario()
    {
        Termino t1 = null;
        String aux = "";
        String aux2 = "";
        int countMax = 0;
        int countMaxAux = 0;
        int count = 0;
        
        
        try
        {
            //db = Datos.getSingleDB();
            
            String s = "";
            //File archivo = new File("/home/dlcusr/NetBeansProjects/MotorDeBusquedaTPI/DocumentosTPI");
            File archivo = new File("C:\\Users\\gasto\\Documents\\NetBeansProjects\\MotorDeBusquedaTPI\\DocumentosTPI");
            lista = archivo.listFiles();
            File fileAux = null; //variable auxiliar para recordar el documento en que estuve parado
            
            FileReader fr = null;
            BufferedReader br = null;
            //ciclo for que recorre la lista de documentos
            for(int i = 0; i < lista.length; i++)
            {      
                al.add(new Documento(i, lista[i].getName()));
                if (es_txt(lista[i]))
                {
                    fr = new FileReader(lista[i]);
                    br = new BufferedReader(fr);
                    fileAux = lista[i];
                }
                else throw new IOException();
                //DBDocumento.insertarDocumento(db, lista[i].getName());
                //ciclo que toma cada linea del documento
                while((s = br.readLine()) != null)
                {
                    
                    st = new StringTokenizer(s, " |\"°!#$%&/()=?¡^[]¿-\\/_*.,;:<>+");                   
                    
                    //ciclo que recorre las palabras y las agrega a una tabla hash
                    //evitando que no se repitan
                    while(st.hasMoreTokens()) 
                    {      
                        aux = st.nextToken().toLowerCase();
                        
                        
                        //aux = aux.replace("'", "''");
                                               
                        
                        //si el termino no esta en la hashtable
                        if(!ht.containsKey(aux)) 
                        {
                            t1 = new Termino(aux);
                            ht.put(aux, t1);
                            //DBTermino.insertarTermino(db, aux);
                        }
                        /*
                        else
                        {
                            t1 = ht.get(aux);
                            if(!t1.getAl().contains(fileAux.getName()))
                            {
                                t1.getAl().add(fileAux.getName());
                            }
                        }
                        */
                    }
                   
                }
                /*
                if(countMax < countMaxAux) countMax = countMaxAux;
                
                if(countMaxAux > 0) count++;
                countMaxAux = 0;
                */
                br.close();
                              
            }           
            //db.disconnect();
        }
        catch(Exception e)
        {
            System.out.println("Error al leer el archivo " + e.getMessage());
        }
    }
    
    public void armar_posteo() throws Exception
    {  
        String palabra = "";
        int cont = 0;
        int id_doc = 0;
        Termino aux = null;
        Hashtable<String, Termino> hashAux = new Hashtable<>();
        db = Datos.getSingleDB();
        int i;
        
        for(i = 0; i < 10; i++)
        {
            FileReader fr = new FileReader(lista[i]);
            BufferedReader br = new BufferedReader(fr);           
            
            while((palabra = br.readLine()) != null)
            {
                st = new StringTokenizer(palabra, " |\"°!#$%&/()=?¡^[]¿-\\/_*.,;:<>+");
                
                while(st.hasMoreTokens()) 
                {    
                    palabra = st.nextToken().toLowerCase();
                    if(ht.containsKey(palabra))
                    {
                        aux = ht.get(palabra);
                        aux.setMax_frec_aparicion(aux.getMax_frec_aparicion() + 1);
                        
                        if(!hashAux.containsKey(palabra))
                        {
                            palabra = palabra.replace("'", "''");
                            hashAux.put(palabra, aux);
                        }                      
                    }
                    
                }                
            } 
            //Recorrido de la hashAux
            Enumeration e = hashAux.keys();
            String clave;
            while( e.hasMoreElements() )
            {
                clave = (String)e.nextElement();
                DBTerminoXDocumento.insertarTerminoXDocumento(db, clave, i + 1, hashAux.get(clave).getMax_frec_aparicion());
                ht.get(clave).setMax_frec_aparicion(0);
            }
            hashAux = new Hashtable<>();
        }          
    }            
        
            
    
    
    public void obtenerDocumentos()
    {
        try
        {
            db = Datos.getSingleDB();
            File archivo = new File("C:\\Users\\gasto\\Documents\\NetBeansProjects\\MotorDeBusquedaTPI\\DocumentosTPI");
            File[] lista = archivo.listFiles();
       
            //ciclo for que recorre la lista de documentos
            for(int i = 0; i < lista.length; i++)
            {               
                //sl.add(new Documento(i, lista[i].getName()));
                DBDocumento.insertarDocumento(db, lista[i].getName());
            }
            db.disconnect();
        } 
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
    }

    public List getDocumentosList()
    {
        return al;
    }
    
    public Hashtable<String, Termino> getHt() 
    {
        return ht;
    }
    
    /**
     * Metodo que recibe un archivo y valida que sea de texto
     * @param f: el archivo a validar
     * @return true si es un archivo txt y false en caso contrario
     */
    private boolean es_txt(File f)
    {
        return f.getName().endsWith(".txt") || f.getName().endsWith(".TXT");
    }
      
}
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import Database.DBDocumento;
import Database.DBManager;
import Database.DBTermino;
import Database.DBTerminoXDocumento;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author dlcusr
 */
public class Indexacion implements Serializable
{
    private Hashtable<String, Termino> ht = new Hashtable<>();
    private ArrayList<Documento> sl = new ArrayList<>();
    private int cantidad = 0;
    private StringTokenizer st = null;
    private String directory="DocumentosTPI";
    private File[] lista = null;
    DBManager db = null;
    
    /**
     * Arma el vocabulario del motor;
     */
    public void armar_vocabulario()
    {
        Termino t1 = null;
        String aux = "";
        String aux2 = "";
        int countMax = 0;
        int countMaxAux = 0;
        int count = 0;
        
        
        try
        {
            //db = Datos.getSingleDB();
            
            String s = "";
            //File archivo = new File("/home/dlcusr/NetBeansProjects/MotorDeBusquedaTPI/DocumentosTPI");
            File archivo = new File(directory);
            lista = archivo.listFiles();
            File fileAux = null; //variable auxiliar para recordar el documento en que estuve parado
            
            FileReader fr = null;
            BufferedReader br = null;
            //ciclo for que recorre la lista de documentos
            for(int i = 0; i < lista[i].length(); i++)
            {              
                sl.add(new Documento(i, lista[i].getName()));
                if(es_txt(lista[i]))
                {
                    fr = new FileReader(lista[i]);
                    br = new BufferedReader(fr);
                    fileAux = lista[i];
                }
                else throw new IOException();
                //DBDocumento.insertarDocumento(db, lista[i].getName());
                //ciclo que toma cada linea del documento
                while((s = br.readLine()) != null)
                {
                    
                    st = new StringTokenizer(s, " |\"°!#$%&/()=?¡^[]¿-\\/_*.,;:<>+");                   
                    
                    //ciclo que recorre las palabras y las agrega a una tabla hash
                    //evitando que no se repitan
                    while(st.hasMoreTokens()) 
                    {      
                        aux = st.nextToken().toLowerCase();
                        
                        
                        aux = aux.replace("'", "''");
                        
                        //si el termino no esta en la hashtable
                        if(!ht.containsKey(aux)) 
                        {
                            t1 = new Termino(aux);
                            ht.put(aux, t1);
                            //DBTermino.insertarTermino(db, aux);
                        }
                        /*
                        else
                        {
                            t1 = ht.get(aux);
                            if(!t1.getAl().contains(fileAux.getName()))
                            {
                                t1.getAl().add(fileAux.getName());
                            }
                        }
                        */
                    }
                   
                }
                /*
                if(countMax < countMaxAux) countMax = countMaxAux;
                
                if(countMaxAux > 0) count++;
                countMaxAux = 0;
                */
                br.close();
                              
            }           
            //db.disconnect();
        }
        catch(Exception e)
        {
            System.out.println("Error al leer el archivo " + e.getMessage());
        }
    }
    
    public void armar_posteo()
    {  
        Hashtable htAux = ht;
        FileReader fr = null;
        BufferedReader br = null;
        int countMax = 0;
        int countMaxAux = 0;
        int count = 0;
        int frec_doc = 0;       
        String palabra, aux = "";
        Termino t = null;
        boolean encontrado = false;
                             
            for(int i = 0; i < lista[i].length(); i++)
            {
                htAux = ht;
                while(!htAux.isEmpty())
                {                                
                    Enumeration e = htAux.keys();
                    while(e.hasMoreElements())                
                    {    
                        palabra = (String) e.nextElement();
                        t = ht.get(palabra);

                        try
                        {
                            if(db == null) db = Datos.getSingleDB();
                            if(es_txt(lista[i])) 
                            {
                                fr = new FileReader(lista[i]);
                                br = new BufferedReader(fr);
                            }

                            while((aux = br.readLine()) != null) 
                            {
                                st = new StringTokenizer(aux, " |\"°!#$%&/()=?¡^[]¿-\\/_*.,;:<>+");
                                while(st.hasMoreTokens()) 
                                {
                                    if(st.nextToken().compareToIgnoreCase(palabra) == 0) 
                                    {
                                        countMaxAux++;
                                    }
                                }
                            }
                            if(countMaxAux != 0)
                            {
                                DBTerminoXDocumento.insertarTerminoXDocumento(db, palabra, i + 1, countMaxAux);
                                htAux.remove(palabra);
                            }

                            if(countMax < countMaxAux) 
                            {
                                countMax = countMaxAux;
                            }

                            if(countMaxAux > 0) 
                            {
                                count++;
                            }
                            countMaxAux = 0;
                            br.close();
                        } 
                        catch(Exception ex) 
                        {
                            System.out.println("Error en la lectura del archivo");
                        }
                        t.setCant_doc_aparece(count);
                        t.setMax_frec_aparicion(countMax);  
                    } 
                }
            }            
        db.disconnect();
            
    }
    
    /*public void armar_posteo() throws Exception
    {  
        String palabra = "";
        int cont = 0;
        Termino aux = null;
        LinkedList<Termino> listAux = new LinkedList<>();
        db = Datos.getSingleDB();
        
        for(int i = 0; i < lista.length; i++)
        {
            FileReader fr = new FileReader(lista[i]);
            BufferedReader br = new BufferedReader(fr);
            
            while((palabra = br.readLine()) != null)
            {
                while(st.hasMoreTokens()) 
                {      
                    if(ht.containsKey(palabra))
                    {
                        aux = ht.get(palabra);
                        aux.setMax_frec_aparicion(aux.getMax_frec_aparicion() + 1);
                        aux.getAl().add(i);
                        listAux.add(aux);
                    }
                }
            }
            DBTerminoXDocumento.insertarTerminoXDocumento(db, aux.getPalabra(), i + 1, aux.getMax_frec_aparicion());
        }*/
    
    public void obtenerDocumentos()
    {
        try
        {
            db = Datos.getSingleDB();
            File archivo = new File("C:\\Users\\gasto\\Documents\\NetBeansProjects\\MotorDeBusquedaTPI\\DocumentosTPI");
            File[] lista = archivo.listFiles();
       
            //ciclo for que recorre la lista de documentos
            for(int i = 0; i < lista.length; i++)
            {               
                //sl.add(new Documento(i, lista[i].getName()));
                DBDocumento.insertarDocumento(db, lista[i].getName());
            }
            db.disconnect();
        } 
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
    }

    public List getDocumentosList()
    {
        return sl;
    }
    
    public Hashtable<String, Termino> getHt() 
    {
        return ht;
    }
    
    /**
     * Metodo que recibe un archivo y valida que sea de texto
     * @param f: el archivo a validar
     * @return true si es un archivo txt y false en caso contrario
     */
    private boolean es_txt(File f)
    {
        return f.getName().endsWith(".txt") || f.getName().endsWith(".TXT");
    }
      
}
