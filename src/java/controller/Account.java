/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author chris
 */
public class Account extends HttpServlet {

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
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");
            
            RequestDispatcher view = request.getRequestDispatcher("account.jsp");
            view.include(request, response);
            
            HttpSession session = request.getSession();
            if (session.getAttribute("username") == null)   {
                out.println("You've logged out.");
            }
            if (session.getAttribute("username") != null)   {
                String username = (String)session.getAttribute("username"); 
                out.println("<h1>"+username+"'s Account</h1>");
                out.println("<table>");
                out.println("<tr><th>Account Details</th></tr>");
                out.println("<tr>");
                    out.println("<td>Name: </td>");
                    out.println("<td>");

                        if (username.equals("amiel"))
                            out.println("Amiel Mala-ay");
                        if (username.equals("christian"))
                            out.println("Christian Mala-ay");
                        if (username.equals("charles"))
                            out.println("Charles Joaquin");
                        if (username.equals("clarisse"))
                            out.println("Clarisse Salazar");
                        if (username.equals("denise"))
                            out.println("Denise Poblete");
                        if (username.equals("lawrence"))
                            out.println("Lawrence Decamora");

                        out.println("</td>");
                    out.println("</tr>");
                    out.println("<tr>");
                        out.println("<td>Address: </td>");
                        out.println("<td>");

                            if (username.equals("amiel"))
                                out.println("Caloocan");
                            if (username.equals("christian"))
                                out.println("Malabon");
                            if (username.equals("charles"))
                                out.println("Quezon City");
                            if (username.equals("clarisse"))
                                out.println("Mandaluyong");
                            if (username.equals("denise"))
                                out.println("Manila");
                            if (username.equals("lawrence"))
                                out.println("San Juan");

                        out.println("</td>");
                    out.println("</tr>");
                    out.println("<tr>");
                        out.println("<td>Contact Number: </td>");
                        out.println("<td>");

                            if (username.equals("amiel"))
                                out.println("09445817726");
                            if (username.equals("christian"))
                                out.println("09314579962");
                            if (username.equals("charles"))
                                out.println("09817463289");
                            if (username.equals("clarisse"))
                                out.println("09216483729");
                            if (username.equals("denise"))
                                out.println("09323286743");
                            if (username.equals("lawrence"))
                                out.println("09671482569");

                        out.println("</td>");
                    out.println("</tr>");
                out.println("</table>");

                out.println("<h4><a href=\"Logout\">Logout</a></h4>");
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
