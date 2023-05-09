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
public class Login extends HttpServlet {

    Connection conn;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            try {
                Class.forName((String) getServletContext().getInitParameter("jdbcClassName"));

                String dbUsername = (String) getServletContext().getInitParameter("dbUsername");
                String dbPassword = (String) getServletContext().getInitParameter("dbPassword");
                StringBuffer url = new StringBuffer((String) getServletContext().getInitParameter("jdbcDriverURL"))
                        .append("://")
                        .append((String) getServletContext().getInitParameter("dbHostName"))
                        .append(":")
                        .append((String) getServletContext().getInitParameter("dbPort"))
                        .append("/")
                        .append((String) getServletContext().getInitParameter("dbName"))
                        .append((String) getServletContext().getInitParameter("addlParams"));

                conn = DriverManager.getConnection(url.toString(), dbUsername, dbPassword);

                String username = request.getParameter("username");
                String password = request.getParameter("password");

                String query = "SELECT * FROM USERS WHERE USER_UNAME=?";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();

                List<String> userInfo = new LinkedList();
                while (rs.next()) {
                    userInfo.add(rs.getString("USER_UNAME"));
                    userInfo.add(rs.getString("USER_PASSWORD"));

                    userInfo.add(rs.getString("USER_GIVEN_NAME"));
                    userInfo.add(rs.getString("USER_ADDRESS"));
                    userInfo.add(rs.getString("USER_CONTACT_NUM"));
                }

                HttpSession session = request.getSession();

                String captchaInput = request.getParameter("captcha-input");
                Captcha captcha = (Captcha) session.getAttribute("captcha");
                String verify = captcha.getAnswer();
                try {
                    if (userInfo.get(0).equals(username) && userInfo.get(1).equals(password) && verify.equals(captchaInput)) {
                        session.setAttribute("username", username);
                        session.setAttribute("userInfo", userInfo);

                        response.sendRedirect("index.jsp");
                    } else {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    String path;
                    if (!verify.equals(captchaInput)) {
                        path = "invalidCaptcha";
                    }
                    else
                        path = "invalidLoginCredentials";
                            
                    request.setAttribute(path, "true");
                    response.sendRedirect("login.jsp?" + path + "=true");
                }

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
