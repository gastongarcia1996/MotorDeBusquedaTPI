/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import clases.Datos;


/**
 *
 * @author Gaston
 */
public abstract class DBDocumento 
{
    private final String nombre_doc = "nombre_doc";
    private final String frec_termino = "frec_termino";
    
    
    public static void insertarDocumento(DBManager db, String nombre)
    {
        try
        {
        int num = 1;
        String query = "INSERT INTO documentos(nombre, frec_termino) VALUES('" + nombre + "',"
                + num + ");";
        if(db != null)
        {
            db.executeInsert(query);
        }
        else
        {
            db = Datos.getSingleDB();
            db.executeInsert(query);
        } 
        }
        catch(Exception e)
        {
            //System.out.println("Error" + e.getMessage());
            System.out.println(e.getClass().getName());
        }
    }
}
