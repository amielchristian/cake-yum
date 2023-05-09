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
import model.Product;
import model.ProductGetter;

/**
 *
 * @author Patricia Denise
 */
public class Products extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
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

            ProductGetter pg = new ProductGetter(driver, username, password, url.toString());
            String productName = request.getParameter("name");//product name
            Product product = pg.getProduct(productName);

            // attributes will be forwarded to specific-product.jsp
            request.setAttribute("altName", product.getAltName());
            request.setAttribute("name", product.getName());
            request.setAttribute("price", String.format("%.2f",product.getPrice()));
            request.setAttribute("description", product.getDescription());
            request.setAttribute("imageString", product.getImageString());
            request.setAttribute("productID", product.getID());

            RequestDispatcher view = request.getRequestDispatcher("specific-product.jsp");
            view.forward(request, response);
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
