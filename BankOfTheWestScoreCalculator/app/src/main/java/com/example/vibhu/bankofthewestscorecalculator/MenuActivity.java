package com.example.vibhu.bankofthewestscorecalculator;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class MenuActivity extends AppCompatActivity {
    private String amountValue = "1079";
    private String expense_type = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        Button upload_receipt = (Button) findViewById(R.id.upload_receipt);
        Button view_score= (Button) findViewById(R.id.view_scores);
        Button transaction = (Button) findViewById(R.id.see_transactions);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
//                amountValue= null;
            } else {
                amountValue = extras.getString("amount");
                expense_type = extras.getString("expense_type");
            }
        } else {
            amountValue= (String) savedInstanceState.getSerializable("amount");
            expense_type= (String) savedInstanceState.getSerializable("expense_type");
        }

        System.out.print("an =  "+amountValue);
        final Intent intent = new Intent(this, CameraActivity.class);
        final Intent score_intent = new Intent(this, ScoreDisplayActivity.class);
        final Intent transaction_intent = new Intent(this, TransactionActivity.class);

        upload_receipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        view_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score_intent.putExtra("score", amountValue);
                startActivity(score_intent);
            }
        });

        transaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction_intent.putExtra("expense", expense_type);
                startActivity(transaction_intent);
            }
        });

    }



}
