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
import model.CartGetter;
import model.UserGetter;

/**
 *
 * @author chris
 */
public class AddToCart extends HttpServlet {
    private Connection conn;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            
            // goes to login page if not logged in
            if (request.getSession(false).getAttribute("username") == null) {
                response.sendRedirect("LoginRedirect");
            }
            else    {
                HttpSession session = request.getSession(false);
                
                // these three integers form an entry in the Cart table in the database
                Integer userID;
                Integer productID = Integer.valueOf(request.getParameter("productID"));
                Integer quantity = Integer.valueOf(request.getParameter("quantity"));
                
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
                    
                    // preliminary calls to the database, to:
                    //     1. get the user ID associated with the username through the UserGetter class
                    //     2. get the list of entries in the Cart table that include the user ID and the ID of the product being processed
                    CartGetter cg = new CartGetter(driver, username, password, url.toString());
                    UserGetter ug = new UserGetter(driver, username, password, url.toString());
                    userID = ug.getUserID((String)session.getAttribute("username")); // gets the user ID associated with the username
                    Map<Integer,Integer> cartContents = cg.getCart(userID); // given the username, gets the rest of the entry in the Cart table associated with that user (

                    // the main calls to the database, either for updating existing entries in the Cart table or adding them
                    Class.forName(driver);
                    conn = DriverManager.getConnection(url.toString(), username, password);
                    
                    // if there is an entry in the database that contains both the user ID and the product ID, then quantity is updated
                    if (cartContents.containsKey(productID))    {
                        String query = "UPDATE CART SET QUANTITY=? WHERE USER_ID=? AND PRODUCT_ID=?";
                        PreparedStatement ps = conn.prepareCall(query);
                        ps.setInt(1, quantity);
                        ps.setInt(2, userID);
                        ps.setInt(3, productID);
                        ps.executeUpdate();
                    }
                    // if there is no such entry, then an entry is added to the database
                    else    {
                        String query = "INSERT INTO CART(USER_ID,PRODUCT_ID,QUANTITY) VALUES ((SELECT USER_ID FROM USERS WHERE USER_UNAME=?),?,?)";
                        PreparedStatement ps = conn.prepareCall(query);
                        ps.setString(1, (String)session.getAttribute("username"));
                        ps.setInt(2,productID);
                        ps.setInt(3, quantity);
                        ps.executeUpdate();
                    }

                    response.sendRedirect("cart.jsp");
                }
                catch (ClassNotFoundException | SQLException e)   {
                    e.printStackTrace();
                }
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
