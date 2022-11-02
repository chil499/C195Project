package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * customer class
 */
public class Customer {
    private int id;
    private String name;
    private String address;
    private String zip;
    private String phone;
    private int divisionID;
    private String country;
    private String state;
    private static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

    /**
     * creates customer objects
     * @param id
     * @param name
     * @param address
     * @param zip
     * @param phone
     * @param divisionID
     * @param country
     * @param state
     */
    public Customer(int id, String name, String address, String zip, String phone, int divisionID,String country, String state) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.zip = zip;
        this.phone = phone;
        this.divisionID = divisionID;
        this.country= country;
        this.state = state;
    }

    /**
     * returns all customer objects
     * @return
     */
    public static ObservableList<Customer> getAllCustomers(){return allCustomers;}

    /**
     * adds customer
     * @param customer
     */
    public static void addCustomer(Customer customer){allCustomers.add(customer);}

    /**
     * sets id
     */
    public void setID(){this.id = id;}

    /**
     * gets id
     * @return
     */
    public int getID(){return id;}

    /**
     * sets name
     */
    public void setName(){this.name = name;}

    /**
     * gets name
     * @return
     */
    public String getName(){return name;}

    /**
     * sets address
     */
    public void setAddress(){this.address = address;}

    /**
     * gets address
     * @return
     */
    public String getAddress(){return address;}

    /**
     * sets zip
     */
    public void setZip(){this.zip = zip;}

    /**
     * gets zip
     * @return
     */
    public String getZip(){return zip;}

    /**
     * sets phone
     */
    public void setPhone(){this.phone = phone;}

    /**
     * gets phone
     * @return
     */
    public String getPhone(){return phone;}

    /**
     * sets divsions id
     *
     */
    public void setDivisionID(){this.divisionID = divisionID;}

    /** gets division id
     *
     * @return
     */
    public int getDivisionID(){return divisionID;}

    /**
     * sets state
     */
    public void setState(){this.state = state;}

    /**
     *  gets state
     * @return
     */
    public String getState(){return state;}

    /**
     * sets country
     */
    public void setCountry(){this.country = country;}

    /**
     * gets country
     * @return
     */
    /**
     * returns country string
     * @return
     */
    public String getCountry(){return country;}

    /**
     * updates customer
     * @param index
     * @param newCustomer
     */
    public static void updateCustomer(int index,Customer newCustomer){allCustomers.set(index,newCustomer);}

    /**
     * deletes customer
     * @param selectedCustomer
     * @return
     */
    public static boolean deleteCustomer(Customer selectedCustomer){return allCustomers.remove(selectedCustomer);}
    @Override
    public String toString(){
        return(name);
   }
}
