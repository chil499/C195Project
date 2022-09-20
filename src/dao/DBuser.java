package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBuser {
    public static Boolean checkLogin(String userName, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE User_Name = '" + userName + "' AND Password ='"+ password + "'";
        PreparedStatement ps = DBconnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);
        if(rs.next()){
            return Boolean.TRUE;
        }
        else{
            return Boolean.FALSE;
        }
        
    }
}
