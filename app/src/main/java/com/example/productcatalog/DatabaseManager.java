package com.example.productcatalog;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "candyDB";
    private static final String TABLE_NAME = "candyTable";
    private static final String ID = "id",
                                NAME = "name",
                                PRICE = "price";

    private static final int DATABASE_VERSION = 1;

    public DatabaseManager(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }//end constructor

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        //create a string to hold the SQL statement to create the DataBase
        String sqlCreate = "create table " + TABLE_NAME
                            + "( " + ID + " integer primary key autoincrement, "
                            + NAME + " text, " + PRICE + " real )";

        //create the database
        db.execSQL(sqlCreate);
    }//end oncreate

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        //String that drops the old table if it exists
        String sqlDrop = "drop table if exists " + TABLE_NAME;

        //drop the old table
        db.execSQL(sqlDrop);

        //Re-Create the new version of the table
        onCreate(db);

    }//end onUpgrade

    public void insert( Candy candy )
    {
        //Get the database
        SQLiteDatabase database = getWritableDatabase();

        //Create a string object that contains the SQL statement for insert
        String sqlInsert = "insert into " + TABLE_NAME
                        + " values( null, '" + candy.getName() + "', "
                        + "'" + candy.getPrice() + "' )";

        //insert the data into the databse
        database.execSQL(sqlInsert);

        //close the database
        database.close();

    }//end insert


    public ArrayList<Candy> selectAll()
    {
        //create a string that containst he SQL statement
        String sqlQuery = "select * from " + TABLE_NAME;

        //get the database with the information
        SQLiteDatabase database = getWritableDatabase();

        //Create the cursor with all of the database information
        Cursor cursor = database.rawQuery(sqlQuery, null);

        //Create the arrayList that will eventually be returned from the method
        ArrayList<Candy> candies = new ArrayList<>();

        //loop that will transfer the information from the cursor to the arrayList
        while(cursor.moveToNext())
        {
            Candy currentCandy = new Candy( Integer.parseInt(cursor.getString(0)),
                                        cursor.getString(1),
                                        cursor.getDouble(2));

            //put the candy object into the array
            candies.add(currentCandy);
        }//end while

        //close the database
        database.close();

        //return the arraylist
        return candies;

    }//end selectAll

    public void deleteByID(int id)
    {
        //create a string with the SQL statement
        String sqlDelete = "delete from " + TABLE_NAME + " where " + ID + " = " + id;

        //get the databse
        SQLiteDatabase database = getWritableDatabase();

        //delete the candy from the database
        database.execSQL(sqlDelete);

        //close the database
        database.close();


    }//end deleteByID

    public void updateById(int currentId, String newName, double newPrice)
    {
        //get the database
        SQLiteDatabase database = getWritableDatabase();

        //String with the sql command
        String sqlUpdate = "update " + TABLE_NAME + " set " + NAME + " = '" + newName + "', "
                            + PRICE + " = '" + newPrice + "'" + " where " + ID + " = " + currentId;

        //update the database
        database.execSQL(sqlUpdate);

        database.close();

    }//end updateById

    public Candy selectById( int currentId )
    {
        //Get the database
        SQLiteDatabase database = getWritableDatabase();

        //SQL command string
        String sqlQuery = " select * from " + TABLE_NAME + " where " + ID + " = " + currentId;

        //create a cursor to hold the retreived information
        Cursor cursor = database.rawQuery(sqlQuery, null);

        //Create the candy object containing the information
        Candy candy = null;
        if (cursor.moveToFirst())
        {
            candy = new Candy(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getDouble(2));
        }

        return candy;

    }//end selectById


}//end DatabaseManager
