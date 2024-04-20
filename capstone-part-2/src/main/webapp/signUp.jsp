<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign Up</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
<header id="header" class="header">
    <div class="container">
        <h1>Farm Share - </h1>
        <h1>Sign Up</h1>
    </div>
</header>
<main id="main-content">
    <form id="signUpForm" action="signUp" method="POST">
        <label for="fullName">Full Name:</label><br>
        <input type="text" id="fullName" name="fullName" required><br><br>

        <label for="address">Address:</label><br>
        <input type="text" id="street" name="street" placeholder="Street" required><br>
        <input type="text" id="city" name="city" placeholder="City" required><br>
        <input type="text" id="postalCode" name="postalCode" placeholder="Postal Code" required><br><br>

        <label for="email">Email Address:</label><br>
        <input type="email" id="email" name="email" required><br><br>
		
		<label for="password">Password:</label><br>
		<input type="password" id="password" name="password" required><br><br>
		
        <label for="phoneNumber">Phone Number:</label><br>
        <input type="tel" id="phoneNumber" name="phoneNumber" required><br><br>

        <label>User Type:</label><br>
        <input type="radio" id="farmer" name="userType" value="farmer" required>
        <label for="farmer">Farmer</label><br>
        <input type="radio" id="customer" name="userType" value="customer" required>
        <label for="customer">Customer</label><br><br>

        <input type="submit" value="Sign Up">
    </form>
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