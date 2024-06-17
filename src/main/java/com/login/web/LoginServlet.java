package com.login.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.login.bean.LoginBean;
import com.login.bean.RegisterBean;
import com.login.database.LoginDao;



@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginDao loginDao;

    public void init() {
        loginDao = new LoginDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        LoginBean loginBean = new LoginBean();
        loginBean.setUsername(username);
        loginBean.setPassword(password);

        try {
            if (!loginDao.UsernameTaken(username, password)) {
                // Username not found
                // Provide feedback to the user
            	request.setAttribute("errorMessage", "Username or password not found. <br /> Please check again or create a new account.");
	            request.getRequestDispatcher("login.jsp").forward(request, response);
                response.sendRedirect("login.html"); // Redirect back to the login page
            } 
            else 
            {
                // Username is not registered
                // Proceed with login authentication logic
            	loginDao.validate(loginBean);
            	response.sendRedirect("home.html");
				
            }
        }
        
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}