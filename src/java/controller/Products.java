/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProductsModel;

/**
 *
 * @author Patricia Denise
 */
public class Products extends HttpServlet {

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
           
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Products</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<body>");
            
            String product = request.getParameter("name");//product name
            String path = getServletContext().getRealPath("/products");
            out.println(path);
            
            // create a list of directories in /products
            List<String> productList = new ArrayList<>();

            File directory = new File(path);
            if (directory.isDirectory()){
               String[] files = directory.list();
               for (String file : files)    {
                   productList.add(file);
               }
            }
            
            // verify if the product is in the list of products
            if (productList.contains(product))    {
                // information about the product will be read from the corresponding directory
                String price = ProductsModel.generatePrice(new File(path + "/" + product + "/price.txt"));
                String description = ProductsModel.generateDescription(new File(path + "/" + product + "/description.txt"));
                
                // attributes will be forwarded to specific-product.jsp
                request.setAttribute("file", product);
                request.setAttribute("formatted-name", ProductsModel.formatName(product));
                request.setAttribute("price", price);
                request.setAttribute("description", description);
                
                RequestDispatcher view = request.getRequestDispatcher("specific-product.jsp");
                view.forward(request, response);    
            }
            else    {
                response.sendRedirect("error-pages/error500.jsp");
            }
            
            out.println("</body>");
            out.println("</html>");
            
            
            
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
