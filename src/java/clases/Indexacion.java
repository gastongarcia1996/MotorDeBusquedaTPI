
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import Database.DBDocumento;
import Database.DBManager;
import Database.DBTermino;
import Database.DBTerminoXDocumento;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dlcusr
 */
public class Indexacion implements Serializable {

    private Hashtable<String, Termino> ht = new Hashtable<>();
    private ArrayList<Documento> al = new ArrayList<>();
    private LinkedList<Termino> ll = new LinkedList<>();
    private int cantidad = 0;
    private StringTokenizer st = null;
    private File[] lista = null;
    DBManager db = null;

    public Indexacion() {
    }

    public Indexacion(File[] lista) {
        this.lista = lista;
    }

    /**
     * Arma el vocabulario del motor;
     */
    public void armar_vocabulario() {
        Termino t1 = null;
        String aux = "";
        String aux2 = "";
        int countMax = 0;
        int countMaxAux = 0;
        FileReader fr = null;
        BufferedReader br = null;
        ArrayList<Termino> listAux = new ArrayList<>();

        try {
            db = Datos.getSingleDB();

            String s = "";
            //File archivo = new File("/home/dlcusr/NetBeansProjects/MotorDeBusquedaTPI/DocumentosTPI");

            if (lista == null) {
                File archivo = new File("C:\\Users\\gasto\\Documents\\NetBeansProjects\\MotorDeBusquedaTPI\\DocumentosTPI");
                lista = archivo.listFiles();
            }

            //ciclo for que recorre la lista de documentos
            for (int i = 0; i < lista.length; i++) {
                al.add(new Documento(i, lista[i].getName()));

                if (validarDocumento(db, lista[i])) {
                    fr = new FileReader(lista[i]);
                    br = new BufferedReader(fr);
                } else {
                    continue;
                }

//                if(!DBDocumento.existeDocumento(db, lista[i].getName()))
//                {
//                    DBDocumento.insertarDocumento(db, lista[i].getName());
//                }
                //DBDocumento.insertarDocumento(db, lista[i].getName());
                //ciclo que toma cada linea del documento
                while ((s = br.readLine()) != null) {

                    st = new StringTokenizer(s, " |\"°!#$%&/()=?¡^[]¿-\\/_*.,;:<>+");

                    //ciclo que recorre las palabras y las agrega a una tabla hash
                    //evitando que no se repitan
                    while (st.hasMoreTokens()) {
                        aux = st.nextToken().toLowerCase();

                        //aux = aux.replace("'", "''");
                        //si el termino no esta en la hashtable
                        if (!ht.containsKey(aux)) {
                            t1 = new Termino(aux);

                            aux = aux.replace("'", "''");
                            /*
                            if(!DBTermino.existeTermino(db, aux))
                            {
                                DBTermino.insertarTermino(db, aux);
                            }       
                             */
                            aux = aux.replace("''", "'");
                            ht.put(aux, t1);
                        }
                        listAux.add(ht.get(aux));
                    }

                }
                br.close();
                for (Termino iterador : listAux) {
                    iterador.setCant_doc_aparece(iterador.getCant_doc_aparece() + 1);
                }
                listAux = new ArrayList<>();
            }
            //db.disconnect();
        } catch (Exception e) {
            System.out.println("Error al leer el archivo " + e.getMessage());
        }

        for (Termino t : listAux) {
            System.out.println(t.toString());
        }
    }

