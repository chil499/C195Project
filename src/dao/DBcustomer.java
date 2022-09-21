package dao;

import javafx.util.converter.TimeStringConverter;
import model.Customer;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DBcustomer {

    public static void addCustomerObjects() throws SQLException {
        String sql = "SELECT * FROM customers";
        PreparedStatement ps = DBconnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);
        while(rs.next()){
            int id = rs.getInt("Customer_ID");
            String name = rs.getString("Customer_Name");
            String address = rs.getString("Address");
            String zip = rs.getString("Postal_Code");
            String phone = rs.getString("Phone");
            int divisionID = rs.getInt("Division_ID");
            String state = DBfirstLevelDivision.returnState(divisionID);
            String country = DBcountry.returnCountry(DBcountry.returnCountryID(divisionID));
            Customer.addCustomer(new Customer(id,name, address,zip,phone,divisionID,state,country));
        }


    }
    public static void insert(int id,String name, String address, String zip, String phone, String createDate, String createdBy, String lastUpdateDate, String lastUpdatedBy, int divisionID) throws SQLException{
         String sql = "INSERT INTO customers VALUES(?,?,?,?,?,?,?,?,?,?)";
         PreparedStatement ps = DBconnection.connection.prepareStatement(sql);
         ps.setInt(1,id);
         ps.setString(2,name);
         ps.setString(3,address);
         ps.setString(4,zip);
         ps.setString(5,phone);
         ps.setString(6, createDate);
         ps.setString(7,createdBy);
         ps.setString(8, lastUpdateDate);
         ps.setString(9, lastUpdatedBy);
         ps.setInt(10,divisionID);
         ps.executeUpdate();
         System.out.print("Insert successful");

    }



}
