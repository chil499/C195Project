package model;

public class User {
    private int id;
    private String name;


    public User(int id, String name){
        this.id = id;
        this.name = name;
    }

    public void setID(int id){this.id = id;}
    public int getID(){return id;}

    public void setName(String name){this.name = name;}
    public String getName(){return name;}

}
