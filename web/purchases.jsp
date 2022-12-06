<%-- 
    Document   : orders
    Created on : Nov 24, 2022, 12:50:10 PM
    Author     : chris
--%>

<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="model.*"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your Orders</title>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Lato:wght@700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Fredoka+One&display=swap" rel="stylesheet"> 
        <link href='https://fonts.googleapis.com/css?family=Cookie' rel='stylesheet'>
        <link href="styles/purchases-styles.css" rel="stylesheet" type="text/css"/>
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
                int current = (Integer)session.getAttribute("orderCounter")-1;
                
                out.println("<div class=\"purchases-body\">");
                out.println("<div class=\"purchases-container\">");
                
                if (current == 0)   {
                    out.println("You haven't placed any orders yet.");
                }
                else    {
                    out.println("<h1 class=\"container-title\">Previous Orders</h1>");
                    for (int i = current; i > 0; i--)   {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
                        String time = ((LocalDateTime)session.getAttribute("orderTime-"+i)).format(formatter);

                        out.println("<div class=\"order\">");
                        out.println("<div class=\"order-heading\">");
                        out.println("<p>Order #"+i+"</p>");
                        out.println("<p>Placed on "+time+"</p>");
                        out.println("</div>");
                        ArrayList cartContents = (ArrayList)session.getAttribute("order-"+i);

                        // Display cart in tabular form
                        out.println("<div class=\"table\" style=\"overflow-x:auto;\">");
                        out.println("<table>");
                        out.println("<tr>");
                        out.println("<th class=\"product-column\">Product Name</th>");
                        out.println("<th class=\"quantity-column\">Qty.</th>");
                        out.println("<th class=\"price-column\">Price</th>");
                        out.println("</tr>");

                        double total = 0;
                        for (int j = 0; j < cartContents.size(); j++)   {
                            Product product = (Product)cartContents.get(j);
                            total += (product.getPrice()*product.getQuantity());

                            out.println("<tr>");
                                out.println("<td class=\"item-name\">"+ProductsModel.formatName(product.getName())+"</td>");    
                                out.println("<td class=\"item-quantity\">"+product.getQuantity()+"</td>");
                                out.println("<td class=\"item-price\">&#8369 "+(product.getPrice()*product.getQuantity())+"</td>");
                            out.println("</tr>");
                        }

                        out.println("<tr class=\"total-row\">");
                        out.println("<td class=\"total-title\" colspan=\"2\">Total: </td>");
                        out.println("<td class=\"order-price\">&#8369 "+total+"</td>");
                        out.println("</tr>");
                        out.println("</table>");
                        out.println("</div>");
                        out.println("</div>");
                        out.println("<br>");
                        
                    }
                }
                out.println("</div>");
                out.println("</div>");
            }

        %>
    </body>
</html>
