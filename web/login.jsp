<%-- 
    Document   : login
    Created on : Nov 24, 2022, 12:43:45 PM
    Author     : chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log In or Sign Up</title>
        <link rel="icon" href="/CakeYum/images/error-page/cupcake.png"/>
        <link rel="stylesheet" href="styles/login-styles.css">
    </head>
    <body>

        <!-- HEADER -->
        <%@ include file = "header.jsp" %>
        <!-- END OF HEADER -->

        <%
            // Invalidates session after returning to login screen
            if (session.getAttribute("username")!=null)    {
                session.invalidate();
                response.sendRedirect("index.jsp");
            }
            // Ensures invalidation when redirected from other page
            if (request.getParameter("attempted-guest-access") != null)    {
                out.println("<script type=\"text/javascript\">");  
                out.println("alert('You must log in first.');");
                out.println("window.location.replace('login.jsp')");
                out.println("</script>");
            }
            
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");
        %>
        
        <div class="center">
            <div class="container">
                <div class="title"><span>Log In</span></div>
                <form action="Login" method="post">
                    <span class="error-message"></span>
                    <div class="row">
                        <label>Username:</label>
                        <input type="text" class="text" name="username">
                    </div>
                    <div class="row">
                        <label>Password:</label> 
                        <input class="text" type="password" name="password">
                    </div>
                    
                    <div class="captcha">
                        <label for="captcha-input">Enter Captcha</label><br>
                        <img src="CaptchaGenerator" alt="captcha"><br> 
                        <input type="text" name="captcha-input" id="captcha" placeholder="Enter Captcha Text">
                    </div>
                    <br>                   
                    <div class="row button">
                        <input class="button" type="submit" value="Login">
                    </div>
                    <div>Don't have an account? <a href="signup.jsp">Sign Up</a></div>
                </form>
            </div>
        
     <% if (request.getParameter("invalid-login-credentials") != null)    {   %>
            <script>
               let message1 = "Invalid username or password.";

               let messageContainer1 = document.querySelector(".error-message");

               messageContainer1.setAttribute('style','color:#8B0000;');
               messageContainer1.textContent = message1;
            </script>
     <% }
        if (request.getParameter("invalid-captcha") != null)    {   %>
            <script>
               let message1 = "Invalid captcha attempt.";

               let messageContainer1 = document.querySelector(".error-message");

               messageContainer1.setAttribute('style','color:#8B0000;');
               messageContainer1.textContent = message1;
            </script>
     <% } %>
    </body>
</html>
