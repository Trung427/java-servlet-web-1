package com.example;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

class HelloServletTest {

    @Test
    void testDoGetOutput() throws Exception {
        // Tạo servlet instance
        HelloServlet servlet = new HelloServlet();

        // Mock request & response
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);

        // Capture nội dung xuất ra
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);

        // Gọi phương thức doGet
        servlet.doGet(request, response);

        // Flush dữ liệu
        writer.flush();

        // Kiểm tra đầu ra có chứa các nội dung mong muốn không
        String output = stringWriter.toString();
        assertTrue(output.contains("Hello, World!"));
        assertTrue(output.contains("HungNgoc11"));
        assertTrue(output.contains("2025"));
        assertTrue(output.contains("<html")); // Đảm bảo có cấu trúc HTML
    }
}
