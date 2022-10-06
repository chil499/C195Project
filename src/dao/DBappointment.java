package dao;

import javafx.scene.control.Alert;
import model.Appointment;
import model.Customer;

import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class DBappointment {
    private static ZoneId localZone = ZoneId.systemDefault();
    public static int getNewID() throws SQLException {
        int newID =1;
        String sql = "SELECT * FROM appointments";
        PreparedStatement ps = DBconnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);
        while(rs.next()){
            newID++;
        }
        return newID;
    }
    public static void getAppointmentByMonth() throws SQLException {
        Appointment.getAllAppointments().clear();
        int currentUser = DBuser.getLoggedOnUser().getID();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowPlusMonth = now.plusMonths(1);
        String sql = "SELECT * FROM appointments where User_ID = '" + currentUser + "' AND Start BETWEEN '" + now + "' and '" + nowPlusMonth + "'";
        PreparedStatement ps = DBconnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);

        while (rs.next()) {
            int id = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            String start = rs.getString("Start");

            LocalDateTime ldtStart = LocalDateTime.parse( start ,formatter);
            OffsetDateTime odtStart = ldtStart.atOffset( ZoneOffset.UTC );
            ZonedDateTime zdtStart = odtStart.atZoneSameInstant( localZone);
            start = zdtStart.format(formatter);

            String end= rs.getString("End");
            LocalDateTime ldtEnd = LocalDateTime.parse( end ,formatter);
            OffsetDateTime odtEnd = ldtEnd.atOffset( ZoneOffset.UTC );
            ZonedDateTime zdtEnd = odtEnd.atZoneSameInstant( localZone );
            end = zdtEnd.format(formatter);


            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");
            Appointment.addAppointment(new Appointment(id, title, description, location, type, start, end, customerID, userID, contactID));
        }
    }

    public static void getAppointmentByWeek() throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        int currentUser = DBuser.getLoggedOnUser().getID();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowPlusWeek = now.plusWeeks(1);
        String sql = "SELECT * FROM appointments where User_ID = '" + currentUser + "' AND Start BETWEEN '" + now + "' and '" + nowPlusWeek + "'";
        PreparedStatement ps = DBconnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("Appointment_ID");
            String title = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            String start = rs.getString("Start");
            LocalDateTime ldtStart = LocalDateTime.parse( start ,formatter);
            OffsetDateTime odtStart = ldtStart.atOffset( ZoneOffset.UTC );
            ZonedDateTime zdtStart = odtStart.atZoneSameInstant( localZone );
            start = zdtStart.format(formatter);
            String end= rs.getString("End");
            LocalDateTime ldtEnd = LocalDateTime.parse( end ,formatter);
            OffsetDateTime odtEnd = ldtEnd.atOffset( ZoneOffset.UTC );
            ZonedDateTime zdtEnd = odtEnd.atZoneSameInstant( localZone );
            end = zdtEnd.format(formatter);
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");
            Appointment.addAppointment(new Appointment(id, title, description, location, type, start, end, customerID, userID, contactID));
        }
    }

    public static void insert(int id, String title, String description, String location, String type, Timestamp start, Timestamp end, Timestamp createDate, String createdBy, Timestamp lastUpdateDate,String lastUpdatedBy,int customerID,int userID, int contactID) throws SQLException{
        String sql = "INSERT INTO appointments VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = DBconnection.connection.prepareStatement(sql);
        ps.setInt(1,id);
        ps.setString(2,title);
        ps.setString(3,description);
        ps.setString(4,location);
        ps.setString(5,type);
        ps.setTimestamp(6, start);
        ps.setTimestamp(7, end);
        ps.setTimestamp(8, createDate);
        ps.setString(9, createdBy);
        ps.setTimestamp(10,lastUpdateDate);
        ps.setString(11,lastUpdatedBy);
        ps.setInt(12,customerID);
        ps.setInt(13,userID);
        ps.setInt(14,contactID);
        ps.executeUpdate();
        System.out.print("Insert successful");

    }
    public static Boolean checkBusinessHour(String start,String localZone){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZonedDateTime startZoned = ZonedDateTime.of((LocalDateTime.parse(start,formatter)),ZoneId.of(localZone));
        Instant currentZoneToESTInstant = startZoned.toInstant();
        ZonedDateTime est = currentZoneToESTInstant.atZone(ZoneId.of("US/Eastern"));
        if(est.isBefore(ZonedDateTime.now())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"This date and time has already passed.");
            alert.show();
            return false;
        }
        else if(est.getDayOfWeek()==DayOfWeek.SATURDAY || est.getDayOfWeek()==DayOfWeek.SUNDAY){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Appointments can't be scheduled for the Saturday or Sunday");
            alert.show();
            return false;
        }
        else if(est.getHour()>8 && est.getHour()<=21){

            return true;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"Appointments can't be made outside of business hours: 8 AM 10 PM EST");
        alert.show();
        return false;
    }

}
