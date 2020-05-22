/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator.web;

import calculator.ejb.CalculatorBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fadhli Hisyam
 */
@WebServlet(name = "Calculator", urlPatterns = {"/Calculator"})
public class Calculator extends HttpServlet {

    CalculatorBeanLocal calculatorBean = lookupCalculatorBeanLocal();

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
        double total;
        int count = 0;
        
        if(!request.getParameter("value").isEmpty()) {
            total = calculatorBean.add(Double.parseDouble(request.getParameter("value")));
        }
        else {
            total = calculatorBean.getTotal();
        }
        if(calculatorBean.getCount() != 0) {
            count = calculatorBean.getCount();
        }
        if (request.getParameter("Add") != null) {
        total = calculatorBean.add(Double.parseDouble(request.getParameter("value")));
        }
        if (request.getParameter("Sub") != null) {
        total = calculatorBean.sub(Double.parseDouble(request.getParameter("value")));
        }
        if (request.getParameter("Div") != null) {
        total = calculatorBean.div(Double.parseDouble(request.getParameter("value")));
        }
        if (request.getParameter("Mul") != null) {
        total = calculatorBean.multiple(Double.parseDouble(request.getParameter("value")));
        }
        
        PrintWriter out = response.getWriter();
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
            rd.include(request, response);
        }
        
        catch (IOException | NumberFormatException | ServletException ex) {
            PrintWriter out = response.getWriter();
            out.println("Error: " + ex.getMessage() + "<br />Silahkan isi field dengan angka");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.html");
            rd.include(request, response);
        }
        finally {
            PrintWriter out = response.getWriter();
            out.close();
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

    private CalculatorBeanLocal lookupCalculatorBeanLocal() {
        try {
            Context c = new InitialContext();
            return (CalculatorBeanLocal) c.lookup("java:global/Calculator/Calculator-ejb/CalculatorBean!calculator.ejb.CalculatorBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
