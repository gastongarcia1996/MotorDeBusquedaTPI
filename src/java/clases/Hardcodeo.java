/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import Database.DBManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

/**
 *
 * @author dlcusr
 */
public abstract class Hardcodeo 
{
    private static Hashtable documentos = new Hashtable();
    private static String DBUrl = "jdbc:postgresql://localhost:5432/dlcdb";
    private static String DBUserName = "dlcusr";
    private static String DBPassword = "dlcpwd";

    private static String DBResourceName = "jdbc/pg_dlcdb";

    public static DBManager getSingleDB() throws Exception {
        DBManager db = new DBManager();
        db.setConnectionMode(DBManager.SINGLECONNECTIONMODE);
        db.setDriverName(DBManager.POSTGRESDRIVERNAME);
        db.setUrl(DBUrl);
        db.setUserName(DBUserName);
        db.setPassword(DBPassword);
        db.connect();
        return db;
    }

    public static DBManager getPoolDB() throws Exception {
        DBManager db = new DBManager();
        db.setConnectionMode(DBManager.POOLCONNECTIONMODE);
        db.setResourceName(DBResourceName);
        db.connect();
        return db;
    }
    
    public static void populateDocumentos() throws Exception
    {
        
        if (documentos.isEmpty()) 
        {
            documentos.put(1,(new Documento(1,"Holaa")));
            documentos.put(2,(new Documento(2, "Como andas")));
            documentos.put(3,(new Documento(3, "La vuelta al mundo en 80 dias")));
            documentos.put(4,(new Documento(4, "Me cago en todo")));
            /*
            documentos.put(5,(new Documento(5)));
            documentos.put(6,(new Documento(6)));
            documentos.put(7,(new Documento(7)));
            documentos.put(8,(new Documento(8)));
            documentos.put(9,(new Documento(9)));
            documentos.put(10,(new Documento(10)));
            documentos.put(11,(new Documento(11)));
            documentos.put(12,(new Documento(12)));
            documentos.put(13,(new Documento(13)));
            documentos.put(14,(new Documento(14)));
            documentos.put(15,(new Documento(15)));
            documentos.put(16,(new Documento(16)));
            documentos.put(17,(new Documento(17)));
            documentos.put(18,(new Documento(18)));
            documentos.put(19,(new Documento(19)));
            documentos.put(20,(new Documento(20)));
            documentos.put(21,(new Documento(21)));
            */
        }
    }

    public static Hashtable getDocumentos()
    {
        if(documentos == null) documentos = new Hashtable();
        return documentos;
    }

    public static void setAlumnos(Hashtable documentos) 
    {
        Hardcodeo.documentos = documentos;
    }
  
    public static List getDocumentosList()
    {
        Collection aux = getDocumentos().values();
        return new ArrayList(aux);
    }
    

}
