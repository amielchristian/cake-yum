<%-- 
    Document   : orders
    Created on : Nov 24, 2022, 12:50:10 PM
    Author     : chris
--%>

<%@page import="java.io.OutputStream"%>
<%@page import="java.io.File"%>
<%@page import="com.itextpdf.text.pdf.BaseFont"%>
<%@page import="com.itextpdf.text.pdf.PdfStamper"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="com.itextpdf.text.pdf.PdfReader"%>
<%@page import="com.itextpdf.text.pdf.PdfContentByte"%>
<%@page import="com.itextpdf.text.pdf.draw.VerticalPositionMark"%>
<%@page import="java.time.LocalDate"%>
<%@page import="com.itextpdf.text.pdf.draw.LineSeparator"%>
<%@page import="com.itextpdf.text.pdf.PdfPCell"%>
<%@page import="com.itextpdf.text.pdf.PdfPTable"%>
<%@page import="com.itextpdf.text.*"%>
<%@page import="com.itextpdf.text.Document"%>
<%@page import="com.itextpdf.text.pdf.PdfWriter"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.util.Date"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="model.*"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Tracker</title>
        <link rel="icon" href="/CakeYum/images/error-page/cupcake.png"/>
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

        <%            if (session.getAttribute("username") == null) {
                response.sendRedirect("LoginRedirect");
            } else {
                // for connectivity
                String driver = getServletContext().getInitParameter("jdbcClassName");
                String username = getServletContext().getInitParameter("dbUsername");
                String password = getServletContext().getInitParameter("dbPassword");

                StringBuffer url = new StringBuffer((String) getServletContext().getInitParameter("jdbcDriverURL"))
                        .append("://")
                        .append((String) getServletContext().getInitParameter("dbHostName"))
                        .append(":")
                        .append((String) getServletContext().getInitParameter("dbPort"))
                        .append("/")
                        .append((String) getServletContext().getInitParameter("dbName"))
                        .append((String) getServletContext().getInitParameter("addlParams"));

                // gets current user's ID
                UserGetter ug = new UserGetter(driver, username, password, url.toString());
                int userID = ug.getUserID((String) session.getAttribute("username"));

                // gets all the orders associated with the current user
                OrderGetter og = new OrderGetter(driver, username, password, url.toString());
                ArrayList<Integer> orderIDs = og.getOrderIDs(userID);
                int orderCount = orderIDs.size();
        %>
        <div class="purchases-body">
            <div class="purchases-container">
                <%
                        if (orderCount == 0) {
                            out.println("<div class=\"no-item\">You haven't placed any orders yet.</div>");
                        } else {
                            out.println("<h1 class=\"container-title\">Previous Orders</h1>");

                            // loops through all the orders associated with the current user
                            for (int i = orderCount - 1; i >= 0; i--) {
                                // gets products and metadata associated with each order ID
                                int orderID = orderIDs.get(i);
                                ArrayList<Order> orders = og.getOrderInfo(orderID);
                                LocalDateTime dateTime = orders.get(0).getDateTime();
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");

                                out.println("<div class=\"order\">");
                                out.println("<div class=\"order-heading\">");
                                out.println("<p>Order ID: " + orderID + "</p>");
                                out.println("<p>Placed on " + dateTime.format(formatter) + "</p>");
                                out.println("</div>");

                                // Display cart in tabular form
                                out.println("<div class=\"table\" style=\"overflow-x:auto;\">");
                                out.println("<table>");
                                out.println("<tr>");
                                out.println("<th class=\"product-column\">Product Name</th>");
                                out.println("<th class=\"quantity-column\">Qty.</th>");
                                out.println("<th class=\"price-column\">Price</th>");
                                out.println("</tr>");

                                double total = 0;
                                // loops through each row associated with a certain order ID
                                for (int j = 0; j < orders.size(); j++) {
                                    Order order = (Order) orders.get(j);

                                    int productID = order.getProductID();
                                    int quantity = order.getQuantity();
                                    double price = order.getCost();
                                    total += price;

                                    // gets product information for a given product
                                    ProductGetter pg = new ProductGetter(driver, username, password, url.toString());
                                    Product product = pg.getProduct(productID);

                                    out.println("<tr>");
                                    out.println("<td class=\"item-name\">" + product.getName() + "</td>");
                                    out.println("<td class=\"item-quantity\">" + quantity + "</td>");
                                    out.println("<td class=\"item-price\"><b>&#8369</b>" + String.format("%.2f", price) + "</td>");
                                    out.println("</tr>");

                                }

                                out.println("<tr class=\"total-row\">");
                                out.println("<td class=\"total-title\" colspan=\"2\">Total: </td>");
                                out.println("<td class=\"order-price\"><b>&#8369</b>" + String.format("%.2f", total) + "</td>");
                                out.println("</tr>");
                                out.println("</table>");
                                out.println("</div>");
                                out.println("</div>");
                                out.println("<br>");

                            }
                            out.println("<form method=\'post\' action=\'CakeYum_Receipt\' target=\"_blank\"><input type=\"submit\" value=\"Download Receipt\" class = \"button\"name=\"submit\"></form>");
                            out.println("<br>");

                        }
                        out.println("</div>");
                        out.println("</div>");
                    }

                %>
                </body>
                </html>
