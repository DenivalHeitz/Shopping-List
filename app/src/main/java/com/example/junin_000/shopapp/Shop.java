package com.example.junin_000.shopapp;

/**
 * Created by junin_000 on 3/25/2016.
 */
import java.util.ArrayList;

public class Shop {

    Item item = new Item();
    public ArrayList<Item> items = new ArrayList<Item>();
    private Double remainder;
    private String strRemainder;
    //public ArrayList<Item> boughtItems = new ArrayList<Item>();

    public String getStrRemainder() {
        return strRemainder;
    }

    public void setStrRemainder(String strRemainder) {
        this.strRemainder = strRemainder;
    }

    public Double getRemainder() {
        return remainder;
    }

    public void setRemainder(Double remainder) {
        this.remainder = remainder;
    }

    public ArrayList<Item> shopping(ArrayList<Item> list, Double aBudget){

        ArrayList<Item> boughtItems = new ArrayList<Item>();

        for(int i=0; i<list.size(); i++)
        {
            if(aBudget >= list.get(i).getPrice() && !(aBudget == 0))
            {
                aBudget = aBudget - (list.get(i).getPrice() * list.get(i).getQuantity());
                if(!(aBudget < 0)){
                    boughtItems.add(i, list.get(i));
                    remainder = aBudget;
                }
            }
        }


        return boughtItems;
    }


}
