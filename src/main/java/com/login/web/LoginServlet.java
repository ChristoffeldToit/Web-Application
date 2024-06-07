package com.login.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.login.bean.LoginBean;
import com.login.database.LoginDao;



@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private LoginDao loginDao;

    public void init() 
    {
        loginDao = new LoginDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        LoginBean loginBean = new LoginBean();
        //
        LoginDao loginDao = new LoginDao();
        //
        
        loginBean.setUsername(username);
        loginBean.setPassword(password);
        
        //
        boolean status = false;
		try {
			status = loginDao.validate(loginBean);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        response.setContentType("application/json");
        response.getWriter().write(String.valueOf(status));
        //
        
        try 
        {
            if (loginDao.validate(loginBean)) 
            {
                response.sendRedirect("loginsuccess.html");
                
                //
                response.setContentType("application/json");
                response.getWriter().write(String.valueOf(status));
                //
            } 
            else 
            {
                HttpSession session = request.getSession();
                response.sendRedirect("login.html");
            }
        } 
        catch (ClassNotFoundException e) 
        {
            e.printStackTrace();
        }
    }
}