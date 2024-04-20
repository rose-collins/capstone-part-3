<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Password Recovery</title>
	<link rel="stylesheet" href="styles.css">
	<script src="js/passwordRecovery.js"></script>

</head>
<body>
<header id="header" class="header">
    <div class="container">
        <h1>Farm Share - </h1>
        <h1>Password Recovery</h1>
    </div>
</header>
<main id="main-content">
    <form id="passwordRecoveryForm" action="passwordRecovery" method="POST">
        <label for="email">Email:</label><br>
        <input id="email" type="email" id="email" name="email" required><br><br>

        <label for="phoneNumber">Phone Number:</label><br>
        <input id="phoneNumber" type="tel" id="phoneNumber" name="phoneNumber" required><br><br>

        <input type="submit" value="Recover Password">
    </form>
    <p id="passwordResponse"></p>
    <a id="link" href="index.jsp">Return to Login</a>
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