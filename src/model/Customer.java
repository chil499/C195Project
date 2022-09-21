package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;

public class Customer {
    private int id;
    private String name;
    private String address;
    private String zip;
    private String phone;
    private int divisionID;

    private static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

    public Customer(int id, String name, String address, String zip, String phone, int divisionID) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.zip = zip;
        this.phone = phone;
        this.divisionID = divisionID;
    }

    public static ObservableList<Customer> getAllCustomers(){return allCustomers;}
    public static void addCustomer(Customer customer){allCustomers.add(customer);}

    public void setID(){this.id = id;}
    public int getID(){return id;}

    public void setName(){this.name = name;}
    public String getName(){return name;}

    public void setAddress(){this.address = address;}
    public String getAddress(){return address;}

    public void setZip(){this.zip = zip;}
    public String getZip(){return zip;}

    public void setPhone(){this.phone = phone;}
    public String getPhone(){return phone;}
    
    public void setDivisionID(){this.divisionID = divisionID;}
    public int getDivisionID(){return divisionID;}


}
