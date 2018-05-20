/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Database.DBDocumento;
import Database.DBManager;
import Database.DBTermino;
import Database.DBTerminoXDocumento;
import clases.Datos;

/**
 *
 * @author Nico
 */
public class Resultado {
    public String nomArchivo;
    public String[] terminos;
    public float[] pesosBase;
    public float[] pesos;
    DBManager db=Datos.getSingleDB();
    
    public Resultado(String[] palabras, String nomDoc) throws Exception{
        this.terminos=palabras;
        nomArchivo=nomDoc;
        pesos=new float[terminos.length];
        calcularModuloBase();
    }
    
   public float[] calcularPesos() throws Exception{
        for(int i=0;i<pesos.length;i++){
            float weight=0f;
            int cantDoc=DBDocumento.countDocumentos(db);
            int cantDocAparece= DBTerminoXDocumento.contarDocConTermino(db,terminos[i]);
            int frec=DBTerminoXDocumento.frecuenciaTermino(db,terminos[i],nomArchivo);
            weight=(float) ( (frec*Math.log(cantDocAparece/(float)cantDoc))  /  Math.sqrt(sumatoria())  );
            pesos[i]=weight;
        }
        return pesos;
    }
   private void calcularModuloBase(){
       pesosBase=new float[terminos.length];
       for (int i = 0; i < terminos.length; i++) {
           int cantDoc=DBDocumento.countDocumentos(db);
           int cantDocAparece= DBTerminoXDocumento.contarDocConTermino(db,terminos[i]);
           int frec=DBTerminoXDocumento.frecuenciaTermino(db,terminos[i],nomArchivo);
           pesosBase[i]=(float)(frec*Math.log(cantDocAparece/(float)cantDoc));
       }
       
   }
   
   private float sumatoria(){
       float sumatoria=0;
       for(int i=0;i<pesosBase.length;i++){
           sumatoria+=pesosBase[i]*pesosBase[i];
       }
       return sumatoria;
   }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author a6
 */
/*@WebServlet(name = "buscar", urlPatterns = {"/buscar"})
public class buscar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /*protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
      /*      out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet buscar</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet buscar at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /*@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /*@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}*/