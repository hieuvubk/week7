package com.example.w7.controller;

import com.example.w7.model.User;
import com.example.w7.ultis.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "loginServler", value = "/login")
public class LoginServlet extends HttpServlet {

    public void init() {
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/login.jsp");
        dispatcher.forward(request,response);

//        response.setContentType("text/html");
//
//        // Hello
//        PrintWriter out = response.getWriter();
//        out.println("<html><body>");
//        out.println("<h1>" + "message" + "</h1>");
//        out.println("</body></html>");

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = null;

        try {
            user = UserDAO.findUser(username,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if(user!=null) {

//            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/index.jsp");
//            dispatcher.forward(request,response);

            response.sendRedirect( request.getContextPath() + "/index.jsp");
            System.out.println("ákjdjsakd");
        }
        else System.out.println("wrong");
    }
}
