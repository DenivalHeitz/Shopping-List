package com.example.junin_000.shopapp;

/**
 * Created by junin_000 on 3/25/2016.
 */
import java.util.ArrayList;

public class SortItems {

    private Item item = new Item();

    public ArrayList<Item> bubbleSort(ArrayList<Item> array){
        int j;
        boolean flag = true;
        Item temp;

        while(flag)
        {
            flag = false;
            for(j=0; j<array.size()-1; j++)
            {
                if(array.get(j).getPriority() > array.get(j+1).getPriority())
                {
                    temp = array.get(j);
                    array.set(j, array.get(j+1));
                    array.set(j+1, temp);
                    flag = true;
                }
            }
        }
        return array;
    }


}
