<%-- 
    Document   : specific-product
    Created on : 12 2, 22, 7:47:28 AM
    Author     : Clarisse Salazar  
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Specific Dessert Product</title>
        <link href='https://fonts.googleapis.com/css?family=Cookie' rel='stylesheet'>
        <link rel="stylesheet" href="styles/specific-product-styles.css">
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

        <div class="bg-pic">
            <br>
            <div class="form">
                <div class="row">
                    <div class="big-box">
                        <div class="container">
                            <div class="row">
                                <div class="box">
                                    <div class="big">
                                    <img class="img" src="https://i.ytimg.com/vi/vGE-RfP6KRE/maxresdefault.jpg">
                                    </div>
                                    <div class="cont">
                                        <div class="quantity">
                                            <label>Quantity</label>
                            <input type="number" name="quantity" min="1" max="10" value="1"/>
                                        </div>
                                        <button class="button">Add to Cart</button>
                                    </div> 




                                </div>
                                <div class=box>

                                    <div class="whole">
                                        
                                        <div class="border">
                                            <h1 class="title">Red Velvet Cake</h1>  
                                        </div>
                                        <div class="border">
                                            <div class="description">Red velvet cake tastes like very mild cocoa with a slightly tart edge. The cream cheese frosting is the most forward flavor. Perhaps even more important than the taste is the texture: smooth, soft, tender and light with creamy icing.</div>
                                        </div><!-- comment -->
                                        <div class="border bottom">
                                            <div class="price">
                                               &#8369 150
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <br>


        </div>
    </body>

</html>
