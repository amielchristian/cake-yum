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
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nl.captcha.Captcha;

/**
 *
 * @author chris
 */
public class SignUp extends HttpServlet {
    Connection conn;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            try {
                Class.forName((String)getServletContext().getInitParameter("jdbcClassName"));

                String dbUsername = (String)getServletContext().getInitParameter("dbUsername");
                String dbPassword = (String)getServletContext().getInitParameter("dbPassword");
                StringBuffer url = new StringBuffer((String)getServletContext().getInitParameter("jdbcDriverURL"))
                                    .append("://")
                                    .append((String)getServletContext().getInitParameter("dbHostName"))
                                    .append(":")
                                    .append((String)getServletContext().getInitParameter("dbPort"))
                                    .append("/")
                                    .append((String)getServletContext().getInitParameter("dbName"))
                                    .append((String)getServletContext().getInitParameter("addlParams"));

                conn = DriverManager.getConnection(url.toString(), dbUsername, dbPassword);
                
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String fullName = request.getParameter("full-name");
                String address = request.getParameter("address");
                String celNum = request.getParameter("mobile-number");

                try {
                    String query = "INSERT INTO USERS(USER_UNAME, USER_PASSWORD, USER_GIVEN_NAME, USER_ADDRESS, USER_CONTACT_NUM) VALUES (?,?,?,?,?)";
                    PreparedStatement ps = conn.prepareStatement(query);
                    ps.setString(1, username);
                    ps.setString(2, password);
                    ps.setString(3, fullName);
                    ps.setString(4, address);
                    ps.setString(5, celNum);
                    ps.execute();
                    
                    request.setAttribute("user-added", true);
                    response.sendRedirect("login.jsp?user-added=true");
                }
                // this should only happen when the given username is already taken
                catch (SQLException e)    {
                    e.printStackTrace();
                    request.setAttribute("username-taken", "true");
                    response.sendRedirect("login.jsp?username-taken=true");
                }
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
