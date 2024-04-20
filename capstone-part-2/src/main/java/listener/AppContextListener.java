package listener;

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
        	Database.getInstance().getConnection();
        	System.out.println("Database connection initialized.");
        } catch (SQLException e) {
            System.err.println("Failed to initialize database connection:");
            e.printStackTrace();
        }
    }

	//closing the db connection
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        // Don't close the connection here
        System.out.println("Application context destroyed.");
    }
}
