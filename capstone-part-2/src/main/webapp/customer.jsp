<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Page</title>
    <link rel="stylesheet" href="styles.css">
    <script src="js/customer.js"></script>
</head>
<body>
<header id="header" class="header">
    <div class="container">
        <h1>Farm Share - Customer Page</h1>
        <a id="signoutbutton" href="index.jsp">Sign Out</a>
    </div>
</header>

<main id="main-content">
    <div id="message"></div>
    <button type="button" onclick="getUserInformation()">Show Account Information</button>

    <!-- Account Details to be populated by JavaScript -->
    <div id="accountDetails" style="display: none;">
        <p><strong>Email:</strong> <span id="emailDisplay"></span></p>
        <p><strong>Name:</strong> <span id="nameDisplay"></span></p>
        <p><strong>User Type:</strong> <span id="userTypeDisplay"></span></p>
        <p><strong>Password:</strong> <span id="passwordDisplay"></span> <input type="password" id="passwordInput" style="display: none;"></p>
        <p><strong>Address:</strong> <span id="addressDisplay"></span> <input type="text" id="addressInput" style="display: none;"></p>
        <p><strong>Phone Number:</strong> <span id="phoneNumberDisplay"></span> <input type="text" id="phoneNumberInput" style="display: none;"></p>
    </div>
    <div>
         <button onclick="editField('password')">Edit Password</button>
         <button onclick="editField('address')">Edit Address</button>
         <button onclick="editField('phoneNumber')">Edit Phone Number</button>
    </div>
</main>

<!-- Delete user button -->
<button id="deleteAccount" onclick="deleteUserWithConfirmation()">Delete Account</button>

<footer id="footer" class="footer">
    <div class="container">
        <p>&copy; Farm Share 2024. All rights reserved.</p>
        <p>Phone: <a href="tel:+1555555555">+1 (555) 555-5555</a></p>
        <p>Email: <a href="mailto:info@farmshare.com">info@farmshare.com</a></p>
    </div>
</footer>
</body>
</html>