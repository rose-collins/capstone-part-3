package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAO;
import dao.DAOFactory;
import model.User;

@WebServlet("/user")
public class UserServlet extends HttpServlet{
	//accessing the DAO for the corresponding methods for CRUD (RUD)

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//old
	//get user info (R/retrieve)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Retrieve email from the request parameter
	    String requestEmail = request.getParameter("email");
	    System.out.println("Request Email: " + requestEmail);  // Print request email

		@SuppressWarnings("unchecked")
		DAO<User> userDAO = (DAO<User>) DAOFactory.getDAO(DAOFactory.DAOType.USER);
	    User user = userDAO.read(requestEmail);  // Call the DAO method to get the user details

	    // Check if user exists
	    if (user != null) {
	        // Write user details to the response
	        PrintWriter out = response.getWriter();
	        out.println(user.getEmail());
	        out.println(user.getPassword());
	        out.println(user.getName());
	        out.println(user.getAddress());
	        out.println(user.getPhoneNumber());
	        out.println(user.getUserType());
	        System.out.println(user.getEmail() + user.getAddress() + user.getName());
	        // Close PrintWriter
	        out.close();
	    } else {
	        // If user doesn't exist or there's an error, send a message
	        PrintWriter out = response.getWriter();
	        out.println("User not found or error occurred");
	        out.close();
	    }
    }
	
	//Update (entire replacement for doPut)(U/update)
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//read request body
	    BufferedReader reader = request.getReader();
	    StringBuilder stringBuilder = new StringBuilder();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        stringBuilder.append(line);
	    }

	    //parse through parameters from request body
	    Map<String, String> params = new HashMap<>();
	    String[] keyValuePairs = stringBuilder.toString().split("&");
	    for (String pair : keyValuePairs) {
	        String[] entry = pair.split("=");
	        if (entry.length > 1) {
	            params.put(entry[0], URLDecoder.decode(entry[1], "UTF-8"));
	        }
	    }

	    //get the params sent in req body
	    String email = params.get("email");
	    String password = params.get("password");
	    String name = params.get("name");
	    String address = params.get("address");
	    String phoneNumber = params.get("phoneNumber");
	    String userType = params.get("userType");

	    System.out.println("Email " + email);
	    
	    //set the params from request body to the user, weather changed or not 
		User user = new User();
		System.out.println("password:" +user.getPassword());
	    user.setEmail(email);
	    user.setPassword(password);
	    user.setName(name);
	    user.setAddress(address);
	    user.setPhoneNumber(phoneNumber);
	    user.setUserType(userType);

		@SuppressWarnings("unchecked")
		DAO<User> userDAO = (DAO<User>) DAOFactory.getDAO(DAOFactory.DAOType.USER);

	    //call updateUser method from DAO to update the user in the db
	    boolean updated = userDAO.update(user);

	    //handle the response based on the result of the update
	    if (updated) {
	        response.setContentType("application/json");
	        response.getWriter().write("{\"message\": \"User information updated successfully\"}");
	    } else {
	        response.setContentType("application/json");
	        response.getWriter().write("{\"message\": \"Failed to update user information\"}");
	    }
	}
	
	//delete user (use the delete the DAO delete method)(D/delete)
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get user email from the request
	    String email = request.getParameter("email");
		@SuppressWarnings("unchecked")
		DAO<User> userDAO = (DAO<User>) DAOFactory.getDAO(DAOFactory.DAOType.USER);

	    //deleteUser method from DAO to delete the user from the db
	    boolean deleted = userDAO.delete(email);

	    //if successfully deleted or not
	    if (deleted) {
	        // Set response type to plain text
	        response.setContentType("text/plain");
	        response.getWriter().write("Account successfully deleted");

	        // Redirect after 5 seconds
	        response.setHeader("Refresh", "1; URL=index.jsp");
	    } else {
	        response.getWriter().write("Failed to delete user");
	    }
	}
	
}
