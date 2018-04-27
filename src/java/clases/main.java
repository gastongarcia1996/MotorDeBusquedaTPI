/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.List;


/**
 *
 * @author dlcusr
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Vocabulario v = new Vocabulario();
        
        v.obtenerDocumentos();
        
        List<Documento> lista = v.getDocumentosList();
        
        for(int i = 1; i < lista.size(); i++)
        {
            System.out.println(lista.get(i).getNombre_doc());
        }
    }
    
}
