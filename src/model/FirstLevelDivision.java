package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * first level division class
 */
public class FirstLevelDivision{
    private int divisionID;
    private int countryID;
    private String name;
    private static ObservableList<FirstLevelDivision> allStates = FXCollections.observableArrayList();

    /**
     * creates firstr level division
     * @param divisionID
     * @param name
     * @param countryID
     */
    public FirstLevelDivision(int divisionID, String name, int countryID){
        this.divisionID = divisionID;
        this.name = name;
        this.countryID = countryID;
    }

    /**
     * returns all states
     * @return
     */
    public static ObservableList<FirstLevelDivision> getAllStates(){return allStates;}

    /**
     * adds a state
     * @param state
     */
    public static void addStates(FirstLevelDivision state){allStates.add(state);}

    /**
     * matches country id to divisionid
     * @param id
     * @return
     */
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
