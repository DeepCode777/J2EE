import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String userEmail = request.getParameter("email");
		String userPassword = request.getParameter("password");
		String userFullName = request.getParameter("fullName");
		String userGender = request.getParameter("gender");
		String userAddress = request.getParameter("address");
		String userCity = request.getParameter("city");
		String userCountry = request.getParameter("country");
		
		String day = request.getParameter("dob_day");
		String mounth = request.getParameter("dob_month");
		String year = request.getParameter("dob_year");
		
		String dob = year + "-" + mounth + "-" + day;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment", "root", "deep");
			
			PreparedStatement check = con.prepareStatement("SELECT emal FROM register WHERE emal = ?");
			check.setString(1, userEmail);
			ResultSet rs = check.executeQuery();
			
			if (rs.next()) {
				// Email already exists -> create session and go to dashboard
				HttpSession session = request.getSession(true);
				session.setAttribute("fullName", userFullName);
				response.sendRedirect("dashboard");
			} else {
				PreparedStatement ps = con.prepareStatement("INSERT INTO register (emal, password, fullname, gender, adderss, city, country, dob) VALUES (?,?,?,?,?,?,?,?)");
				ps.setString(1, userEmail);
				ps.setString(2, userPassword);
				ps.setString(3, userFullName);
				ps.setString(4, userGender);
				ps.setString(5, userAddress);
				ps.setString(6, userCity);
				ps.setString(7, userCountry);
				ps.setString(8, dob);
				
				
				
				int i = ps.executeUpdate();
				if (i > 0) {
					HttpSession session = request.getSession(true);
					session.setAttribute("fullName", userFullName);
					response.sendRedirect("dashboard");
				} else {
					response.sendRedirect("login.html");
				}
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("login.html");
		}
	}
}