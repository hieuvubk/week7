package com.example.w7.ultis;

import com.example.w7.model.Book;

import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    private static final Charset ISO = Charset.forName("ISO-8859-1");
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    public static List<Book> findBookByName(String name) {
        List<Book> list = new ArrayList<>();
        Book book = new Book();
        try {
            Connection conn = MySQLConnector.getConnection();
            Statement st = conn.createStatement();
            String query = "select * from books where bookName like n\'%" + name + "%\'";
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet result = stm.executeQuery();

            while (result.next()) {
                list.add(new Book(result.getString(1),
                                  result.getString(2),
                                  result.getString(3),
                                  result.getInt(4)));
            }
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public static Book findBookById(String id) {
        Book book = null;
        try {
            Connection conn = MySQLConnector.getConnection();
            Statement st = conn.createStatement();
            String query = "select * from books where id = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, id);
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                book = new Book(result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getInt(4));
            }
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return book;
    }

    public static void editBookById(String id, Book book) {
        System.out.println(book.getName());
        System.out.println(book.getPublisher());
        try {
            Connection conn = MySQLConnector.getConnection();
            Statement st = conn.createStatement();
            String update = "UPDATE books SET bookName = ?, publisher = ?, price = ?  WHERE id = ?";
            PreparedStatement stm = conn.prepareStatement(update);
            stm.setString(1, book.getName());
            stm.setString(2, book.getPublisher());
            stm.setInt(3, book.getPrice());
            stm.setString(4, id);
            stm.executeUpdate();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void deleteBookById(String id) {
        try {
            Connection conn = MySQLConnector.getConnection();
            Statement st = conn.createStatement();
            String update = "delete from books where id = ?";
            PreparedStatement stm = conn.prepareStatement(update);
            stm.setString(1, id);
            stm.executeUpdate();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
