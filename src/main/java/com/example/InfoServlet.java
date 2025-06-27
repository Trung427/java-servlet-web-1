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

@WebServlet("/info")
public class InfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            // Tạo bảng nếu chưa có
            String createTableSQL = "CREATE TABLE IF NOT EXISTS info (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "mssv VARCHAR(20) NOT NULL)";
            stmt.executeUpdate(createTableSQL);

            // Thêm dữ liệu mẫu nếu bảng rỗng
            String checkDataSQL = "SELECT COUNT(*) FROM info";
            ResultSet rs = stmt.executeQuery(checkDataSQL);
            int count = 0;
            if (rs.next()) {
                count = rs.getInt(1);
            }
            if (count == 0) {
                stmt.executeUpdate("INSERT INTO info (name, mssv) VALUES ('Nguyễn Văn A', '123')");
                stmt.executeUpdate("INSERT INTO info (name, mssv) VALUES ('Trần Thị B', '456')");
                stmt.executeUpdate("INSERT INTO info (name, mssv) VALUES ('Lê Văn C', '789')");
            }

            // Lấy dữ liệu từ bảng
            String selectSQL = "SELECT * FROM info";
            rs = stmt.executeQuery(selectSQL);

            out.println("<!DOCTYPE html>");
            out.println("<html lang='vi'>");
            out.println("<head><meta charset='UTF-8'><title>Thông tin nhóm</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; background: #f0f4f8; display: flex; flex-direction: column; align-items: center; min-height: 100vh; margin: 0; }");
            out.println(".container { background: #fff; padding: 30px; border-radius: 12px; box-shadow: 0 4px 12px rgba(0,0,0,0.12); margin-top: 40px; }");
            out.println("form { margin-bottom: 20px; }");
            out.println("input[type='text'] { padding: 8px; margin-right: 10px; border-radius: 5px; border: 1px solid #ccc; }");
            out.println("button { padding: 8px 16px; border: none; border-radius: 5px; background: #74ebd5; color: #333; font-weight: bold; cursor: pointer; }");
            out.println("button:hover { background: #9face6; }");
            out.println("h2 { color: #333; }");
            out.println("ul { padding-left: 20px; }");
            out.println("</style></head>");
            out.println("<body>");
            out.println("<div class='container'>");
            out.println("<h2>Thông tin nhóm:</h2>");
            out.println("<form method='post' action='info'>");
            out.println("<input type='text' name='name' placeholder='Tên thành viên' required />");
            out.println("<input type='text' name='mssv' placeholder='MSSV' required />");
            out.println("<button type='submit'>Thêm</button>");
            out.println("</form>");
            out.println("<ul>");
            while (rs.next()) {
                out.println("<li>" + rs.getString("name") + " - MSSV: " + rs.getString("mssv") + "</li>");
            }
            out.println("</ul>");
            out.println("</div>");
            out.println("</body></html>");
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("<div style='color:red'>Lỗi kết nối database: " + e.getMessage() + "</div>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String mssv = request.getParameter("mssv");
        if (name != null && !name.trim().isEmpty() && mssv != null && !mssv.trim().isEmpty()) {
            try (Connection conn = DBConnection.getConnection();
                 Statement stmt = conn.createStatement()) {
                String insertSQL = "INSERT INTO info (name, mssv) VALUES ('" + name.replace("'", "''") + "', '" + mssv.replace("'", "''") + "')";
                stmt.executeUpdate(insertSQL);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect("info");
    }
}
