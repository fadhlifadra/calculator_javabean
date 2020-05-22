/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator.web;

import java.io.IOException;
import java.io.PrintWriter;
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
        try {
            double total;
            double plus;
            double minus;
            double mul;
            double div;
            int count = 0;
            
            request.setAttribute("display", 4);
            if(!request.getParameter("value").isEmpty()) {
                total = calculatorBean.add(Double.parseDouble(request.getParameter("value")));
            }
            else {
                total = calculatorBean.getTotal();
            }
            
            if(calculatorBean.getCount() != 0) {
                count = calculatorBean.getCount();
            }
            if (request.getParameter("plus") != null) {
                plus = calculatorBean.getPlus();
            }
            if (request.getParameter("minus") != null) {
                minus = calculatorBean.getMinus();
            }
            if (request.getParameter("times") != null) {
                mul = calculatorBean.getMul();
            }
            if (request.getParameter("div") != null) {
                div = calculatorBean.getDiv();
            }
            
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

}
