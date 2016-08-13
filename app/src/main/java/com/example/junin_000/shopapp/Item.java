package com.example.junin_000.shopapp;

import java.util.ArrayList;

/**
 * Created by junin_000 on 3/24/2016.
 */
public class Item implements Print{

    private String name;
    private Integer priority;
    private Double price;
    private Integer quantity;


    public Item(){
        name = "";
        priority = 1;
        price = 0.0;
        quantity = 1;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getPriority() {
        return priority;
    }
    public void setPriority(Integer priority) {
        this.priority = priority;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ArrayList<String> getItemNames(ArrayList<Item> aList){
        ArrayList<String> temp = new ArrayList<String>();

        for(int i=0; i< aList.size(); i++)
        {
            temp.add(i, aList.get(i).getName());
        }

        return temp;
    }

    public String toString(Item anItem){
        String item = "Item Name: " + anItem.getName()+ "\t\t"
                + "Price: $" + String.format("%.2f", anItem.getPrice()) + "\t\t"
//                + "Priority: " + String.valueOf(anItem.getPriority()) + "\t"
                + "Quantity: " + String.valueOf(anItem.getQuantity() + "\t");
        return item;
    }
}

