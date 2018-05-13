/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import clases.Datos;
import java.sql.ResultSet;

/**
 *
 * @author Gaston
 */
public abstract class DBTermino 
{
    public static void insertarTermino(DBManager db, String palabra)
    {
        try
        {
        int num = 1;
        String query = "INSERT INTO terminos VALUES('" + palabra + "');";
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
            System.out.println(e.getMessage());
        }
    }
    
    public static boolean existeTermino(DBManager db, String palabra){
        String query = "SELECT * FROM terminos WHERE palabra = '" + palabra + "'";
        ResultSet rs = null;
        
        try
        {
            if(db != null)
            {
                rs = db.executeQuery(query);
            }
            else
            {
                db = Datos.getSingleDB();
                rs = db.executeQuery(query);
            }
            return rs.next();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
       
        return false;
    }
}
