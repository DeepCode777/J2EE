package com.servletdemo;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class show extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        res.setContentType("text/html");

        PrintWriter pw = res.getWriter();

        pw.print("<h2><i>Form Data</i></h2>");
        pw.print("<br>");

        String nm = req.getParameter("name");
        int ag = Integer.parseInt(req.getParameter("age"));

        pw.print("Name : " + nm + "<br>");
        pw.print("Age : " + ag + "<br>");
    }
}