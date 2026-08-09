package seesion_cookie_manage;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/session")
public class session extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		
		HttpSession session = req.getSession(true);
		int count = 0;
		if(session.isNew()) {
			pw.print("session created");
			session.setAttribute("visitCount", count);
		}else {
			int session_count = (Integer)session.getAttribute("visitCount") + 1;
			session.setAttribute("visitCount", session_count);
			pw.print(session_count);
		}
		
	}
}

