package model;

/**
 * user class
 */
public class User {
    private int id;
    private String name;

    /**
     * creates user
     * @param id
     * @param name
     */
    public User(int id, String name){
        this.id = id;
        this.name = name;
    }

    /**
     * sets id
     * @param id
     */
    public void setID(int id){this.id = id;}

    /**
     * gets id
     * @return
     */
    public int getID(){return id;}

    /**
     * sets name
     * @param name
     */
    public void setName(String name){this.name = name;}

    /**
     * gets name
     * @return
     */
    public String getName(){return name;}

}
