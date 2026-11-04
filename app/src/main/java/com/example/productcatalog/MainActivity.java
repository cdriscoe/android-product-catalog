package com.example.productcatalog;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{

    private DatabaseManager manager;
    private double total;
    private ScrollView scrollView;
    private int buttonWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //create the database
        manager = new DatabaseManager(this);

        //Initialize the total to zero
        total = 0.0;

        //Connect the scrollview to the screen
        scrollView = findViewById(R.id.contentLayout);

        //Calculate the width of the button
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);

        buttonWidth = size.x / 2;

        updateView();

    }//end onCreate

    @Override
    protected void onResume()
    {
        super.onResume();
        updateView();
    }//end onResume

    public void updateView()
    {
        ArrayList<Candy> candies = manager.selectAll();

        if(candies.size() > 0)
        {
            //clear the scrollView
            scrollView.removeAllViewsInLayout();

            //gridLayout for the buttons
            GridLayout gridLayout = new GridLayout(this);
            gridLayout.setRowCount( (candies.size() + 1) / 2 );
            gridLayout.setColumnCount(2);

            //array of buttons, 2 per row
            CandyButton [] buttons = new CandyButton[candies.size()];

            ButtonHandler handler = new ButtonHandler();

            //fill the gridlayout
            int sub = 0;
            for (Candy candy : candies)
            {
                //create the button
                buttons[sub] = new CandyButton(this, candy);
                buttons[sub].setText(candy.getName()+ "\n" + candy.getPrice());

                //handle the buttonClick
                buttons[sub].setOnClickListener(handler);

                //add the buttons to the gridlayout
                gridLayout.addView(buttons[sub], buttonWidth, GridLayout.LayoutParams.WRAP_CONTENT);

                sub++;

            }//end for

            //add the grid to the scrollView
            scrollView.addView(gridLayout);

        }//end if
    }//end updateView

    private class ButtonHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            //increment the total by the price of the selected button
            total += ((CandyButton) v).getPrice();

            String paymentAmount = NumberFormat.getCurrencyInstance().format(total);
            Toast.makeText(MainActivity.this, "Total: " + paymentAmount, Toast.LENGTH_LONG).show();

        }//end onClick
    }//end buttonhanlder

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }//onCreateOptionsMenu end

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add)
        {
            Intent insertIntent = new Intent(MainActivity.this, InsertActivity.class);
            startActivity(insertIntent);
            //Toast.makeText(this, "Menu Option: add selected", Toast.LENGTH_SHORT).show();
            return true;
        }

        else if (id == R.id.action_delete)
        {
            //Toast.makeText(this, "Menu Option: delete selected", Toast.LENGTH_SHORT).show();
            Intent deleteIntent = new Intent(MainActivity.this, DeleteActivity.class);
            startActivity(deleteIntent);

            return true;
        }

        else if (id == R.id.action_update)
        {

            Intent updateIntent = new Intent(MainActivity.this, UpdateActivity.class);
            startActivity(updateIntent);
            //Toast.makeText(this, "Menu Option: update selected", Toast.LENGTH_SHORT).show();
            return true;
        }

        else if (id == R.id.action_reset)
        {
            Toast.makeText(this, "Menu Option: Reset selected", Toast.LENGTH_SHORT).show();
            total = 0.0;
        }

        return super.onOptionsItemSelected(item);
    }//end onOptionsItemSelected


}//end MainActivity
