<%-- 
    Document   : cart
    Created on : Nov 24, 2022, 12:47:06 PM
    Author     : chris
--%>

<%@page import="model.ProductsModel"%>
<%@page import="model.Product"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Cart</title>
        <link rel="stylesheet" href="styles/styles.css">
    </head>
    <body>
        <%
        /*
        Cache Scriptlet
        */
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");
        %>
        
        <!-- HEADER -->
        <%@ include file = "header.jsp" %>
        <!-- END OF HEADER -->
        
        <%
            if (session.getAttribute("username") == null)  {
                response.sendRedirect("login.jsp");
            }
            else    {
                ArrayList cartContents = (ArrayList)session.getAttribute("order-"+session.getAttribute("orderCounter"));
                if (cartContents.isEmpty())   {
                    out.println("<p>It doesn't seem like you have anything in your cart right now.</p>");
                }

                else    {
                    out.println("<h1>Your Cart</h1>");
                    out.println("<form name=\"remove\" id=\"remove\" action=\"RemoveFromCart\"></form>");
                    for (int i = 0; i < cartContents.size(); i++)   {
                        Product product = (Product)cartContents.get(i);
                    
                        out.println("<div class=\"product\">");   
                            out.println("<img width=\"200\" src=\"products/"+product.getName()+"/"+product.getName()+".jpg\">");
                            out.println("<p><a href=\"Products?name="+product.getName()+"\">"+ProductsModel.formatName(product.getName())+"</a></p>");
                            out.println("<p>Price per unit: &#8369 "+product.getPrice()+"</p>");
                            out.println("<p>Quantity: "+product.getQuantity()+"</p>");
                            out.println("<button form=\"remove\" name=\"remove\" value=\""+product.getName()+"\">Remove</button>");
                        out.println("</div><br>");
                    }

                    out.println("<form action=\"Checkout\"><input type=\"submit\" value=\"Checkout\"></form>");
                }
            }
        %>
    </body>
</html>
