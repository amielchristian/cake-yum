/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");
            
            HttpSession session = request.getSession();          
            if (session.getAttribute("blockCheckout") != null)  {
                session.removeAttribute("blockCheckout");
                response.sendRedirect("cart.jsp");
            }
            
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
            
            out.println("<div class=\"order\">");
            
            // Display cart in tabular form
            out.println("<div class=\"table\" style=\"overflow-x:auto;\">");
            out.println("<table>");
            out.println("<tr>");
            out.println("<th class=\"product-column\">Product Name</th>");
            out.println("<th class=\"quantity-column\">Qty.</th>");
            out.println("<th class=\"price-column\">Price</th>");
            out.println("</tr>");
            
        // LOGIC
            // for connectivity
            String driver = getServletContext().getInitParameter("jdbcClassName");
            String username = getServletContext().getInitParameter("dbUsername");
            String password = getServletContext().getInitParameter("dbPassword");

            StringBuffer url = new StringBuffer((String)getServletContext().getInitParameter("jdbcDriverURL"))
                        .append("://")
                        .append((String)getServletContext().getInitParameter("dbHostName"))
                        .append(":")
                        .append((String)getServletContext().getInitParameter("dbPort"))
                        .append("/")
                        .append((String)getServletContext().getInitParameter("dbName"))
                        .append((String)getServletContext().getInitParameter("addlParams"));
            // redirect to error page when cart is empty
            UserGetter ug = new UserGetter(driver, username, password, url.toString());
            CartGetter cg = new CartGetter(driver, username, password, url.toString());
            
            // the map generated below represents the entries in the cart that were selected in the previous screen
            List<Object> array = Arrays.asList(request.getParameterMap().keySet().toArray());
            Map<Integer,Integer> cart = new HashMap();
            for (Object o : array)  {
                String s = (String) o;
                Integer i = Integer.valueOf(s);
                cart.put(i, Integer.valueOf(request.getParameter(s)));
            }
            
            double total = 0;
            for (Map.Entry<Integer,Integer> entry : cart.entrySet())   {
                Integer productID = entry.getKey();
                Integer quantity = entry.getValue();
                
                ProductGetter pg = new ProductGetter(driver, username, password, url.toString());
                Product product = pg.getProduct(productID);
                
                total += product.getPrice() * quantity;
                
                out.println("<tr>");
                    out.println("<td class=\"item-name\">"+product.getName()+"</td>");    
                    out.println("<td class=\"item-quantity\">"+quantity+"</td>");
                    out.println("<td class=\"item-price\"><b>&#8369</b>"+String.format("%.2f", (product.getPrice() * quantity))+"</td>");
                out.println("</tr>");
            }
            session.setAttribute("cart", cart);
            
            out.println("<tr class=\"total-row\">");
            out.println("<td class=\"total-title\" colspan=\"2\">Total: </td>");
            out.println("<td class=\"order-price\"><b>&#8369</b>"+String.format("%.2f", total)+"</td>");
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
