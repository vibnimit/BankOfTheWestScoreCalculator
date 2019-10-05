package com.example.vibhu.bankofthewestscorecalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ScoreDisplayActivity extends AppCompatActivity {

    private String score_value = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_score);
        TextView score = (TextView)findViewById(R.id.score);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                score_value= null;
            } else {
                score_value= extras.getString("score");
            }
        } else {
            score_value= (String) savedInstanceState.getSerializable("amount");
        }
        System.out.print("ss = "+score_value);
        score.setText(score_value);
    }
}
