/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

/**
 *
 * @author Gaston
 */
public abstract class DBTerminoXDocumento 
{
    public static void insertarTerminoXDocumento(DBManager db, String palabra, int id_doc)
    {
        String query = "INSERT INTO terminoxdoocumento(id_doc, palabra) VALUES(" + id_doc
                 + ",'" + palabra + "')";
        
        
    }
}
