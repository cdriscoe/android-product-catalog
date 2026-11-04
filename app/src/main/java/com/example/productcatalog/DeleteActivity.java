package com.example.productcatalog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

public class DeleteActivity extends AppCompatActivity
{

    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        databaseManager = new DatabaseManager(this);
        updateView();

    }//end oncreate

    public void updateView()
    {

        ArrayList<Candy> candies = databaseManager.selectAll();

        //Create layout
        RelativeLayout layout = new RelativeLayout(this);
        ScrollView scrollView = new ScrollView(this);
        RadioGroup radioGroup = new RadioGroup(this);

        //Loop to process each candy in the arraylist
        for(Candy candy : candies)
        {
            //create a radiobutton to be placed into the radiogroup
            RadioButton radioButton = new RadioButton(this);

            //put the info in the radiobutton
            radioButton.setId(candy.getId());
            radioButton.setText(candy.candyToString());

            //add the radiobutton to the radiogroup
            radioGroup.addView(radioButton);
        }//end for

        //Set up the handler for the radioButtons in the radioGroup
        RadioButtonHandler handler = new RadioButtonHandler();
        radioGroup.setOnCheckedChangeListener(handler);

        //Create a back button
        Button backBtn = new Button(this);
        backBtn.setText("Back");
        backBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });

        //Add radiogroup to the scrollview
        scrollView.addView(radioGroup);

        //add the scrollview to the relative layout
        layout.addView(scrollView);

        //Set up some parameters to make the back button display at the bottom of the screen
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.setMargins(0,0,0,50);

        //add the back button to the layout using the parameters
        layout.addView(backBtn, params);

        //display the relativeLayout
        setContentView(layout);

    }//end updateView

    private class RadioButtonHandler implements RadioGroup.OnCheckedChangeListener
    {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId)
        {
            databaseManager.deleteByID(checkedId);

            Toast.makeText(DeleteActivity.this, "Candy deleted", Toast.LENGTH_SHORT).show();

            updateView();

        }//end onCheckedChanged
    }//end radioButtonHandler

}//end DeleteActivity
