/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Product;
import model.Users;

/**
 *
 * @author chris
 */
public class Login extends HttpServlet {
    Connection conn;
    
    @Override
    public void init(ServletConfig config) throws ServletException   {
        try {
            Class.forName(config.getInitParameter("jdbcClassName"));
            
            String dbUsername = config.getInitParameter("dbUsername");
            String dbPassword = config.getInitParameter("dbPassword");
            StringBuffer url = new StringBuffer(config.getInitParameter("jdbcDriverURL"))
                                .append("://")
                                .append(config.getInitParameter("dbHostName"))
                                .append(":")
                                .append(config.getInitParameter("dbPort"))
                                .append("/")
                                .append(config.getInitParameter("dbName"))
                                .append(config.getInitParameter("addlParams"));
            
            conn = DriverManager.getConnection(url.toString(), dbUsername, dbPassword);
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            try {
                String username = request.getParameter("username");
                String password = request.getParameter("password");

                String query = "SELECT * FROM USERS WHERE UNAME=?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();
                
                List<String> userInfo = new LinkedList();
                while (rs.next())   {
                    userInfo.add(rs.getString("UNAME"));
                    userInfo.add(rs.getString("PASSWORD"));
                    
                    userInfo.add(rs.getString("NAME"));
                    userInfo.add(rs.getString("ADDRESS"));
                    userInfo.add(rs.getString("CONTACT_NUM"));
                }
                
                HttpSession session = request.getSession();
                if (userInfo.get(0).equals(username) && userInfo.get(1).equals(password))  {
                    session.setAttribute("username", username);
                    session.setAttribute("orderCounter", 1);
                    session.setAttribute("order-"+session.getAttribute("orderCounter"), new ArrayList<Product>());
                    session.setAttribute("userInfo", userInfo);

                    response.sendRedirect("index.jsp");
                }
                else    {
                    request.setAttribute("invalidLoginCredentials", "true");
                    response.sendRedirect("login.jsp?invalidLoginCredentials=true");
                }
            }
            catch (SQLException sqle)   {
                sqle.printStackTrace();
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
