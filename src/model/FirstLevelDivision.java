package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FirstLevelDivision{
    private int divisionID;
    private int countryID;
    private String name;
    private static ObservableList<FirstLevelDivision> allStates = FXCollections.observableArrayList();

    public FirstLevelDivision(int divisionID, String name, int countryID){
        this.divisionID = divisionID;
        this.name = name;
        this.countryID = countryID;
    }

    public static ObservableList<FirstLevelDivision> getAllStates(){return allStates;}
    public static void addStates(FirstLevelDivision state){allStates.add(state);}

    public static ObservableList matchCountryId(int id) {
        ObservableList<FirstLevelDivision> matchedState = FXCollections.observableArrayList();

        for(int i = 0; i<allStates.size();i++){
            FirstLevelDivision state = allStates.get(i);
            if(state.getCountryID()==id){
                matchedState.add(state);
            }
        }
        return matchedState;
    }

    public void setDivisionID(){this.divisionID = divisionID;}
    public int getDivisionID(){return divisionID;}


    public void setName(){this.name = name;}
    public String getName(){return name;}

    public void setCountryID(){this.countryID = countryID;}
    public int getCountryID(){return countryID;}


    @Override
    public String toString(){
        return(name);
    }
}
