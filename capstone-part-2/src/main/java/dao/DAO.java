package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class DAO {
	//prepared SQL statement for CRUD operations
    private static final String INSERT_USER_SQL = "INSERT INTO user (email, password, name, address, phoneNumber, userType) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_EMAIL_SQL = "SELECT * FROM user WHERE email = ?";
    private static final String UPDATE_USER_SQL = "UPDATE user SET password = ?, name = ?, address = ?, phoneNumber = ?, userType = ? WHERE email = ?";
    private static final String DELETE_USER_SQL = "DELETE FROM user WHERE email = ?";

    //create a user = C
    public boolean createUser(User user) {
    	//checking if user already exists, return false if exists already
        if (getUserByEmail(user.getEmail()) != null) {
            return false;
        }else {
        	//try to connect to DB
            try (Connection connection = Database.getConnection();
                 PreparedStatement statement = connection.prepareStatement(INSERT_USER_SQL)) {
                
                //setting params for the prepared statement based on user input
                statement.setString(1, user.getEmail());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getName());
                statement.setString(4, user.getAddress());
                statement.setString(5, user.getPhoneNumber());
                statement.setString(6, user.getUserType());

                //execute query once all feilds are filled
                int rowsInserted = statement.executeUpdate();
                return rowsInserted > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("inside update user method");
                return false;
            }
        }
    }

    //get/retrieve a user by email = R
    public User getUserByEmail(String email) {
    	//making sure connected to db
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_EMAIL_SQL)) {
            statement.setString(1, email);
            //if connected, get user details in a result set, return to user obj
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Extract user information from the ResultSet
                    User user = new User();
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("password"));
                    user.setName(resultSet.getString("name"));
                    user.setAddress(resultSet.getString("address"));
                    user.setPhoneNumber(resultSet.getString("phoneNumber"));
                    user.setUserType(resultSet.getString("userType"));
                    return user;
                }
            }
        //error if no connection
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //update user = U
    public boolean updateUser(User user) {
    	//testing connection to db
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_SQL)) {
            //setting the new params for the prepared statement (not all need to be inserted, but all need will be updated anyway)
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getName());
            statement.setString(3, user.getAddress());
            statement.setString(4, user.getPhoneNumber());
            statement.setString(5, user.getUserType());
            statement.setString(6, user.getEmail());

            //execute the query with the updated info
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        //if no db connection
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //delete a user = D
    public boolean deleteUser(String email) {
    	//testing connection to db
        try (Connection connection = Database.getConnection();
        	//if connected, delete the user from db	
            PreparedStatement statement = connection.prepareStatement(DELETE_USER_SQL)) {
            statement.setString(1, email);
            
            //executing the query
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
            
            //if no db connection
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //---------------------OTHER METHODS OF ACCESSING AND VALIDATION OF USER--------------------------------------------------------------
    
    //Validate user sign-in credentials
    public static boolean validateUserSignIn(String email, String password) {
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE email = ? AND password = ?")) {
        	//sees if email and password match
            statement.setString(1, email);
            statement.setString(2, password);
            //returns true if the query returns any rows
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); 
            }
        //return false
        } catch (SQLException e) {
            e.printStackTrace();
            return false; 
        }
    }
    
    //get user type by email
    public String getUserTypeByEmail(String email) {
        String userType = null;
        String sql = "SELECT userType FROM user WHERE email = ?";
        
        try (Connection connection = Database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    userType = resultSet.getString("userType");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return userType;
    }

    
}
