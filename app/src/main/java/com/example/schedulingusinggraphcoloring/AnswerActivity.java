package com.example.schedulingusinggraphcoloring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class AnswerActivity extends AppCompatActivity {
    TextView ansview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        ansview = findViewById(R.id.answerview);
        Intent intent= getIntent();
        String res= intent.getStringExtra("answerintent");
        Log.v("res",res);
        ansview.setText(res);

    }
}