package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAO;
import dao.DAOFactory;
import model.User;

@WebServlet("/signUp")
public class SignUpServelet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//old
	//Creates user
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//processing the signUp request with all the needed parameters
		System.out.println("Processing the sighUp servlet now.");
		
		System.out.println("Getting users parameters");
		//getting users input using needed parameters
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String name = request.getParameter("fullName");
		//getting all address params and saving it as one
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String postalCode = request.getParameter("postalCode");
		String address = street + ", " + city + ", " + postalCode;
		String phoneNumber = request.getParameter("phoneNumber");
		String userType = request.getParameter("userType");
		
		//creating user object
		System.out.println("Creating User objest.");
		User user = new User();
		
		//setting the parameters value to the user obj. 
		System.out.println("Setting users params to User object");
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        user.setAddress(address);
        user.setPhoneNumber(phoneNumber);
        user.setUserType(userType);

		//write new user to DB (call the DAO class)
        System.out.println("New instance, DAO object");
		@SuppressWarnings("unchecked")
		DAO<User> userDAO = (DAO<User>) DAOFactory.getDAO(DAOFactory.DAOType.USER);
        
        boolean success = userDAO.create(user);
        System.out.println("User created: " + success);
        
        //rends redirect based of success boolean value
        if (success) {
            response.sendRedirect("SuccessOrErrorSignup.html?success=true");
        } else {
            response.sendRedirect("SuccessOrErrorSignup.html?success=false");
        }
	}


}
