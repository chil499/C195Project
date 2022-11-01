package dao;

import model.Contact;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBcontact {

    //add contact objects from db
    public static void addContactObjects() throws SQLException {
        String sql = "SELECT * FROM contacts";
        PreparedStatement ps = DBconnection.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery(sql);
        while(rs.next()){
            int id = rs.getInt("Contact_ID");
            String name = rs.getString("Contact_name");
            String email =rs.getString("Email");
            Contact.addContact(new Contact(id,name,email));
        }

    }


}
