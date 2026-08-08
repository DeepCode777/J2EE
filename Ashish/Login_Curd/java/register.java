package user_login_curd;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/register")
public class register extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		
		String name = req.getParameter("username");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String gender = req.getParameter("gender");
		String address = req.getParameter("address");
		String city = req.getParameter("city");
		String date = req.getParameter("dob");
		Date dob = Date.valueOf(date);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/j2ee","root","ashu");
			PreparedStatement create = conn.prepareStatement("insert into users (username,email,password,gender,address,city,dob) values(?,?,?,?,?,?,?)");
			
			create.setString(1, name);
			create.setString(2, email);
			create.setString(3, password);
			create.setString(4, gender);
			create.setString(5, address);
			create.setString(6, city);
			create.setDate(7, dob);
			
			PreparedStatement find = conn.prepareStatement("select * from users where email=?");
			find.setString(1, email);
			ResultSet rs = find.executeQuery();
			if(rs.next()) {
				pw.print("Invalid email");
				req.getRequestDispatcher("register.htm").include(req, res);
			}
			else {
				int created = create.executeUpdate();
				pw.print( created > 0 ? "<h3>Register successful<h3>" : "<h3>Unable to Register<h3>");
				req.getRequestDispatcher("login.htm").include(req, res);
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
		
	}
}
