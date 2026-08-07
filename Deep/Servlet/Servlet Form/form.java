import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class form extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        res.setContentType("text/html");

        PrintWriter pw = res.getWriter();

        String nm = req.getParameter("email");
        String pass = req.getParameter("password");
        String con = req.getParameter("contry");

        pw.print("<!DOCTYPE html>");
        pw.print("<html>");
        pw.print("<head>");
            pw.print("<title>Registration Data</title>");
        pw.print("</head>");
        pw.print("<body>");
            pw.print("<h2><i><u>Form Data</u></i></h2>");
            pw.print("<br><br>");

            pw.print("<table border=1>");
                pw.print("<tr>");
                    pw.print("<th>Username</th>");
                    pw.print("<th>Password</th>");
                    pw.print("<th>Contry</th>");
                pw.print("</tr>");

                pw.print("<tr>");
                    pw.print("<td>" + nm + "</td>");
                    pw.print("<td>" + pass + "</td>");
                    pw.print("<td>" + con + "</td>");
                pw.print("</tr>");

            pw.print("</table>");
        pw.print("</body>");
        pw.print("</html>");

    }
}
