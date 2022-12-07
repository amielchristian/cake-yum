<%-- 
    Document   : error404
    Created on : Nov 24, 2022, 11:19:49 AM
    Author     : Charles
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error 404</title>
        <link href="../styles/error-page-styles.css" rel="stylesheet" type="text/css"/>
        <link href="styles/error-page-styles.css" rel="stylesheet" type="text/css"/>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Lato:wght@700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Fredoka+One&display=swap" rel="stylesheet"> 
    </head>
    <body>
        <div class="container">
            <!--TODO change src-->
            <img src="http://localhost:8080/shopping-cart/images/error-page/error-cupcake.png" alt="eaten-cupcake" id="error-cupcake"/>
            <div class="error-text">
                <h1>403</h1>
                <h2>ERROR</h2>
                <p>Oops! We can't authorize you to view this page</p>
            </div>
        </div>
    </body>
</html>
