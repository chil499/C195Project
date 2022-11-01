package dao;

import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBuser {
    private static User loggedOnUser;

    //check if valid login
    public static Boolean checkLogin(String userName, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE User_Name = '" + userName + "' AND Password ='"+ password + "'";
        PreparedStatement ps = DBconnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);
        if(rs.next()){
            loggedOnUser = new User(rs.getInt("User_ID"), rs.getString("User_Name"));
            return Boolean.TRUE;
        }
        else{
            return Boolean.FALSE;
        }

    }
    //returns user
    public static User getLoggedOnUser() {
        return loggedOnUser;
    }
}
