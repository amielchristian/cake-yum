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
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"styles/account-styles.css\">");
            out.println("<link href='https://fonts.googleapis.com/css?family=Cookie' rel='stylesheet'>");
                    
            RequestDispatcher view = request.getRequestDispatcher("account.jsp");
            view.include(request, response);
            
            HttpSession session = request.getSession();
            if (session.getAttribute("username") == null)   {
                out.println("You've logged out.");
            }
            if (session.getAttribute("username") != null)   {
                String username = (String)session.getAttribute("username"); 
                out.println("<div class=\"main\">");
                out.println("<div class=\"dot\"></div>");
                out.println("<h1 class=\"name\">"+username+"'s Account</h1>");
                out.println("<h3 class=\"account\">Account Details</h3>");
                out.println("<table>");
                    out.println("<tr>");
                        out.println("<td>Name: </td>");
                        out.println("<td>");

                            String name = (String)getServletConfig().getInitParameter(username+"-name");
                            out.println(name);

                        out.println("</td>");
                    out.println("</tr>");
                    out.println("<tr>");
                            out.println("<td>Address: </td>");
                            out.println("<td>");

                            String address = (String)getServletConfig().getInitParameter(username+"-address");
                            out.println(address);

                            out.println("</td>");
                    out.println("</tr>");
                    out.println("<tr>");
                        out.println("<td>Contact Number: </td>");
                        out.println("<td>");

                            String number = (String)getServletConfig().getInitParameter(username+"-number");
                            out.println(number);

                        out.println("</td>");
                    out.println("</tr>");
                out.println("</table>");

                out.println("<h4><a class=\"logout\" href=\"Logout\">Logout</a></h4>");
                out.println("</div>");
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
