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
            }
            // Ensures invalidation when previous login attempt failed
            if (request.getParameter("invalidLoginCredentials") != null)    {
                out.println("<script type=\"text/javascript\">");  
                out.println("alert('Invalid username or password. Please try again.');");  
                out.println("</script>");
            }
            // Ensures invalidation when redirected from other page
            if (request.getParameter("attemptedGuestAccess") != null)    {
                out.println("<script type=\"text/javascript\">");  
                out.println("alert('You must log in first.');");  
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
                    <div>error message goes here</div>
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
                </form>

            </div>
            
            <div class="container">
                <div class="title"><span>Sign Up</span></div>
                <form action="SignUp" method="post">
                    <div class="row">
                        <label>Username:</label> 
                        <input class="text" type="text" name="username" required>
                    </div>
                    <div class="row">
                        <label>Password:</label> 
                        <input class="text" type="password" name="password" id="new-password" required>
                    </div>
                    <div class="row">
                        <label>Full Name:</label> 
                        <input class="text" type="text" name="full-name" required>
                    </div>
                    <div class="row">
                        <label>Address:</label> 
                        <input class="text" type="text" name="address" required>
                    </div>
                    <div class="row">
                        <label>Mobile Number:</label> 
                        <input class="text" type="tel" name="mobile-number" pattern="[\d\s()+-]*" required>
                    </div>
                    
                    <div class="captcha">
                        <label for="captcha-input">Enter Captcha</label><br>
                        <img src="CaptchaGenerator" alt="captcha"><br> 
                        <input type="text" name="captcha-input" id="captcha" placeholder="Enter Captcha Text">
                    </div>
                    <br>                   
                    <div class="row button">
                        <input class="button" type="submit" value="Sign Up">
                    </div>
                </form>
            </div>
        </div>
        <script>
            document.getElementById('new-password').setAttribute('autocomplete', 'new-password');
        </script>
    </body>
</html>
