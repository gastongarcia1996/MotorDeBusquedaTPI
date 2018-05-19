/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import Database.DBDocumento;
import Database.DBManager;
import Database.DBTerminoXDocumento;
import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author Gaston
 */
public class Consulta 
{   
    
    
    public List ordenarPorRelevancia(String consulta, Hashtable<String, Termino> ht, int r, ArrayList<Documento> docs) throws Exception
    {
        int peso = 0;
        LinkedList<String> listAux = procesar_consulta(consulta, ht, r);
        List listRet = null;
        DBManager db = Datos.getSingleDB();
        
        for (String term : listAux)
        {
            //peso += DBDocumento.countFrecXDocumento(db, term, listA * Math.log(docs.size() / ht.get(term).getCant_doc_aparece());
        }
        
        
        return listRet;
    }
    
    public LinkedList procesar_consulta(String consulta, Hashtable<String, Termino> ht, int r) throws Exception
    {
        int cont = 0;
        String aux = "";
        LinkedList<String> list = new LinkedList<>();
        DBManager db = Datos.getSingleDB();
        LinkedList<Termino> listAux = menorCantDocAparece(consulta, ht);
        Termino termino = null;
        
        if (r <= 0) r = 6;
        
        while (cont < r)
        {                       
            
            termino = listAux.getFirst();
            listAux.removeFirst();
            
            ResultSet rs = DBTerminoXDocumento.documentosxtermino(db, termino.getPalabra(), r - cont);
            
            while(rs.next())
            {                    
                list.add(rs.getString(1));
                cont++;
            }
            
        }
        
        return list;
    }
    
    private LinkedList<Termino> menorCantDocAparece(String consulta, Hashtable<String, Termino> ht)
    {
        StringTokenizer st = new StringTokenizer(consulta, " ~¼±|\"°!#$%&/()=?¡^¶[]¿-\\/_*³.,«»;:<>+æ’");
        LinkedList<Termino> list = new LinkedList<>();
        LinkedList<Termino> listAux = new LinkedList<>();
        Termino menor = null;
        
        String aux = "";
        int i = 0;
        
        for (i = 0; st.hasMoreTokens(); i++)
        {
            aux = st.nextToken();
            if (ht.containsKey(aux))
            {
                listAux.add(ht.get(aux));
            }            
        }
        while (!listAux.isEmpty())
        {
            menor = listAux.getFirst();
            for (i = 1; i < listAux.size(); i++)
            {
                if (menor.getCant_doc_aparece() > listAux.get(i).getCant_doc_aparece())
                {
                    menor = list.get(i);
                }
            }
            listAux.remove(i);
            list.add(menor);
        }
        return list;
    }
    
    
}
