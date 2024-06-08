package com.login.database;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.login.bean.RegisterBean;

public class RegisterDao  implements Serializable{
	private static final long serialVersionUID = 1L;
	
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



