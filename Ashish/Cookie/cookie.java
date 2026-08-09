package seesion_cookie_manage;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/cookie")
public class cookie extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		Cookie username = new Cookie("username",req.getParameter("username"));
		Cookie password = new Cookie("password",req.getParameter("password"));
		
		username.setMaxAge(120);
		password.setMaxAge(120);
		
		res.addCookie(username);
		res.addCookie(password);
		
		res.sendRedirect("seeCookie");
	}
}


