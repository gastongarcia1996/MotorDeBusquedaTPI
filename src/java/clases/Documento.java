/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author dlcusr
 */
public class Documento 
{
    private int id_doc;
    private String nombre_doc;
    private int frecuencia_termino;

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
          
    public String to_string()
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Id: ").append(this.id_doc).append(" Nombre: ").append(this.nombre_doc);
        
        return sb.toString();
    }
}
