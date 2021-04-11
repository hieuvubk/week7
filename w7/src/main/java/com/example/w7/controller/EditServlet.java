package com.example.w7.controller;

import com.example.w7.model.Book;
import com.example.w7.ultis.BookDAO;

import javax.jms.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "editServlet", value = "/edit")
public class EditServlet extends HttpServlet {
    private String id;
    private static final Charset ISO = Charset.forName("ISO-8859-1");
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.setProperty("file.encoding", "UTF-8");
        id = request.getParameter("id");
        Book book = BookDAO.findBookById(id);

        System.out.println(book.getName());
        System.out.println(book.getPublisher());

        request.setAttribute("book", book);
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/book_infor.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

//        String id = (String) request.getAttribute("id");
//        System.out.println(request.getAttribute("id"));
        System.out.println(id);
        String name = new String(request.getParameter("name").getBytes(ISO), UTF_8);
        String publisher = new String(request.getParameter("publisher").getBytes(ISO), UTF_8);
        int price = Integer.parseInt(request.getParameter("price"));

        Book book = new Book(id, name, publisher, price);
        BookDAO.editBookById(id, book);
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}
