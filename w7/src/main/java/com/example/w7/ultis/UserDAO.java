package com.example.w7.ultis;

import com.example.w7.model.User;

import java.sql.*;

public class UserDAO {

    public static User findUser(String username, String password) throws SQLException {
        User user = new User();
        try {
            Connection conn = MySQLConnector.getConnection();
            Statement st = conn.createStatement();
            String query = "select * from users where username = ? and password = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet result = stm.executeQuery();

            while (result.next()) {
                user.setUsername(result.getString(1));
                user.setPassword(result.getString(2));
            }
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return user;



    }
}
