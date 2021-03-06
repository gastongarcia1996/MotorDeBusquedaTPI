/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;

/**
 *
 * @author dlcusr
 */
public class Documento implements Serializable
{
    private int id_doc = 0;
    private String nombre_doc;
    private int frecuencia_termino;
    private double peso = 0;

    public Documento()
    {
    }

    public Documento(int id_doc, String nombre_doc) 
    {
        this.id_doc = id_doc;
        this.nombre_doc = nombre_doc;
    }
    
    public Documento(String nombre_doc)
    {
        this.nombre_doc = nombre_doc;
    }
  
    public String getNombre_doc() 
    {
        return nombre_doc;
    }

    public void setNombre_doc(String nombre_doc)
    {
        this.nombre_doc = nombre_doc;
    }

    public int getId_doc()
    {
        return id_doc;
    }

    public void setId_doc(int id_doc) 
    {
        this.id_doc = id_doc;
    }

    public int getFrecuencia_termino() 
    {
        return frecuencia_termino;
    }

    public void setFrecuencia_termino(int frecuencia_termino) 
    {
        this.frecuencia_termino = frecuencia_termino;
    }
     
    public double getPeso()
    {
        return this.peso;
    }
    
    public void setPeso(double peso)
    {
        this.peso = peso;
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Id: ").append(this.id_doc).append(" Nombre: ").append(this.nombre_doc);
        
        return sb.toString();
    }
}
