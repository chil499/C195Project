package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * appointment class
 */
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

    /**
     * sets id
     */
    public void setId(){this.id=id;}

    /**
     * gets id
     * @return
     */
    public int getId(){return id;}

    /**
     * sets title
     */
    public void setTitle(){this.title=title;}

    /**
     * gets title
     * @return
     */
    public String getTitle(){return title;}

    /**
     * sets description
     */
    public void setDescription(){this.description=description;}

    /**
     * gets description
     * @return
     */
    public String getDescription(){return description;}

    /**
     * sets location
     */
    public void setLocation(){this.location = location;}

    /**
     * gets location
     * @return
     */
    public String getLocation(){return location;}

    /**
     * sets type
     */
    public void setType(){this.type = type; }

    /**
     * gets Type
     * @return
     */
    public String getType(){return type;}

    /**
     * gets start
     */
    public void setStart(){this.start =start;}

    /** sets start
     *
     * @return
     */
    public String getStart(){return start;}

    /**
     * sets end
     */
    public void setEnd(){this.end = end;}

    /**
     * gets end
     * @return
     */
    public String getEnd(){return end;}

    /**
     * sets user id
     */
    public void setUserID(){this.userID=userID;}

    /**
     * gets user id
     * @return
     */
    public int getUserID(){return userID; }

    /**
     * sets customer id
     */
    public void setCustomerID(){this.customerID=customerID;}

    /**
     * gets customer id
     * @return
     */
    public int getCustomerID(){return customerID;}

    /**
     * sets contact id
     */
    public void setContactID(){this.contactID = contactID;}

    /**
     * gets contact id
     * @return
     */
    public int getContactID(){return contactID;}

}
