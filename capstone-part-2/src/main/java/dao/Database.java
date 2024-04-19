package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	//needed to create a connection to db
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";//added for testing

	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/capstone";
    private static final String USERNAME = "alysha_root";
    private static final String PASSWORD = "leahjakeE3";

    //added code for jdbc driver problem 
    static {
        try {
            // Load the JDBC driver
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load JDBC driver", e);
        }
    }
    
    //method to establish connection to db
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        
    }
    
    //maybe delete this?
	public static void main(String[] args) throws SQLException {
		//try to connect to db by calling get connection method 
		try (Connection conn = getConnection()) {
            //if connection is successful
            System.out.println("Connection to database successful.");
        } catch (SQLException e) {
            //if connection fails
            System.err.println("Failed to connect to database:");
            e.printStackTrace();
        }
	}
}
