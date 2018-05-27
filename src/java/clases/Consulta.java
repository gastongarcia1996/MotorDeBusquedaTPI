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
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Gaston
 */
public class Consulta 
{   
    private LinkedList<String> aux = new LinkedList<>();
    
    public LinkedList<Documento> ordenarPorRelevancia(String consulta, Hashtable<String, Termino> ht, int r, int totalDocs) throws Exception
    {
        BigDecimal bd = null;
        LinkedList<String> listAux = procesar_consulta(consulta, ht, r); //lista de documentos de la consulta
        LinkedList<Documento> docs = new LinkedList<>(); //lista a retornar de documentos ordenados por relevancia
        LinkedList<Documento>listRet = new LinkedList<>();
        double peso = 0;
        int rec = 0;
        int i = 1;
        double idf = 0;
        int tf = 0;
        
        Documento mayor = null;
        
        DBManager db = Datos.getSingleDB();
        
        for (String doc : listAux)
        {
            docs.add(new Documento(i ,doc)); //llenar la lista de los documentos
            i++;
        }
        i = 0;
        for (String term : aux)
        {
            for (Documento d : docs)
            {               
                tf = DBTerminoXDocumento.countFrecXDocumento(db, term.replace("'", "''"), d.getNombre_doc());
                idf = Math.log(totalDocs / ht.get(term.replace("''", "'")).getCant_doc_aparece());
                bd = new BigDecimal(tf * idf);
                bd = bd.setScale(2, RoundingMode.HALF_UP);
                peso = bd.doubleValue();
                d.setPeso(d.getPeso() + peso);
            }
        }
        
        while (!docs.isEmpty())
        {
            mayor = docs.getFirst();
            
            for (i = 1; i < docs.size(); i++)
            {
                if (mayor.getPeso() < docs.get(i).getPeso())
                {
                    mayor = docs.get(i);
                    rec = i;
                }
            }
            listRet.add(mayor);
            if (docs.size() != 1) docs.remove(rec);
            else docs.removeFirst();
            rec = 0;            
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
        
        //while (cont < r)
        //{                       
            while (!listAux.isEmpty())
            {
                termino = listAux.getFirst();
                listAux.removeFirst();
            
            
                ResultSet rs = DBTerminoXDocumento.documentosxtermino(db, termino.getPalabra().replace("'", "''"), r);
            
                while(rs.next())
                {    
                    if (!list.contains(rs.getString(1)))
                    {
                        list.add(rs.getString(1));
                    }
                    
                    cont++;
                }
            
            //else break;
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
        int rec = 0;
        
        for (i = 0; st.hasMoreTokens(); i++)
        {
            aux = st.nextToken().trim();
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
                    menor = listAux.get(i);
                    rec = i;
                }
                    
            }
            list.add(menor);
            if (listAux.size() != 1) listAux.remove(rec);
            else listAux.removeFirst(); 
            rec = 0;
        }
        return list;
    }
}
