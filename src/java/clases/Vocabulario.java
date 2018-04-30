/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author dlcusr
 */
public class Vocabulario implements Serializable
{
    private Hashtable<String, Termino> ht = new Hashtable<>();
    private ArrayList<Documento> sl = new ArrayList<>();
    private int cantidad = 0;
    private StringTokenizer st = null;
    private File[] lista = null;
    
    /**
     * Arma el vocabulario del motor;
     */
    public Termino armar_vocabulario()
    {
        Termino t1 = null;
        String aux = "";
        String aux2 = "";
        int countMax = 0;
        int countMaxAux = 0;
        int count = 0;
        
        try
        {
            String s = "";
            File archivo = new File("/home/dlcusr/NetBeansProjects/MotorDeBusquedaTPI/DocumentosTPI");
            lista = archivo.listFiles();
            File fileAux = null; //variable auxiliar para recordar el documento en que estuve parado
            
            FileReader fr = null;
            BufferedReader br = null;
            //ciclo for que recorre la lista de documentos
            for(int i = 0; i < 6; i++)
            {
                
                sl.add(new Documento(i, lista[i].getName()));
                if(es_txt(lista[i]))
                {
                    fr = new FileReader(lista[i]);
                    br = new BufferedReader(fr);
                    fileAux = lista[i];
                }
                else break;
                //ciclo que toma cada linea del documento
                while((s = br.readLine()) != null)
                {
                    
                    st = new StringTokenizer(s, " |°!#$%&/()=?¡¿-\\/_*.,;:<>+");                   
                    
                    //ciclo que recorre las palabras y las agrega a una tabla hash
                    //evitando que no se repitan
                    while(st.hasMoreTokens()) 
                    {      
                        aux = st.nextToken();
                        
                        //si el termino no esta en la hashtable
                        if(!ht.containsKey(aux)) 
                        {
                            t1 = new Termino(aux);
                            ht.put(aux, t1);
                            t1.getAl().add(fileAux.getName());
                            
                        }
                        else
                        {
                            t1 = ht.get(aux);
                            if(!t1.getAl().contains(fileAux.getName()))
                            {
                                t1.getAl().add(fileAux.getName());
                            }
                        }                                        
                    }
                   
                }
                /*
                if(countMax < countMaxAux) countMax = countMaxAux;
                
                if(countMaxAux > 0) count++;
                countMaxAux = 0;
                */
                br.close();
                              
            }           
                
        }
        catch(IOException e)
        {
            System.out.println("Error al leer el archivo");
        }
        return ht.get("count");
    }
    /*
    public Termino armar_posteo(String palabra)
    {
        Termino t = ht.get(palabra);
        FileReader fr = null;
        BufferedReader br = null;
        String aux = "";
        int countMax = 0;
        int countMaxAux = 0;
        int count = 0;
        StringTokenizer st = null;
        
        for(int i = 0; i < 3; i++)
        {
            try
            {
                if(es_txt(lista[i]))
                {
                    fr = new FileReader(lista[i]);
                    br = new BufferedReader(fr);
                }
                               
                while((aux = br.readLine()) != null)
                {
                    st = new StringTokenizer(aux);
                    while(st.hasMoreTokens())
                    {
                        if(st.nextToken().compareToIgnoreCase(palabra) == 0)
                        {
                            countMaxAux++;
                        }
                    }
                    
                }
                
                if(countMax < countMaxAux) countMax = countMaxAux;
                
                if(countMaxAux > 0) count++;
                countMaxAux = 0;
                br.close();
            }
            catch(IOException e)
            {
                System.out.println("Error en la lectura del archivo");
            }         
        }
        t.setCant_doc_aparece(count);
        t.setMax_frec_aparicion(countMax);
        return t;
    }
    */
    public void obtenerDocumentos()
    {
        
        File archivo = new File("/home/dlcusr/NetBeansProjects/MotorDeBusquedaTPI/DocumentosTPI");
        File[] lista = archivo.listFiles();
       
            //ciclo for que recorre la lista de documentos
            for(int i = 0; i < lista.length; i++)
            {
                
                sl.add(new Documento(i, lista[i].getName()));
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
