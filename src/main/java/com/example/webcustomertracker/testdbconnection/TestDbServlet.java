package com.example.webcustomertracker.testdbconnection;

import java.io.*;
import java.rmi.ServerException;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class TestDbServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String user = "springstudent";
        String pwd = "springstudent";
        String url = "jdbc:mysql://localhost:3306/web_customer_tracker?allowPublicKeyRetrieval=true&useSSL=false";
        String driver = "com.mysql.jdbc.Driver";
        PrintWriter out = response.getWriter();

        try {
            out.println("Connecting to DB: "+ url);
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url,user, pwd);
            out.println("connected to DB");
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServerException(e.getMessage());
        }


    }

    public void destroy() {
    }
}