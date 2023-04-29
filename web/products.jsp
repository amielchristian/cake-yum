<%-- 
    Document   : products.jsp
    Created on : 12 1, 22, 3:04:28 AM
    Author     : Patricia Denise
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.Product"%>
<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="java.sql.Blob"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="model.ProductGetter"%>
<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Products</title> 
        <link href='https://fonts.googleapis.com/css?family=Cookie' rel='stylesheet'>
        <link rel="stylesheet" href="styles/products-styles.css">
    </head>
    <body>

        <%
            /*
            Cache Scriptlet
             */
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");

            session.setAttribute("invalidCredentials", false);
        %>

        <!-- HEADER -->
        <%@ include file = "header.jsp" %>
        <!-- END OF HEADER -->

        <div class="main">
            <div class="products-container">
                <% 
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
                    ArrayList<Product> products = pg.getProducts();
                    
                    for (int i = 0; i < products.size(); i++) {
                        Product product = products.get(i);
                    
                        String name = product.getName();
                        String price = "Price: <b>&#8369</b>"+String.format("%.2f",product.getPrice());
                        String altname = product.getAltName();
                        String imageString = product.getImageString();
                        
                        out.println("<a href =\"Products?name=" + altname + "\" class=\"card\">");
                        out.println("<img src=\"data:img/jpg;base64," + imageString + "\">");
                        out.println("<description>");
                        out.println("<h2>" + name + "</h2>");
                        out.println("<h3>" + price + "</h3>");
                        out.println("</description>");
                        out.println("</a>");
                    }
                %>
            </div>
    </body>
</html>
