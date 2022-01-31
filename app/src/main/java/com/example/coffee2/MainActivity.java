package com.example.coffee2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private int pay = 0;
    private int addCream = 0;
    private int addCko = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void plus(View view) {
        pay += 1;
        display(pay);

    }
    public void decrement(View view) {
        pay -= 1;
        display(pay);
    }
    public void totalPay(View view) {
        //
        CheckBox WhippedCream = findViewById(R.id.whipped_cream);
        boolean flag = WhippedCream.isChecked();
        if(flag == true){
            addCream += 1;
        }else{
            addCream = 0;
        }
        //
        CheckBox Chocolate = findViewById(R.id.chocolate);
        boolean chokFlag = Chocolate.isChecked();
        if(chokFlag==true) {
            addCko += 2;
        }else{
            addCko = 0;
        }
        //
        EditText name =  findViewById(R.id.name);
        String strName = name.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto"));

        intent.putExtra(Intent.EXTRA_SUBJECT, "order for " + name);

        intent.putExtra(Intent.EXTRA_TEXT, calculatorPrice());

        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }

        int price = calculatorPrice();
        dispayMessage(greatOrderSummer(price + addCream + addCko, flag, chokFlag, strName));

    }

    private int calculatorPrice() {
        return pay * 5;

    }

    private void dispayMessage(String message) {
        TextView orderSummerTextView =  findViewById(R.id.order_summary_text_view);
        orderSummerTextView.setText(message);

    }

    private void display(int orderPay) {
        TextView payTextView =  findViewById(R.id.zero_quantity);
        payTextView.setText(" "+ orderPay);
    }

    /**
     * Создаем сводку заказа
     * @param price получаем общую цену за чашку кофе в текстовом значении
     * @return получаем в текстовом значении
     */

    private String greatOrderSummer(int price, boolean addCream, boolean chokolate, String strName) {
        String priceMessage = "\nYou'e name "+strName;
        priceMessage += "\nAdd WhippedCrem? " + addCream;
        priceMessage += "\nAdd Chokolate? " + chokolate;
        priceMessage += "\nQuantity " + pay;
        priceMessage += "\nTotal: " + price;
        priceMessage += "\nThank you";
        return priceMessage;

    }

}






