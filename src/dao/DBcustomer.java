package dao;

import javafx.scene.control.Alert;
import javafx.util.converter.TimeStringConverter;
import model.Customer;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * gets customer information from database
 */
public class DBcustomer {

    /**returns customer objects
     *
     * @throws SQLException
     */
    public static void getCustomerObjects() throws SQLException {
        Customer.getAllCustomers().clear();
        Customer.getAllCustomers();
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
            Customer.addCustomer(new Customer(id,name, address,zip,phone,divisionID,country,state));
        }


    }

    /**inserts new customer
     *
     * @param id
     * @param name
     * @param address
     * @param zip
     * @param phone
     * @param createDate
     * @param createdBy
     * @param lastUpdateDate
     * @param lastUpdatedBy
     * @param divisionID
     * @throws SQLException
     */
    public static void insert(int id,String name, String address, String zip, String phone, Timestamp createDate, String createdBy, Timestamp lastUpdateDate, String lastUpdatedBy, int divisionID) throws SQLException{
         String sql = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID) VALUES(?,?,?,?,?,?,?,?,?)";
         PreparedStatement ps = DBconnection.connection.prepareStatement(sql);

         ps.setString(1,name);
         ps.setString(2,address);
         ps.setString(3,zip);
         ps.setString(4,phone);
         ps.setTimestamp(5, createDate);
         ps.setString(6,createdBy);
         ps.setTimestamp(7, lastUpdateDate);
         ps.setString(8, lastUpdatedBy);
         ps.setInt(9,divisionID);
         ps.executeUpdate();
         System.out.print("Insert successful");

    }

    /**updates customer
     *
     * @param name
     * @param address
     * @param zip
     * @param phone
     * @param createDate
     * @param createdBy
     * @param lastUpdateDate
     * @param lastUpdatedBy
     * @param divisionID
     * @param id
     * @throws SQLException
     */
    public static void update(String name, String address, String zip, String phone, Timestamp createDate, String createdBy, Timestamp lastUpdateDate, String lastUpdatedBy, int divisionID,int id) throws SQLException {
        String sql = "UPDATE customers SET Customer_Name = ?,Address = ?, Postal_Code = ?,Phone = ?, Create_Date =?,Created_By = ?,Last_Update = ?,Last_Updated_By = ?,Division_ID = ? WHERE Customer_ID = ?";
        PreparedStatement ps = DBconnection.connection.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, address);
        ps.setString(3, zip);
        ps.setString(4, phone);
        ps.setTimestamp(5, createDate);
        ps.setString(6, createdBy);
        ps.setTimestamp(7, lastUpdateDate);
        ps.setString(8, lastUpdatedBy);
        ps.setInt(9, divisionID);
        ps.setInt(10, id);
        ps.executeUpdate();
        System.out.print("Update successful");


    }

    /**deletes customer
     *
     * @param ID
     * @throws SQLException
     */
    public static void delete(int ID) throws SQLException {
        String sql = "DELETE FROM customers where Customer_ID = ?";
        PreparedStatement ps = DBconnection.connection.prepareStatement(sql);
        ps.setInt(1,ID);
        ps.executeUpdate();



    }

    /**selects customer
     *
     * @param columnName
     * @param ID
     * @return
     * @throws SQLException
     */
    public static String select( String columnName,int ID) throws SQLException {
        String sql = "SELECT * FROM customers WHERE Customer_ID = ?";
        PreparedStatement ps = DBconnection.connection.prepareStatement(sql);
        ps.setInt(1,ID);
        ResultSet rs = ps.executeQuery();
        rs.next();
        String result  = rs.getString(columnName);
        return result;
    }

    /**gets time stamp from customer
     *
     * @param columnName
     * @param ID
     * @return
     * @throws SQLException
     */
    public static Timestamp selectTimestamp( String columnName,int ID) throws SQLException {
        String sql = "SELECT * FROM customers WHERE Customer_ID = ?";
        PreparedStatement ps = DBconnection.connection.prepareStatement(sql);
        ps.setInt(1,ID);
        ResultSet rs = ps.executeQuery();
        rs.next();
        Timestamp result  = rs.getTimestamp(columnName);
        return result;
    }

    /**deletes appointments with customer
     *
     * @param ID
     * @throws SQLException
     */
    public static void deleteCustomerAppointments(int ID) throws SQLException {

        String sql = "DELETE FROM appointments WHERE Customer_ID = ?";
        PreparedStatement ps = DBconnection.connection.prepareStatement(sql);
        ps.setInt(1,ID);
        ps.executeUpdate();
    }

    }
