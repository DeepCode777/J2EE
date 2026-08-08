package user_login_curd;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/login")
public class login extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/j2ee","root","ashu");
			
			PreparedStatement find = conn.prepareStatement("select * from users where email=?");
			find.setString(1, email);
			ResultSet rs = find.executeQuery();
			if(!rs.next()) {
				pw.print("Invalid email or password");
				req.getRequestDispatcher("login.htm").include(req,res);
			}
			else {
				//compare password
				String dbPass = rs.getString("password");
				if(!dbPass.equals(password)){
					pw.print("Invalid email or password");
				}else {
					HttpSession session = req.getSession(true);
					String username = rs.getString("username");
					
					if(session.isNew()) {
						session.setAttribute("name", username);
						res.sendRedirect("dashboard");
					}
					else {
						pw.print("User is already logged in" + "<br>" + "<a href='dashboard'>Dashboard</a>");
						
					}
				}
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
		
	}
}
