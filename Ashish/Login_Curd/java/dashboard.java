package user_login_curd;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/dashboard")
public class dashboard extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		
		HttpSession session = req.getSession(false);
		if(!session.isNew()) {
			String name = (String)session.getAttribute("name");
			pw.print("Welcome " + name);
			pw.print("<a href='logout'>Logout</a>");
		}else {
			pw.print("<p>Invalid Request</p>");
			req.getRequestDispatcher("login.htm").include(req, res);
		}
		
	}
}
