package com.example.productcatalog;

public class Candy
{
    private int id;
    private String name;
    private double price;

    public Candy( int newId, String newName, double newPrice )
    {
        setId( newId );
        setName( newName );
        setPrice( newPrice );
    }//end constructor

    //Getters
    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public double getPrice()
    {
        return price;
    }


    //Setters
    public void setId( int newId )
    {
        id = newId;
    }

    public void setName( String newName )
    {
        name = newName;
    }

    public void setPrice( double newPrice )
    {
        price = 0.00;
        if( newPrice >= 0.00 )
            price = newPrice;
    }


    //Method to make displaying a Candy object easier
    public String candyToString()
    {
        return id + " " + name + " " + price;
    }
}//end of Candy class
