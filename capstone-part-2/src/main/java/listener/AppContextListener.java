package listener;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import dao.Database;

public class AppContextListener implements ServletContextListener{

	//initializing the database
	@Override
    public void contextInitialized(ServletContextEvent event) {
        try {
        	//get DB connection
            Database.getConnection();
            System.out.println("Database connection initialized.");
        } catch (SQLException e) {
            System.err.println("Failed to initialize database connection:");
            e.printStackTrace();
        }
    }

	//closing the db connection
    @Override
    public void contextDestroyed(ServletContextEvent event) {
    	Connection connection = null;
        try {
            //get db connection again to close
            connection = Database.getConnection();
            
            //closing connection if its open
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        //if couldnt close the db connection, print stack trace.
        } catch (SQLException e) {
            System.err.println("Failed to close database connection:");
            e.printStackTrace();
        }
        //success message of app context being destroyede
        System.out.println("Application context destroyed.");
    }
	
}
