package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Contact {
    private int id;
    private String name;
    private String email;
    private static ObservableList<Contact> allContacts = FXCollections.observableArrayList();

    public Contact(int id, String name,String email){
        this.id = id;
        this.name = name;
        this.email =email;
    }
    public static ObservableList<Contact> getAllContacts(){return allContacts;}
    public static void addContact(Contact contact){allContacts.add(contact);}

    public void setID(){this.id = id;}
    public int getID(){return id;}

    public void setName(){this.name = name;}
    public String getName(){return name;}

    public void setEmail(){this.email = email;}
    public String getEmail(){return email;}


    @Override
    public String toString(){
        return(name);
    }
}

