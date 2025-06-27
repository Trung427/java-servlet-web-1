package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            // Tạo bảng nếu chưa có
            String createTableSQL = "CREATE TABLE IF NOT EXISTS greeting (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "message VARCHAR(255) NOT NULL)";
            stmt.executeUpdate(createTableSQL);

            // Thêm dữ liệu mẫu nếu bảng rỗng
            String checkDataSQL = "SELECT COUNT(*) FROM greeting";
            ResultSet rs = stmt.executeQuery(checkDataSQL);
            int count = 0;
            if (rs.next()) {
                count = rs.getInt(1);
            }
            if (count == 0) {
                String insertSQL = "INSERT INTO greeting (message) VALUES ('Hello, World! I am HungNgoc1 22.06.2025')";
                stmt.executeUpdate(insertSQL);
            }

            // Lấy dữ liệu từ bảng
            String selectSQL = "SELECT * FROM greeting";
            rs = stmt.executeQuery(selectSQL);

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
            out.println("    flex-direction: column;");
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
            out.println("    margin-bottom: 30px;");
            out.println("}");
            out.println(".card h1 {");
            out.println("    color: #333;");
            out.println("    font-size: 28px;");
            out.println("}");
            out.println("form { margin-bottom: 20px; }");
            out.println("input[type='text'] { padding: 8px; width: 250px; border-radius: 5px; border: 1px solid #ccc; }");
            out.println("button { padding: 8px 16px; border: none; border-radius: 5px; background: #74ebd5; color: #333; font-weight: bold; cursor: pointer; }");
            out.println("button:hover { background: #9face6; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<form method='post' action='hello'>");
            out.println("<input type='text' name='message' placeholder='Nhập nội dung mới...' required />");
            out.println("<button type='submit'>Thêm</button>");
            out.println("</form>");
            out.println("<div class='card'>");
            out.println("<h1>Greetings from Database:</h1>");
            while (rs.next()) {
                out.println("<div>" + rs.getString("message") + "</div>");
            }
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<div style='color:red'>Lỗi kết nối database: " + e.getMessage() + "</div>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String message = request.getParameter("message");
        if (message != null && !message.trim().isEmpty()) {
            try (Connection conn = DBConnection.getConnection();
                 Statement stmt = conn.createStatement()) {
                String insertSQL = "INSERT INTO greeting (message) VALUES ('" + message.replace("'", "''") + "')";
                stmt.executeUpdate(insertSQL);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect("hello");
    }
}
