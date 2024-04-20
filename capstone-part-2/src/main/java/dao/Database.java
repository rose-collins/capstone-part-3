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
    
    //hold the single instance of the DB 
    private static Database DBINSTANCE = null;
    private Connection connection;

    //initializing the JDBC driver
    static {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load JDBC driver", e);
        }
    }
    
    //private constructor to prevent external instantiation
    private Database() {
    	//loads JDBC
    	try {
            this.connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database", e);
        }
    }
    //get Singleton instance and return the instance if !null
    public static Database getInstance() {
        if (DBINSTANCE == null) {
        	DBINSTANCE = new Database();
        }
        System.out.println("in getinstance");
        return DBINSTANCE;
    }

    
    //method to establish connection to db
    public Connection getConnection() throws SQLException {
    	System.out.println("in getConnection");
    	if (this.connection == null || this.connection.isClosed()) {
    	        this.connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    	}
    	return this.connection;           
    }
    
    //maybe delete this?
	public static void main(String[] args) throws SQLException {
		Database.getInstance();
		//try to connect to db by calling get connection method 
		try (Connection conn = Database.getInstance().getConnection()) {
            //if connection is successful
            System.out.println("Connection to database successful.");
        } catch (SQLException e) {
            //if connection fails
            System.err.println("Failed to connect to database:");
            e.printStackTrace();
        }
	}
}
