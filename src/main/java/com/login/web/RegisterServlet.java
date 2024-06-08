package com.login.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.login.bean.RegisterBean;
import com.login.database.LoginDao;
import com.login.database.RegisterDao;


//Changed from RegisterServlet to /register
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RegisterDao registerDao;
       

	public void init() {
        registerDao = new RegisterDao();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("paasword");
		RegisterBean registerBean = new RegisterBean();
		registerBean.setUsername(username);
		registerBean.setPassword(password);
		
		try 
		{
			if(registerDao.insertRegistration(registerBean)) 
			{
				response.sendRedirect("home.html");
			}
			else
			{
				//
			}
			
		}
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
