package com.login.bean;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

import com.login.database.RegisterDao;

public class RegisterBean implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    public boolean validateUserInput(String username, String password, String email) {
		return false;
        // Validate the user input, e.g., check if username is empty, password length, email format, etc.
        // Return true if the input is valid, false otherwise.
    }
    
    public boolean isUsernameTaken(String username) throws ClassNotFoundException, SQLException, IOException {
        // Check if the username is already taken by querying the database
        // Return true if the username is taken, false otherwise.
    	return new RegisterDao().userTaken(username);
    	
    
    }
    
    public void insertRegistrationData(String username, String password) throws ClassNotFoundException, SQLException {
        // Insert the registration data into the database
        new RegisterDao().insertRegistration(this);
    }

}
