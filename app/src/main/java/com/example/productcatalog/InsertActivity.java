package com.example.productcatalog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class InsertActivity extends AppCompatActivity
{

    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        databaseManager = new DatabaseManager(this);

    }//end onCreate

    public void insertCandy(View view)
    {
        //get the info from the editTexts
        EditText candyNameET = findViewById(R.id.nameEditText);
        EditText priceET = findViewById(R.id.priceEditText);

        String nameStr = candyNameET.getText().toString();
        String priceStr = priceET.getText().toString();

        try
        {
            double price = Double.parseDouble(priceStr);

            //create a candy object to be inserted into the database
            Candy candy = new Candy(0, nameStr, price);

            //insert the Candy object into the database
            databaseManager.insert(candy);

            Toast.makeText(this, "Candy: " + nameStr + " added to DB", Toast.LENGTH_SHORT).show();

        }//end try
        catch (NumberFormatException nfe)
        {
            Toast.makeText(this, "Price error", Toast.LENGTH_SHORT).show();
        }//end catch

        ArrayList<Candy> candies = databaseManager.selectAll();
        for(Candy candy : candies)
        {
            Toast.makeText(this, "The candy is " + candy.candyToString(), Toast.LENGTH_SHORT).show();
        }

        //Clear the editText fields
        candyNameET.setText("");
        priceET.setText("");

    }//end insertCandy

    public void goBack(View view)
    {
        finish();
    }//end goBack

}//end InsertActivity
