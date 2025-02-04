package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAO;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//old
	//signs in user, redirects to userpage.html if successful
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//processing the signIn request with all the needed parameters
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		//call DAO class to see if user credentials exist. (validate user is a DAO method that is a sql query for user info)
	    boolean isValidUser = DAO.validateUserSignIn(email, password);

	    System.out.println("checking if user exists");
	    if (isValidUser) {
	    	//instance of DAO
	        DAO dao = new DAO();
	        
	        //get user type from DAO based on email
	        String userType = dao.getUserTypeByEmail(email);

	        //check if userType is not null
	        if (userType != null) {
	            //and if so, redirect user to appropriate page based on userType
	            if ("farmer".equals(userType)) {
	                //redirect to farmer page
	                response.sendRedirect("FarmerPage.html");
	            } else if ("customer".equals(userType)) {
	                //redirect to customer page
	                response.sendRedirect("CustomerPage.html");
	            }
	        } else {
	            //handle no user with provided email exists
	            PrintWriter error = response.getWriter();
	            error.write("No user found with the provided email.");
	        }
	    } else {
	        //handle invalid login 
	        PrintWriter error = response.getWriter();
	        error.write("Incorrect email or password. Please try again.");
	    }
	}
}
