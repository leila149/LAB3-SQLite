package bbit3a.com.lab3_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anableila on 10/25/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "TheGamers";

    //table names
    private static final String TABLE_GAMER = "gamer";
    private static final String TABLE_GAMES = "games";

    //columns
    private static final String KEY_GAMER_ID = "gamerID";
    private static final String KEY_USERNAME = "userName";
    private static final String KEY_BELONG_TO = "belongTo";
    private static final String  KEY_CURRENT_GAMES = "currentGames";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_GAMER_TABLE = " CREATE TABLE " + TABLE_GAMER + "(" + KEY_GAMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_USERNAME + " TEXT " + ")";
        db.execSQL(CREATE_GAMER_TABLE);

        String CREATE_TABLE_GAMES = " CREATE TABLE " + TABLE_GAMES + "(" + KEY_GAMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_BELONG_TO + " INTEGER, " + KEY_CURRENT_GAMES + " TEXT " + ")";
        db.execSQL(CREATE_TABLE_GAMES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_GAMER);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_GAMES);

        onCreate(db);

    }

    public void addGamer(Gamers gamer){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, gamer.getUserName());

        //inserting row
        db.insert(TABLE_GAMER, null, values);
        db.close();
    }

    public void addGames(Games games){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_BELONG_TO, games.getBelongTo());
        values.put(KEY_CURRENT_GAMES, games.getCurrentGames());

        //inserting row
        db.insert(TABLE_GAMES, null, values);
        db.close();
    }

    //getting single gamer
    Gamers getGamer(String gamerID){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_GAMER, new String[] { KEY_GAMER_ID, KEY_USERNAME}, KEY_GAMER_ID + " =? ", new String[] { String.valueOf(gamerID) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Gamers gamer = new Gamers(Integer.parseInt(cursor.getString(0)), cursor.getString(1));

        return gamer;
    }
    //getting all gamer
    public List<Gamers> getAllGamers(){
        List<Gamers> gamersList = new ArrayList<Gamers>();
        //select all
        String selectQuery = " SELECT * FROM " + TABLE_GAMER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //adding to list
        if (cursor.moveToFirst()){
            do {
                Gamers gamers = new Gamers(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
                //adding to list
                gamersList.add(gamers);
            } while (cursor.moveToNext());
            }
            return gamersList;
        }

    //updating single gamer
    public int updateGamer(Gamers gamers){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, gamers.getUserName());

        //updating row
        return db.update(TABLE_GAMER, values, KEY_GAMER_ID + " =? ", new String[] { String.valueOf(gamers.getGamerID()) });
    }

    //deleting single gamer
    public void deleteGamer(Gamers gamers){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_GAMER, KEY_GAMER_ID + " =? ", new String[] { String.valueOf(gamers.getGamerID()) });
        db.close();
    }

    //getting gamers count
    public int getGamersCount(){
        String countQuery = " SELECT * FROM " + TABLE_GAMER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    public List<Games> getAllGamesWithGamers( int gameID){
        List<Games> gamesList = new ArrayList<Games>();
        String selectQ = " SELECT * FROM " + TABLE_GAMES + " WHERE " + KEY_GAMER_ID + " = " + KEY_BELONG_TO + ";";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQ, null);

        Cursor cursor2 = db.query(TABLE_GAMES, new String[] { KEY_GAMER_ID, KEY_BELONG_TO, KEY_USERNAME }, KEY_BELONG_TO + " =? " , new String[] { String.valueOf(gameID) }, null, null, null, null);
        if (cursor.moveToFirst()){
            do {
                Games games = new Games(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
                gamesList.add(games);
            }while (cursor.moveToNext());
        }
        return gamesList;
    }

    public List<Games> getAllGames(){
        List<Games> gamesList = new ArrayList<Games>();
        String selectQ = " SELECT * FROM " + TABLE_GAMES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQ, null);

        if (cursor.moveToFirst()){
            do{
                Games games = new Games(Integer.parseInt(cursor.getString(0)),cursor.getString(1), cursor.getString(2));
                gamesList.add(games);
            }while (cursor.moveToNext());
        }
        return gamesList;
    }
}

