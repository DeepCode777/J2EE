import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String userEmail = request.getParameter("email");
        String userPassword = request.getParameter("password");
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment", "root", "deep");
            
            PreparedStatement ps = con.prepareStatement("SELECT emal, password, fullname FROM register WHERE emal = ?");
            ps.setString(1, userEmail);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String pass = rs.getString("password");
                if (pass.equals(userPassword)) {
                    HttpSession session = request.getSession(true);
                    String username = rs.getString("fullname");
                    session.setAttribute("fullName", username);
                    response.sendRedirect("dashboard");
                } else {
                    out.print("<h3 style='color:red;'>Invalid Email or Password</h3>");
                    request.getRequestDispatcher("login.html").include(request, response);
                }
            } else {
                out.print("<h3 style='color:red;'>Invalid Email or Password</h3>");
                request.getRequestDispatcher("login.html").include(request, response);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.print("<h3 style='color:red;'>An error occurred. Please try again.</h3>");
            request.getRequestDispatcher("login.html").include(request, response);
        }
    }
}