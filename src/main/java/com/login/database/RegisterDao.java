package com.login.database;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import com.login.bean.RegisterBean;

public class RegisterDao  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//Insert registration data into the database
	public boolean insertRegistration(RegisterBean registerBean) throws ClassNotFoundException, SQLException {
		boolean status = false;

		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(ClassNotFoundException cnf)
		{
			cnf.printStackTrace();
			System.out.println("Driver not found");
		}
		try (Connection con = DriverManager.getConnection("jdbc:mysql:///demo", "root", "admin");)
		{
			//Create a statement using connection object
			PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO LOGIN (USERNAME, PASSWORD) VALUES(?, ?)");
			preparedStatement.setString(1, registerBean.getUsername());
			preparedStatement.setString(2, registerBean.getPassword());
			System.out.println(preparedStatement);
			//ResultSet rs = preparedStatement.executeQuery();
			//status = rs.next();
			
			int rowsAffected = preparedStatement.executeUpdate(); // Use executeUpdate() for INSERT statements
            
            if (rowsAffected > 0) 
            {
                System.out.println("User registered successfully.");
				status = true;
            } else 
            {
                System.out.println("Registration failed. Please try again.");
                status = false;
            }
		
		}
		catch(SQLException e)
		{
			printSQLException(e);
		}
		return status;
	}
	
	//Check database to see if username exists
	public boolean userTaken(String username) throws ClassNotFoundException, SQLException, IOException {
		 boolean status = false;
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		try (Connection con = DriverManager.getConnection("jdbc:mysql:///demo", "root", "admin");
		     PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM LOGIN WHERE USERNAME = ?");) 
		{
			// Set the value of the username parameter in the prepared statement
		    ps.setString(1, username);
		    // Execute the query and store the result in the ResultSet
		    ResultSet rs = ps.executeQuery();
		    // Check if the ResultSet has any rows
		    if (rs.next()) {
		        // If the count in the first column of the result is greater than 0, set status to true (username exists)
		        status = rs.getInt(1) > 0;
		    }
		} 
		catch (SQLException e) 
		{
			printSQLException(e);
		}
		return status;
	}

	private void printSQLException(SQLException ex) {
        for (Throwable e: ex) 
        {
            if (e instanceof SQLException) 
            {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) 
                {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}



