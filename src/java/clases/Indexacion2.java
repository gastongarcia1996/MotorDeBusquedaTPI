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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Nico
 */
public class Indexacion2 implements Serializable {

    private static final long serialVersionUID = -5234473242999323411L;
    public Hashtable<String, Termino> ht = new Hashtable<>();
    private LinkedList<Documento> al = new LinkedList<>();
    private LinkedList<Termino> ll = new LinkedList<>();
    private LinkedList<String> nuevos = new LinkedList<>();
    private int cantidad = 0;
    private boolean cargo = false;
    private File[] lista = null;
    DBManager db = null;
    Indexacion2 indexAux;

    public Indexacion2() {
        if (existe_hashtable("Hashtable")) {
            try {
                leer_hashtable();
            } catch (Exception e) {
                al = new LinkedList<>();
                ht = new Hashtable<>();
            }
        }
    }

    public Indexacion2(File[] lista) {
        this.lista = lista;
    }

    /**
     * Arma el vocabulario del motor;
     */
    public boolean sweepFolder() {
        File fi = new File("C:\\Users\\Nico\\Documents\\Facu\\4º\\DLC\\TP\\MotorDeBusquedaTPI\\DocumentosTPI");
        lista = fi.listFiles();
        ArrayList<String> dblist = DBDocumento.getDocsList();
        for (int i = 0; i < lista.length; i++) {
            String aux = lista[i].getName();
            if (es_txt(lista[i]) && !dblist.contains(aux)) {
                nuevos.add(aux);
                DBDocumento.insertarDocumento(db, aux);
            }
        }
        if (nuevos.size() > 0) {
            Helper.indexado = false;
            return true;
        } else {

            return false;
        }
    }

