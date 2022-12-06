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
            out.println("<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n" +
                        "<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n" +
                        "<link href=\"https://fonts.googleapis.com/css2?family=Lato:wght@700&display=swap\" rel=\"stylesheet\">\n" +
                        "<link href=\"https://fonts.googleapis.com/css2?family=Fredoka+One&display=swap\" rel=\"stylesheet\"> \n" +
                        "<link href='https://fonts.googleapis.com/css?family=Cookie' rel='stylesheet'>");
            out.println("<link rel=\"stylesheet\" href=\"styles/checkout-styles.css\">");
            out.println("</head>");
            out.println("<body>");
            
            // include header
            request.getRequestDispatcher("/header.jsp").include(request, response);
            
            
            out.println("<div class=\"checkout-body\">");
            out.println("<div class=\"checkout-container\">");
            
            out.println("<h1 class=\"container-title\">Checkout</h1>");
            HttpSession session = request.getSession(false);
            ArrayList cartContents = (ArrayList)session.getAttribute("order-"+session.getAttribute("orderCounter"));
            
            
            out.println("<div class=\"order\">");
            // Display cart in tabular form
            out.println("<div class=\"table\" style=\"overflow-x:auto;\">");
            out.println("<table>");
            out.println("<tr>");
            out.println("<th class=\"product-column\">Product Name</th>");
            out.println("<th class=\"quantity-column\">Qty.</th>");
            out.println("<th class=\"price-column\">Price</th>");
            out.println("</tr>");
            
            double total = 0;
            for (int i = 0; i < cartContents.size(); i++)   {
                Product product = (Product)cartContents.get(i);
                total += (product.getPrice()*product.getQuantity());
                
                out.println("<tr>");
                    out.println("<td class=\"item-name\">"+ProductsModel.formatName(product.getName())+"</td>");    
                    out.println("<td class=\"item-quantity\">"+product.getQuantity()+"</td>");
                    out.println("<td class=\"item-price\">&#8369 "+(product.getPrice()*product.getQuantity())+"</td>");
                out.println("</tr>");
            }
            
            out.println("<tr class=\"total-row\">");
            out.println("<td class=\"total-title\" colspan=\"2\">Total: </td>");
            out.println("<td class=\"order-price\">&#8369 "+total+"</td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("</div>");
            out.println("</div>");
            
            out.println("<div class=\"button-container\">");
            out.println("<form name=\"PlaceOrder\" id=\"PlaceOrder\" action=\"PlaceOrder\"></form>");
            out.println("<button class=\"order-button\" form=\"PlaceOrder\" name=\"PlaceOrder\" value=\"PlaceOrder\">Place Order</button>");
            out.println("</div>");
            
            out.println("</div>");
            out.println("</div>");
            
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
