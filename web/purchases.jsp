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
                int current = (Integer)session.getAttribute("orderCounter")-1;

                if (current == 0)   {
                    out.println("You haven't placed any orders yet.");
                }
                else    {
                    out.println("<h1>Previous Orders</h1>");
                    for (int i = current; i > 0; i--)   {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
                        String time = ((LocalDateTime)session.getAttribute("orderTime-"+i)).format(formatter);

                        out.println("<div>");
                        out.println("<p>Order #"+i+"</p>");
                        out.println("<p>Placed on "+time+"</p>");
                        ArrayList cartContents = (ArrayList)session.getAttribute("order-"+i);

                        // Display cart in tabular form
                        out.println("<table>");
                        out.println("<tr>");
                        out.println("<th>Qty.</th>");
                        out.println("<th>Product Name</th>");
                        out.println("<th>Price</th>");
                        out.println("</tr>");

                        double total = 0;
                        for (int j = 0; j < cartContents.size(); j++)   {
                            Product product = (Product)cartContents.get(j);
                            total += (product.getPrice()*product.getQuantity());

                            out.println("<tr>");
                                out.println("<td>"+product.getQuantity()+"</td>");
                                out.println("<td>"+ProductsModel.formatName(product.getName())+"</td>");
                                out.println("<td>&#8369 "+(product.getPrice()*product.getQuantity())+"</td>");
                            out.println("</tr>");
                        }

                        out.println("<tr>");
                        out.println("<td colspan=\"2\">Total: </td>");
                        out.println("<td>&#8369 "+total+"</td>");
                        out.println("</tr>");
                        out.println("</table>");
                        out.println("</div>");
                        out.println("<br>");
                    }
                }
            }

        %>
    </body>
</html>
