// Add your package below
package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
int quantity=0;

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

       EditText name = (EditText) findViewById(R.id.name);
       String value = name.getText().toString();

       CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.checkbox1);
        boolean isWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocoletCheckBox = (CheckBox) findViewById(R.id.checkbox2);
       boolean isChocolet = chocoletCheckBox.isChecked();



        int price= totalPrice(quantity,10,isWhippedCream,isChocolet);
        String msg=createOrderSummery(quantity,price,isWhippedCream,isChocolet,value);


        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,"Just Java Order for "+ value);
        intent.putExtra(Intent.EXTRA_TEXT,msg);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }

    private String createOrderSummery(int quantity,int price,boolean iswhippedCream,boolean isChocolet,String value){
        return "Name : "+ value +"\nquantity:"+quantity+"\nAdd Whipped Cream : "+iswhippedCream
                + "\nAdd Chocolet : "+isChocolet+"\nTotal: "+price+"\nThank you!";
    }


    private int totalPrice(int quantity,int percup,boolean isWhippedCream,boolean isChocolet){
        int basePrice = percup;
        if(isWhippedCream){ basePrice=basePrice+2;}
        if(isChocolet){basePrice = basePrice+2;}

        return basePrice*quantity;
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

/** For increment button*/
    public void increment(View view) {
        if(quantity==100){
            /** Toast for max quantity */
            Toast.makeText(this,"Max Quantity Reached",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity++;
        display(quantity);
    }

    /** For decrement button*/
    public void decrement(View view){
        if (quantity==0) {
            /** Toast for the negative quantity */
            Toast.makeText(this,"Minimum Quantity Reached",Toast.LENGTH_SHORT).show();
           return;
        }
        quantity--;
        display(quantity);

    }
}