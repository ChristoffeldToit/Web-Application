package com.login.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.login.bean.LoginBean;

public class LoginDao {

    public boolean validate(LoginBean loginBean) throws ClassNotFoundException {
        boolean status = false;

       // Class.forName("com.mysql.jdbc.Driver");
        try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(ClassNotFoundException cnf)
		{
			cnf.printStackTrace();
			System.out.println("Driver not found!");
		}
        try (Connection con = DriverManager.getConnection("jdbc:mysql:///demo", "root", "admin");
      
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = con
            .prepareStatement("select * from login where username = ? and password = ? ")) {
            preparedStatement.setString(1, loginBean.getUsername());
            preparedStatement.setString(2, loginBean.getPassword());

            System.out.println(preparedStatement);
            // Execute the query and store the result in the ResultSet
            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return status;
    }
    
    public boolean UsernameTaken(String username, String password) throws SQLException, ClassNotFoundException {
        boolean status = false;
        // Establish a database connection
		// Execute a query to check if the username exists in the database
		// Set status based on the query result
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		try (Connection con = DriverManager.getConnection("jdbc:mysql:///demo", "root", "admin");
		     PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM LOGIN WHERE USERNAME = ? AND PASSWORD = ?");) 
		{
			// Set the value of the username parameter in the prepared statement
		    ps.setString(1, username);
		    ps.setString(2, password);
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
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
