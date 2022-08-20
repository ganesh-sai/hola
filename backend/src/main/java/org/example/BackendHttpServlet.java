package org.example;


import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;

@WebServlet(urlPatterns = "/api/backend")
public class BackendHttpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(req.getParameterMap());
        String greeting = req.getParameter("greeting");
        System.out.println(greeting);
        ResponseDTO response = new ResponseDTO();
        response.setGreeting(greeting + " from cluster backend");

        response.setTime(Instant.now().toEpochMilli());
        response.setIpAddress(getIp());

        try {
            PrintWriter writer = resp.getWriter();
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(writer, response);
        } catch (IOException e) {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(resp.getWriter(), "Couldn't parse the value");
        }
    }

    private String getIp() {
        String hostname = null;

        try {
            hostname = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            hostname = "unknown";
        }
        return hostname;
    }
}