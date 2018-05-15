package clases;

/**
 * Representa una palabra, asociada con un significado, como para armar un diccionario.
 * 
 * @author Ing. Valerio Frittelli.
 * @version Octubre de 2008.
 */

import java.io.*;
import java.util.ArrayList;

public class Termino implements Comparable<Termino>, Serializable
{
    private String palabra;     
    private String significado; 
    private int cant_doc_aparece;
    private int frec_aparicion;
    private int max_frec_aparicion;
    private ArrayList al;
    private String nom_doc;
    /**
     * Constructor por defecto. Asigna frecuencia 1 al objeto creado
     */
    public Termino()
    {
    }

    /**
     * Inicializa un termino con palabra igual p y significado en blanco. 
     * @param p la palabra a almacenar en el objeto creado.
     */
    public Termino (String p)
    {
       palabra = p;
       significado = "";
       al = new ArrayList();
    }


    /**
     * Inicializa un termino con palabra igual p y significado igual a s. 
     * @param p la palabra a almacenar en el objeto creado.
     * @param s el significado de la palabra a almacenar.
     */
    public Termino (String p, String s)
    {
       palabra = p;
       significado = s;
    }

    Termino(String word, int cantApar, int frecMax) {
        palabra=word;
        max_frec_aparicion=frecMax;
        cant_doc_aparece=cantApar;
    }

    public ArrayList getAl() 
    {
        return al;
    }

    public void setAl(ArrayList al) 
    {
        this.al = al;
    }

    public String getNom_doc()
    {
        return nom_doc;
    }

    public void setNom_doc(String nom_doc)
    {
        this.nom_doc = nom_doc;
    }

    public int getMax_frec_aparicion() 
    {
        return max_frec_aparicion;
    }

    public void setMax_frec_aparicion(int max_frec_aparicion) 
    {
        this.max_frec_aparicion = max_frec_aparicion;
    }
      
    /**
     * Metodo de acceso al valor de la palabra.
     * @return el valor de la palabra.
     */
    public String getPalabra()
    {
       return palabra;
    }

    /**
     * Metodo de acceso al valor del significado.
     * @return el valor del significado.
     */
    public String getSignificado()
    {
       return significado;
    }


    /**
     * Metodo modificador de la palabra.
     * @param c el valor a almacenar como palabra. 
     */
    public void setPalabra(String c)
    {
       palabra = c;
    }

    /**
     * Metodo modificador del significado.
     * @param c el valor a almacenar como significado. 
     */
    public void setSignificado(String c)
    {
       significado = c;
    }

    /**
     * Metodo que retorna la cantidad de documentos en la que aparece el termino
     * @return la cantidad de documentos en la que aparece un termino
     */
    public int getCant_doc_aparece() 
    {
        return cant_doc_aparece;
    }

    public void setCant_doc_aparece(int cant_doc_aparece) 
    {
        this.cant_doc_aparece = cant_doc_aparece;
    }

    /**
     * Metodo que calcula retorna la cantidad de apariciones maxima en un documento
     * @return la frecuencia maxima de aparicion en un documento
     */
    public int getFrec_aparicion() 
    {
        return frec_aparicion;
    }

    public void setFrec_aparicion(int frec_aparicion) 
    {
        this.frec_aparicion = frec_aparicion;
    }
         
    /**
     * Metodo para redefinir el metodo toString heredado desde Object.
     * @return  una cadena representando los valores contenidos.
     */
    @Override
    public String toString()
    {
       return "\n\tPalabra: " + palabra + "\tsignificado: " + significado;
    }

    /**
     * Redefine al metodo hashCode heredado desde Object.      * 
     * @return el valor del atributo "clave", para ser usado como hashCode en una tabla hash o en donde sea 
     * que se requiera esa clave.
     */
    @Override
    public int hashCode()
    {
      return Math.abs(palabra.hashCode()); 
    }
    
   
    /**
     * Redefine al metodo equals heredado desde Object. 
     * 
     * @param obj el objeto contra el cual se compara.
     * @return true si se considera que los objetos son iguales.
     */
    @Override
    public boolean equals (Object obj)
    {
      if( obj == null ) { return false; }
      if( ! (obj instanceof Termino) ) { return false; }
      
      Termino x = (Termino) obj;    
      return palabra.equals(x.palabra);
    }

    /**
     * Permite comparar el contenido de informacion de un objeto  con el de otro que viene como parametro.
     * @param o el objeto a comparar.
     * @return un valor int:  0 si eran iguales, >0 si el impl�cito era mayor, <0 si el impl�cito era menor.
     * @throws ClassCastException si se intenta moldear a una clase incompatible.
     */
     @Override
     public int compareTo(Termino o)
     {
       return palabra.compareTo(o.palabra);
     }
}
