package com.example;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Hello Page</title>");
        out.println("<style>");
        out.println("body {");
        out.println("    background: linear-gradient(135deg, #74ebd5, #9face6);");
        out.println("    font-family: Arial, sans-serif;");
        out.println("    display: flex;");
        out.println("    justify-content: center;");
        out.println("    align-items: center;");
        out.println("    height: 100vh;");
        out.println("    margin: 0;");
        out.println("}");
        out.println(".card {");
        out.println("    background-color: white;");
        out.println("    padding: 40px;");
        out.println("    border-radius: 15px;");
        out.println("    box-shadow: 0 8px 16px rgba(0, 0, 0, 0.25);");
        out.println("    text-align: center;");
        out.println("}");
        out.println(".card h1 {");
        out.println("    color: #333;");
        out.println("    font-size: 28px;");
        out.println("}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='card'>");
        out.println("<h1>Hello, World!<br>I am HungNgoc11<br>22.06.2025</h1>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
