package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Appointment;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class DBappointment {
    private static ZoneId localZone = ZoneId.systemDefault();
    private static int appointmentCount =0;

    public static void getAppointmentByMonth() throws SQLException {
        Appointment.getAllAppointments().clear();
        int currentUser = DBuser.getLoggedOnUser().getID();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowPlusMonth = now.plusMonths(1);
        String sql = "SELECT * FROM appointments where Start BETWEEN '" + now + "' and '" + nowPlusMonth + "'";
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
        Appointment.getAllAppointments().clear();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowPlusWeek = now.plusWeeks(1);
        String sql = "SELECT * FROM appointments where Start BETWEEN '" + now + "' and '" + nowPlusWeek + "'";
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
    public static void getAppointmentByCustomer(int customerID) throws SQLException {
        Appointment.getAllAppointments().clear();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String sql = "SELECT * FROM appointments where Customer_Id = '"+customerID+"'";
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
            int userID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");
            Appointment.addAppointment(new Appointment(id, title, description, location, type, start, end, customerID, userID, contactID));
        }
    }
    public static long getAppointmentByCustomerTimes(int customerID) throws SQLException, ParseException {
        Appointment.getAllAppointments().clear();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long minutes= 0;
        String sql = "SELECT * FROM appointments where Customer_Id = '"+customerID+"'";
        PreparedStatement ps = DBconnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);
        while (rs.next()) {
            String start = rs.getString("Start");
            Date startTime =  formatter.parse(start);
            String end= rs.getString("End");
            Date endTime =  formatter.parse(end);
            minutes += ( endTime.getTime() - startTime.getTime());


        }
        long totalMinutes = TimeUnit.MILLISECONDS.toMinutes(minutes);
        System.out.println(totalMinutes);
        return totalMinutes;

    }
    public static void getAppointmentSoon() throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime userZoneNow = now.atZone(localZone);
        ZonedDateTime nowUTC = userZoneNow.withZoneSameInstant(ZoneOffset.UTC);
        ZonedDateTime utcPlus15 = nowUTC.plusMinutes(15);

        String start = nowUTC.format(formatter);
        String end = utcPlus15.format(formatter);

        int currentUser = DBuser.getLoggedOnUser().getID();

        System.out.print(now);
        String sql = "SELECT * FROM appointments where User_ID = '" + currentUser + "' AND Start BETWEEN '" + start+ "' and '"  + end + "'";
        PreparedStatement ps = DBconnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);
        while(rs.next()){
            System.out.print("ok");
            int id = rs.getInt("Appointment_ID");
            String startDB = rs.getString("Start");
            LocalDateTime ldtStart = LocalDateTime.parse( startDB ,formatter);
            OffsetDateTime odtStart = ldtStart.atOffset( ZoneOffset.UTC );
            ZonedDateTime zdtStart = odtStart.atZoneSameInstant( localZone );
            startDB = zdtStart.format(formatter);
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"You have appointment '"+id+"' starting at: " + startDB );
            alert.show();
            appointmentCount++;
        }
        if(appointmentCount ==0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"You have no upcoming appointments");
            alert.show();
        }

    }
    public static int getAppointmentTypeMonth(String month, String type) throws SQLException {
        int appointments = 0;
        String sql = "SELECT * FROM appointments where Type = '" + type + "' AND monthname(Start) = '"+month+"'";
        PreparedStatement ps = DBconnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);
        while(rs.next()) {
            appointments++;
        }
        return appointments;

    }

    public static void insert(String title, String description, String location, String type, Timestamp start, Timestamp end, Timestamp createDate, String createdBy, Timestamp lastUpdateDate,String lastUpdatedBy,int customerID,int userID, int contactID) throws SQLException{
        String sql = "INSERT INTO appointments (Title, Description,Location,Type,Start,End,Create_Date,Created_By,Last_Update,Last_Updated_By,Customer_ID, User_ID,Contact_ID)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = DBconnection.connection.prepareStatement(sql);
        ps.setString(1,title);
        ps.setString(2,description);
        ps.setString(3,location);
        ps.setString(4,type);
        ps.setTimestamp(5, start);
        ps.setTimestamp(6, end);
        ps.setTimestamp(7, createDate);
        ps.setString(8, createdBy);
        ps.setTimestamp(9,lastUpdateDate);
        ps.setString(10,lastUpdatedBy);
        ps.setInt(11,customerID);
        ps.setInt(12,userID);
        ps.setInt(13,contactID);
        ps.executeUpdate();
        System.out.print("Insert successful");

    }
    public static void update(String title, String description, String location, String type, Timestamp start,Timestamp end, Timestamp createDate,String createdBy, Timestamp lastUpdateDate,String lastUpdatedBy,int customerID, int userID, int contactID,int id) throws SQLException {
        String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Create_Date = ?,Created_By = ?, Last_Update = ?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID=? WHERE Appointment_ID = ?";
        PreparedStatement ps = DBconnection.connection.prepareStatement(sql);
        ps.setString(1,title);
        ps.setString(2,description);
        ps.setString(3,location);
        ps.setString(4,type);
        ps.setTimestamp(5, start);
        ps.setTimestamp(6, end);
        ps.setTimestamp(7, createDate);
        ps.setString(8, createdBy);
        ps.setTimestamp(9,lastUpdateDate);
        ps.setString(10,lastUpdatedBy);
        ps.setInt(11,customerID);
        ps.setInt(12,userID);
        ps.setInt(13,contactID);
        ps.setInt(14,id);
        ps.executeUpdate();


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

    public static Boolean checkOverLap(String start) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime checkStart = LocalDateTime.parse(start,formatter).atOffset( ZoneOffset.UTC).toLocalDateTime();
        LocalDateTime checkEnd = checkStart.plusHours(1);
        String sql = "SELECT * FROM appointments";
        PreparedStatement ps = DBconnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);

        while (rs.next()) {
            Timestamp startTimes = rs.getTimestamp("Start");
            Timestamp endTimes =rs.getTimestamp("End");
            LocalDateTime startDB = startTimes.toLocalDateTime();
            LocalDateTime endDB =  endTimes.toLocalDateTime();
            if(checkStart.isAfter(startDB) && checkStart.isBefore(endDB)){
                return true;
            }else if(startDB.isAfter(checkStart) && startDB.isBefore(checkEnd)) {
                return true;
            }else if(checkEnd.isAfter(startDB) && checkEnd.isBefore(endDB)){
               return true;
            }else if(endDB.isAfter(checkStart) && endDB.isBefore(checkEnd)){
                return true;
            }else if(checkStart.isEqual(startDB) && checkEnd.isEqual(endDB)){
                return true;
            }
        }
       return false;

    }
    public static Boolean checkOverLapUpdate(String start) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime checkStart = LocalDateTime.parse(start,formatter).atOffset( ZoneOffset.UTC).toLocalDateTime();
        LocalDateTime checkEnd = checkStart.plusHours(1);
        String sql = "SELECT * FROM appointments";
        PreparedStatement ps = DBconnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);

        while (rs.next()) {
            Timestamp startTimes = rs.getTimestamp("Start");
            Timestamp endTimes =rs.getTimestamp("End");
            LocalDateTime startDB = startTimes.toLocalDateTime();
            LocalDateTime endDB =  endTimes.toLocalDateTime();
            if(checkStart.isAfter(startDB) && checkStart.isBefore(endDB)){
                return true;
            }else if(startDB.isAfter(checkStart) && startDB.isBefore(checkEnd)) {
                return true;
            }else if(checkEnd.isAfter(startDB) && checkEnd.isBefore(endDB)){
                return true;
            }else if(endDB.isAfter(checkStart) && endDB.isBefore(checkEnd)){
                return true;
            }else if(checkStart.isEqual(startDB) && checkEnd.isEqual(endDB)){
                return false;
            }
        }
        return false;

    }

    public static void delete(int ID) throws SQLException {
        String sql = "DELETE FROM appointments where appointment_ID = ?";
        PreparedStatement ps = DBconnection.connection.prepareStatement(sql);
        ps.setInt(1,ID);
        ps.executeUpdate();
    }

    public static String select( String columnName,int ID) throws SQLException {
        String sql = "SELECT * FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = DBconnection.connection.prepareStatement(sql);
        ps.setInt(1,ID);
        ResultSet rs = ps.executeQuery();
        rs.next();
        String result  = rs.getString(columnName);
        return result;
    }
    public static ObservableList<String> selectType() throws SQLException {
        ObservableList<String> typeList = FXCollections.observableArrayList();
        String sql = "SELECT Distinct(Type) FROM appointments";
        PreparedStatement ps = DBconnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            String type = rs.getString("Type");
            typeList.add(type);
        }
        return typeList;
    }

    public static Timestamp selectTimestamp( String columnName,int ID) throws SQLException {
        String sql = "SELECT * FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = DBconnection.connection.prepareStatement(sql);
        ps.setInt(1,ID);
        ResultSet rs = ps.executeQuery();
        rs.next();
        Timestamp result  = rs.getTimestamp(columnName);
        return result;
    }
}
