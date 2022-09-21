package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Country {
    private int id;
    private String name;
    private static ObservableList<Country> allCountries = FXCollections.observableArrayList();

    public Country(int id, String name){
        this.id = id;
        this.name = name;
    }
    public static ObservableList<Country> getAllCountries(){return allCountries;}
    public static void addCountries(Country country){allCountries.add(country);}

    public void setID(){this.id = id;}
    public int getID(){return id;}

    public void setName(){this.name = name;}
    public String getName(){return name;}


    @Override
    public String toString(){
        return(name);
    }
}
