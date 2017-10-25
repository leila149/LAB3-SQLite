package bbit3a.com.lab3_sqlite;

/**
 * Created by anableila on 10/25/17.
 */

public class Gamers {
    int gamerID;
    String userName;

    //constructors
    public Gamers(){

    }

    public Gamers(int gamerID, String userName){
        this.gamerID = gamerID;
        this.userName = userName;

    }

    public Gamers(String userName){
        this.userName = userName;

    }

    //getters
    public int getGamerID() {

        return this.gamerID;
    }

    public String getUserName() {
        return this.userName;
    }


    //setters
    public void setGamerID(int gamerID) {
        this.gamerID = gamerID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
