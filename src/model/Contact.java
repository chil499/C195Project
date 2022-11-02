package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * contact class
 */
public class Contact {
    private int id;
    private String name;
    private String email;
    private static ObservableList<Contact> allContacts = FXCollections.observableArrayList();

    /**
     * creates contact
     * @param id
     * @param name
     * @param email
     */
    public Contact(int id, String name,String email){
        this.id = id;
        this.name = name;
        this.email =email;
    }

    /**
     * returns all contacts
     * @return
     */
    public static ObservableList<Contact> getAllContacts(){return allContacts;}

    /**
     * adds contacts
     * @param contact
     */
    public static void addContact(Contact contact){allContacts.add(contact);}

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
     * sets email
     */
    public void setEmail(){this.email = email;}

    /**
     * gets email
     * @return
     */
    public String getEmail(){return email;}


    @Override
    public String toString(){
        return(name);
    }
}

