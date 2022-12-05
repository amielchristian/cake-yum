/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.*;

/**
 *
 * @author chris
 */
public class Checkout extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Checkout</title>");
            out.println("<link rel=\"stylesheet\" href=\"styles/styles.css\">");
            out.println("</head>");
            out.println("<body>");
            
            // include header
            request.getRequestDispatcher("/header.jsp").include(request, response);
            
            
            out.println("<h1>Checkout</h1>");
            
            HttpSession session = request.getSession(false);
            ArrayList cartContents = (ArrayList)session.getAttribute("order-"+session.getAttribute("orderCounter"));
            
            // Display cart in tabular form
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>Qty.</th>");
            out.println("<th>Product Name</th>");
            out.println("<th>Price</th>");
            out.println("</tr>");
            
            double total = 0;
            for (int i = 0; i < cartContents.size(); i++)   {
                Product product = (Product)cartContents.get(i);
                total += (product.getPrice()*product.getQuantity());
                
                out.println("<tr>");
                    out.println("<td>"+product.getQuantity()+"</td>");
                    out.println("<td>"+ProductsModel.formatName(product.getName())+"</td>");
                    out.println("<td>&#8369 "+(product.getPrice()*product.getQuantity())+"</td>");
                out.println("</tr>");
            }
            
            out.println("<tr>");
            out.println("<td colspan=\"2\">Total: </td>");
            out.println("<td>&#8369 "+total+"</td>");
            out.println("</tr>");
            out.println("</table>");
            
            out.println("<form action=\"PlaceOrder\"><input type=\"submit\" value=\"Place Order\"></form>");
            
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
