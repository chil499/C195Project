package dao;


import model.FirstLevelDivision;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBfirstLevelDivision {

    public static void addfirstLevelDivsionObjects() throws SQLException {
        String sql = "SELECT * FROM first_level_divisions";
        PreparedStatement ps = DBconnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);
        while (rs.next()) {
            int divisionID = rs.getInt("Division_ID");
            String name = rs.getString("Division");
            int countryID = rs.getInt("Country_ID");
            FirstLevelDivision.addStates(new FirstLevelDivision(divisionID, name, countryID));
        }
    }


    public static String returnState(int divisionID) throws SQLException {
        String sql = "SELECT Division FROM first_level_divisions WHERE Division_ID = '" + divisionID + "'";
        PreparedStatement ps = DBconnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);
        rs.next();
        String state = rs.getString("Division");

        return state;
    }
}
