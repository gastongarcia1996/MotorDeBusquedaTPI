/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author Gaston
 */
public class Hilo2 extends Thread 
{
    Indexacion v = null;
    
    public Hilo2(Indexacion v)
    {
        this.v = v;
    }
    
    @Override
    public void run()
    {
        try
        {
            v.armar_posteo();
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }       
    }
}
