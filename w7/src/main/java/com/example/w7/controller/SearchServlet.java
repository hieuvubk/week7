package com.example.w7.controller;

import com.example.w7.model.Book;
import com.example.w7.ultis.BookDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet(name = "searchServlet", value = "/index")
public class SearchServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String input = request.getParameter("search");
        System.out.println(input);
        List<Book> books = BookDAO.findBookByName(input);
        request.setAttribute("input", input);
        request.setAttribute("books", books);

        System.out.println(input);

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/index.jsp");
        dispatcher.forward(request,response);

    }
}
