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
    private Termino t1 = null;
    
    /**
     * Arma el vocabulario del motor;
     */
    public void armar_vocabulario()
    {
        String aux = "";
        String aux2 = "";
        int cant_aux = 0;
        
        try
        {
            String s = "";
            File archivo = new File("/home/dlcusr/NetBeansProjects/MotorDeBusquedaTPI/DocumentosTPI");
            File[] lista = archivo.listFiles();
            File fileAux = null; //variable auxiliar para recordar el documento en que estuve parado
            
            FileReader fr = null;
            BufferedReader br = null;
            //ciclo for que recorre la lista de documentos
            for(int i = 0; i < lista.length; i++)
            {
                
                sl.add(new Documento(i, lista[i].getName()));
                if(es_txt(lista[i]))
                {
                    fr = new FileReader(lista[i]);
                    br = new BufferedReader(fr);
                    fileAux = lista[i];
                }
                //ciclo que toma cada linea del documento
                while((s = br.readLine()) != null)
                {
                    
                    st = new StringTokenizer(s, " |°!#$%&/()=?¡¿-\\/_*.,;:<>+");                   
                    
                    //ciclo que recorre las palabras y las agrega a una tabla hash
                    //evitando que no se repitan
                    while(st.hasMoreTokens()) 
                    {      
                        aux = st.nextToken();
                        t1 = new Termino(aux);
                        //si el termino no esta en la hashtable
                        if(!ht.containsKey(t1.getPalabra())) 
                        {
                            t1.setCant_doc_aparece(t1.getCant_doc_aparece() + 1);
                            ht.put(aux, t1);  
                            
                        }
                        else //si esta
                        {
                            if(lista[i] != fileAux)
                            {
                                t1.setCant_doc_aparece(t1.getCant_doc_aparece() + 1);
                            }
                        }
                    }
                   
                }
                              
                br.close();
            }           
                
        }
        catch(IOException e)
        {
            System.out.println("Error al leer el archivo");
        }
    }
    
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
