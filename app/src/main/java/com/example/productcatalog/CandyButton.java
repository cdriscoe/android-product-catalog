package com.example.productcatalog;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;

public class CandyButton extends AppCompatButton
{
    private Candy candy;

    public CandyButton(Context context, Candy newCandy)
    {
        super(context);
        candy = newCandy;
    }//end CandyButton() constructor

    public double getPrice()
    {
        return candy.getPrice();
    }//end getPrice

}//end CandyButton class
