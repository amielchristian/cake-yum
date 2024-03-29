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
import model.AccountAccessor;
import nl.captcha.Captcha;

/**
 *
 * @author chris
 */
public class SignUp extends HttpServlet {
    private Connection conn;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            try {
                String driver = getServletContext().getInitParameter("jdbcClassName");
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
                Security sec = new Security(getServletContext().getInitParameter("key"), getServletContext().getInitParameter("cipherTransformation"));
               
                HttpSession session = request.getSession();
                
                String captchaInput = request.getParameter("captcha-input");
                Captcha captcha = (Captcha) session.getAttribute("captcha");
                String verify = captcha.getAnswer();
                try {
                    if (verify.equals(captchaInput)) {
                        AccountAccessor aa = new AccountAccessor(driver, dbUsername, dbPassword, url.toString());
                        aa.signup(username, sec.encrypt(password), fullName, address, celNum);
                        
                        response.sendRedirect("signup.jsp?user-added=true");
                    }
                    else    {
                        response.sendRedirect("signup.jsp?invalid-captcha=true");
                    }
                }
                // this should only happen when the given username is already taken
                catch (SQLException e)    {
                    e.printStackTrace();
                    response.sendRedirect("signup.jsp?username-taken=true");
                }
            }
            catch (SQLException e)   {
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
