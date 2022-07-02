package com.example.login;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Category extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /* TODO Auto-generated method stub */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);
    }
    public void switchScreen(View view){
        Intent intent = new Intent(Category.this,EmiCalculator.class);
        startActivity(intent);
    }
}