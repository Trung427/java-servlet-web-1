package com.example;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/info")
public class InfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='vi'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Thông tin nhóm</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; padding: 20px; background-color: #f0f8ff; }");
        out.println("h2 { color: #2c3e50; }");
        out.println("ul { background: #ffffff; padding: 15px; border-radius: 10px; box-shadow: 0 0 5px rgba(0,0,0,0.1); }");
        out.println("li { margin-bottom: 10px; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Thông tin nhóm:</h2>");
        out.println("<ul>");
        out.println("<li><strong>Nguyễn Văn A</strong> - MSSV: 123</li>");
        out.println("<li><strong>Trần Thị B</strong> - MSSV: 456</li>");
        out.println("<li><strong>Lê Văn C</strong> - MSSV: 789</li>");
        out.println("</ul>");
        out.println("</body>");
        out.println("</html>");
    }
}
