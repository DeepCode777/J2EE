package seesion_cookie_manage;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/seeCookie")
public class seeCookie extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		Cookie[] cooks = req.getCookies();
		
		if(cooks != null) {
			for(int i = 0; i < cooks.length; i++) {
					Cookie cookie = cooks[i];
					out.print(cookie.getName() + ", " );
					out.print(cookie.getValue() + "<br/>" );
			}
		}else {
			out.print("Cookies Not Found");
		}
	}
}


