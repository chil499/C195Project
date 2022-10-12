package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Appointment {
    private int id;
    private String title;
    private String description;
    private String location;
    private String type;
    private String start;
    private String end;
    private int userID;
    private int customerID;
    private int contactID;
    private static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    public static ObservableList<Appointment> getAllAppointments(){return allAppointments;}

    public static void addAppointment(Appointment appointment){allAppointments.add(appointment);}

    public Appointment(int id, String title, String description, String location, String type, String start,String end, int customerID, int userID, int contactID) {
        this.id = id;
        this.title=title;
        this.description=description;
        this.location=location;
        this.type=type;
        this.start=start;
        this.end=end;
        this.userID = userID;
        this.customerID=customerID;
        this.contactID = contactID;
    }
    public void setId(){this.id=id;}
    public int getId(){return id;}

    public void setTitle(){this.title=title;}
    public String getTitle(){return title;}

    public void setDescription(){this.description=description;}
    public String getDescription(){return description;}

    public void setLocation(){this.location = location;}
    public String getLocation(){return location;}

    public void setType(){this.type = type; }
    public String getType(){return type;}

    public void setStart(){this.start =start;}
    public String getStart(){return start;}

    public void setEnd(){this.end = end;}
    public String getEnd(){return end;}

    public void setUserID(){this.userID=userID;}
    public int getUserID(){return userID; }

    public void setCustomerID(){this.customerID=customerID;}
    public int getCustomerID(){return customerID;}

    public void setContactID(){this.contactID = contactID;}
    public int getContactID(){return contactID;}

}
