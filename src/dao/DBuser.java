package dao;

import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * getins user information from database
 */
public class DBuser {
    private static User loggedOnUser;

    /**check if valid login
     *
     * @param userName
     * @param password
     * @return
     * @throws SQLException
     */
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

    /**returns user
     *
     * @return
     */
    public static User getLoggedOnUser() {
        return loggedOnUser;
    }
}
