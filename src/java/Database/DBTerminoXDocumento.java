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
public abstract class DBTerminoXDocumento 
{
    public static void insertarTerminoXDocumento(DBManager db, String palabra, int id_doc, int frec_termino)
    {
        String query = "";
        try
        {
            query = "INSERT INTO terminoxdocumento(palabra, id_doc, frec_termino) VALUES('" + palabra + "'," + 
                    id_doc + "," + frec_termino + ");";
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
            System.out.println(e.getMessage());
        }
        
        
    }
}
