package com.example.junin_000.shopapp;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    Button btnAddItem;
    Button btnShop;
    Button btnSave;
//    Button btnViewItems;
    EditText txtItemName;
    EditText txtPriority;
    EditText txtPrice;
    EditText txtQty;
    EditText textArea;
    EditText txtBudget;
    EditText txtSaveList;

    Shop shop = new Shop();
    Item item = new Item();
    SortItems sort = new SortItems();

    private ArrayList<String> myList = item.getItemNames(shop.items);

    public void addItem(View v){
        textArea.setText(R.string.empty_text);
        String strItemName = txtItemName.getText().toString();
        String strPriority = txtPriority.getText().toString();
        String strPrice = txtPrice.getText().toString();
        String strQty = txtQty.getText().toString();

        Integer quantity;
        Double price;
        Integer priority;
        Item tempItem;

        if((strPrice.matches("[0-9]{1,13}(\\\\.[0-9]*)?")) && (strPriority.matches("\\d+")) && (strQty.matches("\\d+")))
        {
            quantity = Integer.parseInt(strQty);
            price = Double.parseDouble(strPrice);
            priority = Integer.parseInt(strPriority);
            tempItem = new Item();
            tempItem.setName(strItemName);
            tempItem.setPrice(price);
            tempItem.setPriority(priority);
            tempItem.setQuantity(quantity);

            shop.items.add(tempItem);
            sort.bubbleSort(shop.items);

            for(int i=0; i<shop.items.size(); i++)
            {
                Item temp = shop.items.get(i);
                textArea.append(item.toString(temp) + "\n");
            }
        }
        else
        {
            textArea.setText("*****Item input is incorrect*****");
        }

        txtItemName.setText(R.string.empty_text);
        txtPriority.setText(R.string.empty_text);
        txtPrice.setText(R.string.empty_text);
        txtQty.setText(R.string.empty_text);

    }

    public void startShop(View v){

        Double budget = Double.parseDouble(txtBudget.getText().toString());
        ArrayList<Item> boughtItems = shop.shopping(shop.items, budget);
        DecimalFormat df = new DecimalFormat("#.00");

        textArea.append("\n***********Items Bought*************" + "\n");

        for(int i=0; i<boughtItems.size(); i++)
        {
            Item temp = boughtItems.get(i);
            textArea.append(item.toString(temp) + "\n");
        }

        txtBudget.setText("");
        textArea.append("Remaining Budget: $" + df.format(shop.getRemainder()));
    }

    public void saveList(View v){
        String filename = txtSaveList.getText().toString();
        


        FileOutputStream outputStream;

        try{
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(textArea.getText().toString().getBytes());
            outputStream.close();
        } catch(Exception e){
            e.printStackTrace();
        }

        txtSaveList.setText("");
    }

    public void scanBarCode(View v){
        if(v.getId()==R.id.btnScan){
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }

    public void onActivityResult(int requestCode, int resultCode,Intent intent){
        textArea.setText("");
        txtItemName.setText("");
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if(scanningResult != null){
            String scanFormat = scanningResult.getFormatName();
            String scanContent = scanningResult.getContents();
            txtItemName.setText(scanContent);
            textArea.setText("Format: " + scanFormat + "\n" + "Content: " + scanContent);

        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

//    public void viewItems(View v){
//        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
//        intent.putExtra("Item_names", myList);
//        startActivity(intent);
//
//        //System.out.println(item.getItemNames(shop.items).toString());
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAddItem = (Button)findViewById(R.id.btnAddItem);
        btnShop = (Button)findViewById(R.id.btnShop);
        btnSave = (Button)findViewById(R.id.btnSave);
        txtItemName = (EditText)findViewById(R.id.txtItemName);
        textArea = (EditText)findViewById(R.id.txtTextArea);
        txtPriority = (EditText)findViewById(R.id.txtPriority);
        txtQty = (EditText)findViewById(R.id.txtQty);
        txtPrice = (EditText)findViewById(R.id.txtPrice);
        txtBudget = (EditText)findViewById(R.id.txtBudget);
        txtSaveList = (EditText)findViewById(R.id.txtSaveList);
//        btnViewItems = (Button)findViewById(R.id.btnViewItems);
    }
}
