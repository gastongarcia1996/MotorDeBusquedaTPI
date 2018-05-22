/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
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
public class CtrlDocumento extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        ErrorMsg errorMsg = null;
        String titulo = "Error";
        String dest = "/error.jsp";
                
        try
        {
            //File f = new File(request.getParameter("documento"));
            String att=request.getQueryString();
            int ind=att.indexOf("=");
            att=att.substring(ind+1);
            String docPath="C:\\Users\\Nico\\Documents\\Facu\\4º\\DLC\\TP\\MotorDeBusquedaTPI\\DocumentosTPI\\"+att;
            String documentString=translateDoc(docPath);
            
            request.setAttribute("docName", att);
            request.setAttribute("docPath", docPath);
            request.setAttribute("documentString", documentString);
            dest = "/documento.jsp";
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

    private String translateDoc(String path) throws FileNotFoundException, IOException{
        File file=new File(path);
        String aux="", text="";
//        FileReader fr = new FileReader(file);
//        BufferedReader br = new BufferedReader(fr);
        Scanner sc= new Scanner(file);
        while (sc.hasNext())
                {
                    aux = sc.nextLine();
                    text+=aux+"\n";
                }

        return text;
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
