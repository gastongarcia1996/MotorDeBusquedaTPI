/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

/**
 *
 * @author dlcusr
 */
public class ErrorMsg 
{
    private String titulo;
    private String mensaje;
    
    public ErrorMsg()
    {
    }
    
    public ErrorMsg(String titulo, String mensaje)
    {
        this.titulo = titulo;
        this.mensaje = mensaje;
    }

    public String getTitulo() 
    {
        return titulo;
    }

    public void setTitulo(String titulo) 
    {
        this.titulo = titulo;
    }

    public String getMensaje() 
    {
        return mensaje;
    }

    public void setMensaje(String mensaje) 
    {
        this.mensaje = mensaje;
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Titulo: ").append(this.titulo).append(" Mensaje: ").append(this.mensaje);
        
        return sb.toString();
    }
}