    public void armar() {

       

            if (existe_hashtable("C:\\Users\\Nico\\Documents\\Facu\\4º\\DLC\\TP\\MotorDeBusquedaTPI\\Hashtable")) {
                try {
                    leer_hashtable();
                    cargo = true;
                } catch (IOException ex) {
                    Logger.getLogger(Indexacion2.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Indexacion2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        
        sweepFolder();
        if (nuevos.size() > 0) {
            if (cargo) {
                vocabularioYposteo();
            } else {

                vocabularioYposteoCompleto();
            }
        } else {
            if (!cargo) {
                vocabularioYposteoCompleto();
            }
        }
        try {
            guardar_hashtable();
        } catch (IOException ex) {
            Logger.getLogger(Indexacion2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void vocabularioYposteo() {
        try {
            String clave = "";
            FileReader fr = null;
            BufferedReader br = null;
            Enumeration enumeration = null;
            Hashtable<String, Termino> hashVoc = new Hashtable<>();
            Hashtable<String, Integer> hashPost = new Hashtable<>();
            StringTokenizer st = null;
            String palabra = "";
            Termino t1 = null;
            String s = "";
            db = Datos.getSingleDB();
            int id_doc = 0;
            //ciclo for que recorre la lista de documentos
            for (int i = 0; i < nuevos.size(); i++) {
                System.out.println("Laburando nuevo archivo:" + nuevos.get(i));
                File fil = new File("C:\\Users\\Nico\\Documents\\Facu\\4º\\DLC\\TP\\MotorDeBusquedaTPI\\DocumentosTPI\\" + nuevos.get(i));
                al.add(new Documento(al.size() + 1, nuevos.get(i)));
                fr = new FileReader(fil);
                br = new BufferedReader(fr);
                hashVoc = new Hashtable<>();
                hashPost = new Hashtable<>();
                id_doc = DBDocumento.selectDocumentoId(db, fil.getName());
                //ciclo que toma cada linea del documento
                while ((s = br.readLine()) != null) {
                    s.toLowerCase();

                    st = new StringTokenizer(s, " ~¼±|\"°!#$%&/()=?¡^¶[]¿-\\/_*³.,«»;:<>+æ’");
                    //ciclo que recorre las palabras y las agrega a una tabla hash
                    //evitando que no se repitan
                    while (st.hasMoreTokens()) {
                        palabra = st.nextToken().toLowerCase();
                        palabra = palabra.replace("'", "''");
                        //si el termino no esta en la hashtable
                        if (!ht.containsKey(palabra)) {
                            t1 = new Termino(palabra);
                            hashVoc.put(palabra, t1);
                            ht.put(palabra, t1);
                            hashPost.put(palabra, 1);
                        } else if (!hashVoc.containsKey(palabra)) {
                            hashVoc.put(palabra, ht.get(palabra));
                            if (hashPost.contains(palabra)) {
                                hashPost.put(palabra, hashPost.get(palabra) + 1);
                            } else {
                                hashPost.put(palabra, 1);
                            }
                        }

                        if (!DBTermino.existeTermino(db, palabra)) {
                            DBTermino.insertarTermino(db, palabra);
                        }

                    }

                }
                br.close();

                enumeration = hashVoc.keys();
                while (enumeration.hasMoreElements()) {
                    clave = (String) enumeration.nextElement();
                    hashVoc.get(clave).setCant_doc_aparece(hashVoc.get(clave).getCant_doc_aparece() + 1);

                }
                insertarABasePosteo(hashPost, id_doc);
                hashVoc = null;
            }
            nuevos = new LinkedList<>();
            db.disconnect();

        } catch (Exception ex) {
            System.out.println("Error al leer el archivo " + ex.getMessage());
        }
    }

    private void vocabularioYposteoCompleto() {
        try {
            String clave = "";
            FileReader fr = null;
            BufferedReader br = null;
            Enumeration enumeration = null;
            Hashtable<String, Termino> hashVoc = new Hashtable<>();
            Hashtable<String, Integer> hashPost = new Hashtable<>();
            StringTokenizer st = null;
            String palabra = "";
            Termino t1 = null;
            String s = "";
            db = Datos.getSingleDB();
            int id_doc = 0;
            //ciclo for que recorre la lista de documentos
            for (int i = 0; i < lista.length; i++) {
                System.out.println("Laburando nuevo archivo:" + lista[i].getName());
                File fil = new File("C:\\Users\\Nico\\Documents\\Facu\\4º\\DLC\\TP\\MotorDeBusquedaTPI\\DocumentosTPI\\" + lista[i].getName());
                al.add(new Documento(al.size() + 1, lista[i].getName()));
                fr = new FileReader(fil);
                br = new BufferedReader(fr);
                hashVoc = new Hashtable<>();
                hashPost = new Hashtable<>();
                id_doc = DBDocumento.selectDocumentoId(db, fil.getName());
                //ciclo que toma cada linea del documento
                while ((s = br.readLine()) != null) {
                    s.toLowerCase();

                    st = new StringTokenizer(s, " ~¼±|\"°!#$%&/()=?¡^¶[]¿-\\/_*³.,«»;:<>+æ’");
                    //ciclo que recorre las palabras y las agrega a una tabla hash
                    //evitando que no se repitan
                    while (st.hasMoreTokens()) {
                        palabra = st.nextToken().toLowerCase();
                        palabra = palabra.replace("'", "''");
                        //si el termino no esta en la hashtable
                        if (!ht.containsKey(palabra)) {
                            t1 = new Termino(palabra);
                            hashVoc.put(palabra, t1);
                            ht.put(palabra, t1);

                        } else if (!hashVoc.containsKey(palabra)) {
                            hashVoc.put(palabra, ht.get(palabra));

                        }
                        if (hashPost.containsKey(palabra)) {
                            hashPost.put(palabra, hashPost.get(palabra) + 1);
                        } else {
                            hashPost.put(palabra, 1);
                        }
                        if (!DBTermino.existeTermino(db, palabra)) {
                            DBTermino.insertarTermino(db, palabra);
                        }

                    }

                }
                br.close();

                enumeration = hashVoc.keys();
                while (enumeration.hasMoreElements()) {
                    clave = (String) enumeration.nextElement();
                    hashVoc.get(clave).setCant_doc_aparece(hashVoc.get(clave).getCant_doc_aparece() + 1);

                }
                insertarABasePosteo(hashPost, id_doc);
                hashPost = new Hashtable<>();
                hashVoc = new Hashtable<>();
            }
            nuevos = new LinkedList<>();
            //db.disconnect();

        } catch (Exception ex) {
            System.out.println("Error al leer el archivo " + ex.getMessage());
        }
    }

    //public void armar_posteo(int desde, int hasta) throws Exception 
    public void armar_posteo() throws Exception {
        String palabra = "";
        int cont = 0;
        int id_doc = 0;
        Termino aux = null;
        Hashtable<String, Termino> hashAux = new Hashtable<>();
        FileReader fr = null;
        BufferedReader br = null;
        db = Datos.getSingleDB();
        ArrayList<String> arrayListAux = DBTermino.selectTerminos(db);
        Hashtable<String, Termino> aux2 = new Hashtable<>();
        StringTokenizer st = null;
        //ArrayList<String> docs = DBDocumento.getDocsList();

        for (String doc : nuevos)//Sacar el 10
        {
            id_doc = DBDocumento.selectDocumentoId(db, doc);
            /*
            if (!(docs.contains(lista[i].getName()))) {
                //JOptionPane.showMessageDialog(null, "Entro al Posteo por: "+lista[i].getName());
                
                
                  if(validarDocumento(db, lista[i]))
                   {
                    fr = new FileReader(lista[i]);
                       br = new BufferedReader(fr);              
                 }
                else continue;
             */
            fr = new FileReader(new File("C:\\Users\\Nico\\Documents\\Facu\\4º\\DLC\\TP\\MotorDeBusquedaTPI\\DocumentosTPI\\" + doc));
            br = new BufferedReader(fr);

            while ((palabra = br.readLine()) != null) {
                st = new StringTokenizer(palabra, " ~¼±|\"°!#$%&/()=?¡^¶[]¿-\\/_*³.,«»;:<>+æ’");

                while (st.hasMoreTokens()) {
                    palabra = st.nextToken().toLowerCase();
                    if (ht.containsKey(palabra)) {
                        aux = ht.get(palabra);
                        aux.setFrec_aparicion(aux.getFrec_aparicion() + 1);

//                            if (!hashAux.containsKey(palabra)) 
//                            {
//                                palabra = palabra.replace("'", "''");
//                                hashAux.put(palabra, aux);
//                            }
                        if (!arrayListAux.contains(palabra)) {
                            DBTermino.insertarTermino(db, palabra);
                        }

                        if (!aux2.containsKey(palabra)) {
                            aux2.put(palabra, aux);
                        }
                    }
                }
            }
            //Recorrido de la hashAux
            //insertarABasePosteo(aux2, id_doc);
            hashAux = new Hashtable<>();
        }
        nuevos = new LinkedList<>();
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
                this.nuevos.add(f.getName());
            }
        } else {
            return false;
        }

        return true;
    }

    private void insertarABasePosteo(Hashtable<String, Integer> hasht, int id_doc) {
        Enumeration e = hasht.keys();
        String clave;
        while (e.hasMoreElements()) {
            clave = (String) e.nextElement();
            DBTerminoXDocumento.insertarTerminoXDocumento(db, clave, id_doc, hasht.get(clave));
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
            File archivo = new File("C:\\Users\\Nico\\Documents\\Facu\\4º\\DLC\\TP\\MotorDeBusquedaTPI\\DocumentosTPI");
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

    public LinkedList<Documento> getDocumentosList() {
        return al;
    }

    public void setDocumentosList(LinkedList<Documento> al) {
        this.al = al;
    }

    public int cantDocs() {
        return lista.length;
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
            System.out.println("Error: " + ex.getMessage());
        }
        return tablaTerminos;
    }

    public void guardar_hashtable() throws IOException {
        File f = new File("C:\\Users\\Nico\\Documents\\Facu\\4º\\DLC\\TP\\MotorDeBusquedaTPI\\Hashtable");
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        //oos.writeObject(crearHash());
        oos.writeObject(al);
        oos.writeObject(ht);
        oos.close();
    }

    public void leer_hashtable() throws IOException, ClassNotFoundException {
        File f = new File("C:\\Users\\Nico\\Documents\\Facu\\4º\\DLC\\TP\\MotorDeBusquedaTPI\\Hashtable");

        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);
        al = (LinkedList) ois.readObject();
        ht = (Hashtable) ois.readObject();
        ois.close();
    }

    public boolean existe_hashtable(String archivo) {
        File f = new File(archivo);

        return (f.exists() && f.isFile());
    }

}
