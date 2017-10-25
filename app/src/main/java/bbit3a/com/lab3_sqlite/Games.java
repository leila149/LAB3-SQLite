package bbit3a.com.lab3_sqlite;

/**
 * Created by anableila on 10/25/17.
 */

public class Games {
    int gameID;
    String currentGames;
    String belongTo;

    //constructors
    public Games(int gamerID, String halo){

    }

    public Games(int gameID, String currentGames, String belongTo){
        this.gameID = gameID;
        this.currentGames = currentGames;
        this.belongTo = belongTo;
    }

    public Games(String currentGames, String belongTo){
        this.currentGames = currentGames;
        this.belongTo = belongTo;
    }

    //getters

    public int getGameID() {
        return gameID;
    }

    public String getCurrentGames() {
        return this.currentGames;
    }

    public String getBelongTo() {
        return this.belongTo;
    }

    //setter

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public void setCurrentGames(String currentGames) {
        this.currentGames = currentGames;
    }

    public void setBelongTo(String belongTo) {
        this.belongTo = belongTo;
    }
}
