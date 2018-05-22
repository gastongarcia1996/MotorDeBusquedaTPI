/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import clases.Consulta;
import clases.Documento;
import clases.Helper;
import clases.Indexacion;
import clases.Termino;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.ErrorMsg;

/**
 *
 * @author Gaston
 */
public class CtrlBusqueda extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    static Indexacion index=Helper.index;
    static Hashtable<String, Termino> ht;
    private static boolean indexado=false;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        if(!indexado){
            Helper.armarVocabulario();
            indexado=true;
        }
        index=Helper.index;
        ht = Helper.getHt();
        String titulo = "Error";
        String dest = "/error.jsp";
        ErrorMsg errorMsg = null;
        
        LinkedList<Documento> listDoc = new LinkedList();

        
        int cant_doc = 0;
        
        try
        {
            Consulta c = new Consulta();
            String consulta = request.getParameter("consulta");
            consulta = consulta.toLowerCase();
            cant_doc = index.getDocumentosList().size();
            List documentos = c.ordenarPorRelevancia(consulta, index.getHt(), 25, cant_doc);
            request.setAttribute("consulted", consulta); //Devuelve la consulta buscada
            request.setAttribute("documentos", documentos); 
            dest = "/index.jsp";
        }
        catch (Exception e)
        {
            errorMsg = new ErrorMsg(e.getMessage(), e.getMessage());
            request.setAttribute("errorMsg", errorMsg);
        }
        
        ServletContext app = this.getServletContext();
        RequestDispatcher disp = app.getRequestDispatcher(dest);
        disp.forward(request, response);
        
    }
    
    public static void setIndexado(boolean b){
        indexado=b;
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
    @Override
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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
