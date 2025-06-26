package com.example;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/hello9")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html; charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Hello Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Hello, World</h1>");
            out.println("<p>Tôi là một servlet. Chú ý nhánh: <strong>feature/hello-19.06.2025</strong></p>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}