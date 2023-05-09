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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ProductGetter;
import model.UserGetter;

public class RemoveFromCart extends HttpServlet {
    Connection conn;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession(false);
            String removedProduct = request.getParameter("remove");
            
            try {
                // connectivity stuff
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
                
                // preliminary calls to the database for getting user ID and product ID
                UserGetter ug = new UserGetter(driver, username, password, url.toString());
                ProductGetter pg = new ProductGetter(driver, username, password, url.toString());
                Integer userID = ug.getUserID((String)session.getAttribute("username"));
                Integer productID = pg.getProduct(removedProduct).getID();
                
                // main call to database
                Class.forName(driver);
                conn = DriverManager.getConnection(url.toString(), username, password);
                
                String query = "DELETE FROM CART WHERE USER_ID=? AND PRODUCT_ID=?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setInt(1, userID);
                ps.setInt(2, productID);
                ps.executeUpdate();
                
                response.sendRedirect("cart.jsp");
            }
            catch (ClassNotFoundException | SQLException e)   {
                e.printStackTrace();
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
