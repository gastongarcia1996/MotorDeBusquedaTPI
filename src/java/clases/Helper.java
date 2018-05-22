/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nico
 */
public class Helper {
        public static Indexacion index =new Indexacion();
        public static boolean indexado=false;
        public Hashtable<String,Termino> ht;
        
        
        public static void armarVocabulario(){
            if(!indexado){
                index.armar_vocabulario();
            }
        }
        
        public static void resetIndexado(){
            indexado=false;
        }
        
        public static int getCantDocumentos(){
            int aux=0;
            aux=index.cantDocs();
            return aux;
        }
        
        public static Hashtable<String,Termino> getHt(){
            return index.getHt();
        }
        
        
        
        public static boolean indexar(){
            try {
                index.armar_posteo(0, 0);
                return true;
            } catch (Exception ex) {
                Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }
}
