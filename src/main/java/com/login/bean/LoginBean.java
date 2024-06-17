package com.login.bean;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

import com.login.database.LoginDao;
import com.login.database.RegisterDao;

public class LoginBean implements Serializable {
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
    
    public boolean isUsernameRegistered(String username, String password) throws ClassNotFoundException, SQLException, IOException {
        // Check if the username is already taken by querying the database
        // Return true if the username is taken, false otherwise.
    	return new LoginDao().UsernameTaken(username, password);
    	
    
    }
}