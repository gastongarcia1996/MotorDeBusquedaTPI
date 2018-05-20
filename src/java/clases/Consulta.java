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
    private LinkedList<String> aux = new LinkedList<>();
    
    public List ordenarPorRelevancia(String consulta, Hashtable<String, Termino> ht, int r, int totalDocs) throws Exception
    {
        LinkedList<String> listAux = procesar_consulta(consulta, ht, r); //lista de documentos de la consulta
        LinkedList<Documento> docs = new LinkedList<>(); //lista a retornar de documentos ordenados por relevancia
        LinkedList<Documento> listRet = new LinkedList<>();
        double peso = 0;
        int rec = 0;
        int i = 0;
        Documento menor = null;
        
        DBManager db = Datos.getSingleDB();
        
        for (String doc : listAux)
        {
            docs.add(new Documento(doc)); //llenar la lista de los documentos
        }
        
        for (String term : aux)
        {
            for (Documento d : docs)
            {
                peso = (double)(DBTerminoXDocumento.countFrecXDocumento(db, term, d.getNombre_doc()) * 
                        Math.log(totalDocs / ht.get(term).getCant_doc_aparece()));
                d.setPeso(d.getPeso() + peso);
            }
        }
        
        while (!docs.isEmpty())
        {
            menor = docs.getFirst();
            
            for (i = 1; i < docs.size(); i++)
            {
                if (menor.getPeso() > docs.get(i).getPeso())
                {
                    menor = docs.get(i);
                    rec = i;
                }
            }
            if (docs.size() != 1) docs.remove(rec);
            else docs.removeFirst();
            listRet.add(menor);
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
            
            if (listAux.size() > 0)
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
            else break;
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
                this.aux.add(aux);
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
            if (listAux.size() != 1) listAux.remove(i);
            else listAux.removeFirst();
            list.add(menor);
        }
        return list;
    }
    
    
}
