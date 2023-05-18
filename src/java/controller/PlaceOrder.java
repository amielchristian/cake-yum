/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.OrderGetter;
import model.Product;
import model.ProductGetter;
import model.UserGetter;

/**
 *
 * @author chris
 */
public class PlaceOrder extends HttpServlet {
    private Connection conn;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession(false);
            
            // prevent servlet from executing before checking out
            if (session.getAttribute("cart") == null)  {
                response.sendRedirect("cart.jsp");
            }
            else    {
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

                // get user ID
                UserGetter ug = new UserGetter(driver, username, password, url.toString());
                int userID = ug.getUserID((String)session.getAttribute("username"));

                try {
                    Map<Integer,Integer> cart = (Map)session.getAttribute("cart");
                    if (!cart.isEmpty())   {
                        Class.forName(driver);
                        conn = DriverManager.getConnection(url.toString(), username, password);

                        // adds entries to the ORDERS and ORDER_PRODUCTS tables
                        OrderGetter og = new OrderGetter(driver, username, password, url.toString());
                        og.placeOrder(cart, userID);
                    }
                }
                catch (ClassNotFoundException | SQLException e)   {
                    e.printStackTrace();
                }
                session.removeAttribute("cart");
                session.setAttribute("blockCheckout", true);

                response.sendRedirect("purchases.jsp");
            }
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