    public void armar_posteo(int desde, int hasta) throws Exception {
        String palabra = "";
        int cont = 0;
        int id_doc = 0;
        Termino aux = null;
        Hashtable<String, Termino> hashAux = new Hashtable<>();
        FileReader fr = null;
        BufferedReader br = null;
        db = Datos.getSingleDB();
        int i;

        for (i = desde; i < hasta; i++)//Sacar el 10
        {

            /*
            NO HACE FALTA COMPROBAR PORQUE EN armar_vocabulario() LO VALIDO
           if(validarDocumento(db, lista[i]))
           {
               fr = new FileReader(lista[i]);
               br = new BufferedReader(fr);              
           }
           else continue;
             */
            fr = new FileReader(lista[i]);
            br = new BufferedReader(fr);

            while ((palabra = br.readLine()) != null) {
                st = new StringTokenizer(palabra, " |\"°!#$%&/()=?¡^[]¿-\\/_*.,;:<>+");

                while (st.hasMoreTokens()) {
                    palabra = st.nextToken().toLowerCase();
                    if (ht.containsKey(palabra)) {
                        aux = ht.get(palabra);
                        aux.setFrec_aparicion(aux.getFrec_aparicion() + 1);

                        if (!hashAux.containsKey(palabra)) {
                            palabra = palabra.replace("'", "''");
                            hashAux.put(palabra, aux);
                        }
                    }

                }
            }
            //Recorrido de la hashAux
            insertarABasePosteo(hashAux, i + 1);
            hashAux = new Hashtable<>();
        }
        db.disconnect();
    }

    /**
     * Metodo que valida que sea txt y si esta o no en la base de datos
     *
     * @param db DBManager
     * @param f El archivo a validar
     * @return true sale todo bien, false en caso contrario
     */
    private boolean validarDocumento(DBManager db, File f) {
        if (es_txt(f)) {
            if (!DBDocumento.existeDocumento(db, f.getName())) {
                DBDocumento.insertarDocumento(db, f.getName());
            }
        } else {
            return false;
        }

        return true;
    }

    private void insertarABasePosteo(Hashtable<String, Termino> ht, int id_doc) {
        Enumeration e = ht.keys();
        String clave;
        while (e.hasMoreElements()) {
            clave = (String) e.nextElement();
            DBTerminoXDocumento.insertarTerminoXDocumento(db, clave, id_doc, ht.get(clave).getFrec_aparicion());
            clave = clave.replace("''", "'");
            this.ht.get(clave).setFrec_aparicion(0);
        }
    }

    public File[] getLista() {
        return lista;
    }

    public void obtenerDocumentos() {
        try {
            db = Datos.getSingleDB();
            File archivo = new File("C:\\Users\\gasto\\Documents\\NetBeansProjects\\MotorDeBusquedaTPI\\DocumentosTPI");
            File[] lista = archivo.listFiles();

            //ciclo for que recorre la lista de documentos
            for (int i = 0; i < lista.length; i++) {
                //sl.add(new Documento(i, lista[i].getName()));
                DBDocumento.insertarDocumento(db, lista[i].getName());
            }
            db.disconnect();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public List getDocumentosList() {
        return al;
    }

    public Hashtable<String, Termino> getHt() {
        return ht;
    }

    public void setHt(Hashtable<String, Termino> ht) {
        this.ht = ht;
    }

    /**
     * Metodo que recibe un archivo y valida que sea de texto
     *
     * @param f: el archivo a validar
     * @return true si es un archivo txt y false en caso contrario
     */
    private boolean es_txt(File f) {
        return f.getName().endsWith(".txt") || f.getName().endsWith(".TXT");
    }

    public void setLista(File[] lista) {
        this.lista = lista;
    }

    public Hashtable<String, Termino> crearHash() {
        Hashtable<String, Termino> tablaTerminos = new Hashtable<>();
        try {
            db = Datos.getSingleDB();

            int cantTerminos = DBTermino.countTerminos(db);
            ArrayList<String> terminos = DBTermino.selectTerminos(db);
            for (int i = 0; i < cantTerminos; i++) {
                String word = terminos.get(i);
                int frecMax = DBTerminoXDocumento.frecuenciaMaxTermino(db, word);
                int cantApar = DBTerminoXDocumento.contarDocConTermino(db, word);
                Termino term = new Termino(word, cantApar, frecMax);
                tablaTerminos.put(word, term);
            }

        } catch (Exception ex) {

        }
        return tablaTerminos;
    }
}
