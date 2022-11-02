package dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * connects to the database
 */
public abstract class DBconnection {


    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER";
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String userName = "sqlUser";
    private static final String password = "Passw0rd!";
    public static Connection connection;

    /**connects to database
     *
     */
    public static void openConnection(){
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(jdbcUrl,userName,password);
            System.out.println("Connection Successful");
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());

        }
    }

    /**closes the connection with the database
     *
     */
    public static void closeConnection(){
        try{
            connection.close();
            System.out.println("Connection Closed");
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());

        }
    }
}
