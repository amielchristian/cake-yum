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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UserGetter;
import nl.captcha.Captcha;

/**
 *
 * @author chris
 */
public class Delete extends HttpServlet {
    Connection conn;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            try {
                HttpSession session = request.getSession();
                
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
                Class.forName(driver);
                conn = DriverManager.getConnection(url.toString(), username, password);
                
                // get user ID
                UserGetter ug = new UserGetter(driver, username, password, url.toString());
                int userID = ug.getUserID((String)session.getAttribute("username"));
                
                // create DELETE queries
                List<String> queries = new ArrayList<>();
                queries.add("DELETE FROM ORDER_PRODUCTS WHERE ORDER_ID IN (SELECT ORDER_ID FROM ORDERS WHERE USER_ID=?)");
                queries.add("DELETE FROM ORDERS WHERE USER_ID=?");
                queries.add("DELETE FROM CART WHERE USER_ID=?");
                queries.add("DELETE FROM USERS WHERE USER_ID=?");
                
                // execute DELETE queries
                for (String query : queries)    {
                    PreparedStatement ps = conn.prepareStatement(query);
                    ps.setInt(1, userID);
                    ps.execute();
                }
                
                // clear session and "log out"
                session.removeAttribute("username");
                session.invalidate();

                response.sendRedirect("index.jsp");
            } catch (ClassNotFoundException | SQLException e) {
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
