<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Landing Page</title>
    <link rel="stylesheet" href="styles.css">
    <script src="js/index.js"></script>
</head>
<body>
    <header id="header" class="header">
        <div class="container">
            <h1>Farm Share</h1>
        </div>
    </header>

    <main id="main-content">
        <form id="signInForm" action="signIn" method="POST">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required><br><br>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required><br><br>

            <input type="submit" value="Sign In" onclick="return storeEmail();">
        </form>

        <p>Don't have an account? <a href="signUp.jsp">Sign Up</a></p>
        <p>Forgot your password? <a href="passwordRecovery.jsp">Recover Password</a></p>

        <%-- error handling --%>
        <% 
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) { 
        %>
                <p><%= errorMessage %></p>
        <% 
            } 
        %>
    </main>

    <footer id="footer" class="footer">
        <div class="container">
            <p>&copy; Farm Share 2024. All rights reserved.</p>
            <p>Phone: <a href="tel:+1555555555">+1 (555) 555-5555</a></p>
            <p>Email: <a href="mailto:info@farmshare.com">info@farmshare.com</a></p>
        </div>
    </footer>
</body>
</html>