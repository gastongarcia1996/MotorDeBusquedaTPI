/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import clases.Datos;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Gaston
 */
public abstract class DBDocumento
{

    private final String nombre_doc = "nombre_doc";
    private final String frec_termino = "frec_termino";

    public static boolean existeDocumento(DBManager db, String nombre)
    {
        String query = "SELECT * FROM documentos WHERE nombre = '" + nombre + "';";
        ResultSet rs = null;

        try
        {
            if (db != null)
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
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return false;
    }

    public static ArrayList<String> getDocsList()
    {

        String query = "SELECT nombre FROM documentos";
        ResultSet rs = null;
        ArrayList<String> list = new ArrayList<>();

        try
        {
            DBManager db = Datos.getSingleDB();
            if (db != null)
            {
                rs = db.executeQuery(query);
            }
            else
            {
                db = Datos.getSingleDB();
                rs = db.executeQuery(query);
            }

            while (rs.next())
            {
                String aux = rs.getString(1);
                list.add(aux);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return list;
    }

    public static void insertarDocumento(DBManager db, String nombre)
    {
        try
        {
            int num = 1;
            String query = "INSERT INTO documentos(nombre) VALUES('" + nombre + "');";
            if (db != null)
            {
                db.executeInsert(query);
            }
            else
            {
                db = Datos.getSingleDB();
                db.executeInsert(query);
            }
        }
        catch (Exception e)
        {
            //System.out.println("Error" + e.getMessage());
            System.out.println(e.getClass().getName());
        }
    }

    public static int selectDocumentoId(DBManager db, String nombre)
    {
        String query = "SELECT * FROM documentos WHERE nombre = '" + nombre + "';";
        ResultSet rs = null;

        try
        {
            if (db != null)
            {
                rs = db.executeQuery(query);
            }
            else
            {
                db = Datos.getSingleDB();
                rs = db.executeQuery(query);
            }
            return rs.next() ? rs.getInt("id") : 0;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public static int countDocumentos(DBManager db)
    {
        String query = "SELECT count(id_doc) FROM documentos";
        ResultSet rs = null;

        try
        {
            if (db != null)
            {
                rs = db.executeQuery(query);
            }
            else
            {
                db = Datos.getSingleDB();
                rs = db.executeQuery(query);
            }
            rs.next();
            return rs.getInt(1);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return 0;
    }
}
