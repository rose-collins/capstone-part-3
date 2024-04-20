package servlets;
import java.io.BufferedReader;
//old
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAO;
import dao.DAOFactory;
import model.User;

@WebServlet("/passwordRecovery")
public class PasswordRecoverySevlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Read the body content from the request
	    BufferedReader reader = request.getReader();
	    StringBuilder requestBody = new StringBuilder();
	    String line;
	    while ((line = reader.readLine()) != null) {
	        requestBody.append(line);
	    }

	    // Parse the request body to get email and phoneNumber
	    String[] params = requestBody.toString().split("&");
	    String email = null;
	    String phoneNumber = null;

	    for (String param : params) {
	        String[] keyValue = param.split("=");
	        if (keyValue[0].equals("email")) {
	            email = URLDecoder.decode(keyValue[1], "UTF-8");
	        } else if (keyValue[0].equals("phoneNumber")) {
	            phoneNumber = URLDecoder.decode(keyValue[1], "UTF-8");
	        }
	    }

	    @SuppressWarnings("unchecked")
		DAO<User> userDAO = (DAO<User>) DAOFactory.getDAO(DAOFactory.DAOType.USER);
	    User user = userDAO.read(email);
	    
	    // If email exists
	    if (user != null) { 
	        if (user.getPhoneNumber().equals(phoneNumber)) { // Correct phone number
	            String currentPassword = user.getPassword(); // Get current password
	            // Return current password to the user
	            response.getWriter().write("Your current password is: " + currentPassword);
	        } else {
	            // Incorrect phone number
	            response.getWriter().write("Incorrect phone number");
	        }
	    } else {
	        // Email does not exist
	        response.getWriter().write("Email not found");
	    }

        
	}
}
