package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBcustomer {
    public static void fillCustomerTable() throws SQLException {
        String sql = "SELECT * FROM customers";
        PreparedStatement ps = DBconnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);
        while(rs.next()){

        }

    }
}
