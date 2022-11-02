package dao;

import model.Country;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * gets country information from database
 */
public class DBcountry {
    /**adds country objects from db
     *
     * @throws SQLException
     */
    public static void addCountryObjects() throws SQLException {
        String sql = "SELECT * FROM countries";
        PreparedStatement ps = DBconnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);
        while(rs.next()){
            int id = rs.getInt("Country_ID");
            String name = rs.getString("Country");
            Country.addCountries(new Country(id,name));
        }

    }

    /**returns the country id from division id
     *
     * @param divisionID
     * @return
     * @throws SQLException
     */
    public static int returnCountryID(int divisionID) throws SQLException {
        String sql = "SELECT Country_ID FROM first_level_divisions WHERE Division_ID = '" + divisionID + "'";
        PreparedStatement ps = DBconnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);
        rs.next();
        int countryID = rs.getInt("Country_ID");
        return countryID;
    }

    /**returns country name from countryid
     *
     * @param countryID
     * @return
     * @throws SQLException
     */
    public static String returnCountry(int countryID) throws SQLException {
        String sql = "SELECT Country FROM countries WHERE Country_ID = '" + countryID + "'";
        PreparedStatement ps = DBconnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);
        rs.next();
        String country = rs.getString("Country");

        return country;
    }
}
