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
public abstract class DBTermino 
{
    public static void insertarTermino(DBManager db, String palabra)
    {
        try
        {
        int num = 1;
        String query = "INSERT INTO terminos(palabra) VALUES('" + palabra + "');";
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
