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
    public static void insertarTerminoXDocumento(DBManager db, String palabra, int id_doc)
    {
        String query = "";
        try
        {
            query = "INSERT INTO terminoxdoocumento(id_doc, palabra) VALUES(" + 
                    (int)DBDocumento.selectDocumentoId(db, query).getObject(1) + ",'" + palabra + "')";
            db = Datos.getSingleDB();
            db.executeQuery(query);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        
    }
}
