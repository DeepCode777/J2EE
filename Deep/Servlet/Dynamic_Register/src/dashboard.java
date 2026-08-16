import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/dashboard")
public class dashboard extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("fullName") != null) {
			String name = (String) session.getAttribute("fullName");
			out.print("<h2>Welcome back, " + name + "</h2>");
			out.print("<a href='logout'><button>Logout</button></a>");
		} else {
			response.sendRedirect("login.html");
		}
	}
}