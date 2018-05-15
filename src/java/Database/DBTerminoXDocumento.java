/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import clases.Datos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    
    public static int contarDocumentos(DBManager db){
        String query = "";
        ResultSet rs=null;
        try
        {
            query = "SELECT count(*) FROM documentos ;";
            if(db != null)
            {
                rs=db.executeQuery(query);
            }
            else
            {
                db = Datos.getSingleDB();
                rs=db.executeQuery(query);
                
            }
            return rs.getInt(0);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        
        return 0;
    }
    
    public static int contarDocConTermino(DBManager db, String palabra){
        String query = "";
        ResultSet rs=null;
        try
        {
            query = "SELECT count(id_doc) FROM terminoxdocumento WHERE palabra = "+palabra+";";
            if(db != null)
            {
                rs=db.executeQuery(query);
            }
            else
            {
                db = Datos.getSingleDB();
                rs=db.executeQuery(query);
                
            }
            return rs.getInt(0);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        
        return 0;
    }
    
    public static int frecuenciaTermino(DBManager db, String palabra, String nombreDoc){
                String query = "";
        ResultSet rs=null;
        try
        {
            int idDoc=DBDocumento.selectDocumentoId(db, nombreDoc).getInt(0);
            query = "SELECT frec_termino FROM terminoxdocumento WHERE palabra = "+palabra+" AND id_doc = "+idDoc+";";
            if(db != null)
            {
                rs=db.executeQuery(query);
            }
            else
            {
                db = Datos.getSingleDB();
                rs=db.executeQuery(query);
                
            }
            return rs.getInt(0);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        
        return 0;
    }
    

    public static int frecuenciaMaxTermino(DBManager db, String word) {
        String query = "SELECT MAX(terminoxdocumento.frec_termino)  FROM terminoxdocumento WHERE palabra = '"+word+"'\n" +
"GROUP BY terminoxdocumento.id_doc\n" +
"ORDER BY MAX(terminoxdocumento.frec_termino) DESC";
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
            rs.next();
                
            return rs.getInt(1);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
       
        return 0;
    
    }

    public static int contarDocConTermino(DBManager db, String word) {
        String query = "SELECT count(id_doc) FROM terminoxdocumento WHERE palabra = '"+word+"';";
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
            rs.next();
            return rs.getInt(1);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
       
        return 0;
    
    }

    public static int frecuenciaTermino(DBManager db, String palabra, String nomArchivo) {
        int idDoc=DBDocumento.selectDocumentoId(db, nomArchivo);
        String query = "SELECT frec_termino FROM terminoxdocumento WHERE palabra = '"+palabra+"' AND id_doc = '"+idDoc+"';";
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
            rs.next();
                
            return rs.getInt(1);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
       
        return 0;
    
    }

}
