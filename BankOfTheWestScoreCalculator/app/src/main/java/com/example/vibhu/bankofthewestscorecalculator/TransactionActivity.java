package com.example.vibhu.bankofthewestscorecalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class TransactionActivity extends AppCompatActivity {

    private String expense_type = "Miscellaneous";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        ListView transaction = (ListView) findViewById(R.id.transaction_list);
        final Intent intent = new Intent(this, MenuActivity.class);
        int n =12;
        ArrayList<String> aList =
                new ArrayList<String>(n);

        aList.add("'Microsoft Salary - Aug','Aug-10','7000','Income'");
        aList.add("'Vertex Apartments','Aug-11','1200','Rent'");
        aList.add("'Cox Internet','Aug-11','120','Utilities'");
        aList.add("'APS','Aug-13','250','Utilities'");
        aList.add("'Walmart','Aug-17','120','Essentials'");
        aList.add("'Delhi Palace','Aug-17','25','Miscellaneous'");
        aList.add("'Walmart','Aug-24','60','Essentials'");
        aList.add("'Great Clips','Aug-25','20','Health'");
        aList.add("'AT&T','Aug-25','35','Utilities'");
        aList.add("'Delhi Palace','Aug-25','32','Miscellaneous'");
        aList.add("'AMC','Aug-29','45','Miscellaneous'");
        aList.add("'Subway','Aug-29','15','Miscellaneous'");
        aList.add("'Apple Store','Aug-31','1083','Miscellaneous'");


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
//                = null;
            } else {
                expense_type = extras.getString("expense");
                if(expense_type!=null)
                aList.add("'Target','Oct-02','267.01',"+expense_type);
            }
        } else {
            expense_type= (String) savedInstanceState.getSerializable("expense");
            if(expense_type!=null)
                aList.add("'Target','Oct-02','267.01',"+expense_type);
        }




        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                aList );

        transaction.setAdapter(arrayAdapter);

    }
}
