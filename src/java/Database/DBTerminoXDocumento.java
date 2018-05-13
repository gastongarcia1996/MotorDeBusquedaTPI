/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import clases.Datos;
import java.sql.SQLException;

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
    public static void insertarTerminoXDocumento(DBManager db, String palabra, String nombreDoc, int frec_termino)
    {
        String query = "";
        
        try
        {
            if(!DBTermino.existeTermino(db, palabra)){
                DBTermino.insertarTermino(db, palabra);
            }
            int idDoc=DBDocumento.selectDocumentoId(db, nombreDoc).getInt(0);
            query = "INSERT INTO terminoxdocumento(palabra, id_doc, frec_termino) VALUES('" + palabra + "'," + 
                    idDoc + "," + frec_termino + ");";
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
