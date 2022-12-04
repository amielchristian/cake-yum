<%-- 
    Document   : products.jsp
    Created on : 12 1, 22, 3:04:28 AM
    Author     : Patricia Denise
--%>

<%@page import="model.ProductsModel"%>
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
                        String path = getServletContext().getRealPath("/products");
                        File directory = new File(path);
                        if (directory.isDirectory()) {
                            String[] files = directory.list();
                            for (String file : files) {
                                String formattedName = ProductsModel.formatName(file);
                                String price = ProductsModel.generatePrice(new File(path + "\\" + file + "\\price.txt")); //temporary
                                out.println("<a href =\"Products?name=" + file + "\" class=\"card\">");
                                out.println("<img src=\"products/" + file + "/" + file + ".jpg\">");
                                out.println("<description>");
                                out.println("<h2>" + formattedName + "</h2>");
                                out.println("<h3>" + price + "</h3>");
                                out.println("</description>");
                                out.println("</a>");
                            }
                        }
                    }
                %>
            </div>
    </body>
</html>
