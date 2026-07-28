import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;


public class Display extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String name = req.getParameter("username");
        String password = req.getParameter("password");
        String country = req.getParameter("country");
        
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html><head><title>Result</title><style>th,td{padding : 5px; text-align:center;}</style></head><body>");
            out.println("<h2>Submitted Details</h2>");
            out.println("<table border=1>");
            out.println("<tr>");
            out.println("<th> Name </th>");
            out.println("<th> password </th>");
            out.println("<th> country </th>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>" + name + "</td>");
            out.println("<td>" + password + "</td>");
            out.println("<td>" + country + "</td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("</body></html>");
    }
}