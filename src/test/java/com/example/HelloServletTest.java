package com.example;

import static org.mockito.Mockito.*;

import java.io.PrintWriter;
import java.io.StringWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;

public class HelloServletTest {
    @Test
    void testDoGet() throws Exception {
        HelloServlet servlet = new HelloServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        servlet.doGet(request, response);

        verify(response).setContentType("text/html");
        writer.flush();
        String output = stringWriter.toString().trim();
        org.junit.jupiter.api.Assertions.assertEquals(
                "<h1>Hello, World, I am a servlet, 10.06.2025!</h1>", output);
    }
}
