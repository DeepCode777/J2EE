package user_login_curd;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/logout")
public class logout extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		
		HttpSession session = req.getSession(true);
		
		if(!session.isNew()) {
			session.invalidate();
			pw.print("Logout Success");
			req.getRequestDispatcher("login.htm").include(req, res);
		}else {
			pw.print("Alredy Logged out");
			req.getRequestDispatcher("login.htm").include(req, res);
		}
	}
}
