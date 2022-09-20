package dao;

import java.sql.Statement;

public class DBuser {
    public static Boolean checkLogin(String Username, String Password){
        Statement statement = DBconnection.openConnection().createStatement();
        
    }
}
