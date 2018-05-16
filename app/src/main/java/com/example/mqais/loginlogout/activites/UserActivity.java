package com.example.mqais.loginlogout.activites;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.mqais.loginlogout.R;

/**
 * Created by MQais on 20/11/2017.
 */

public class UserActivity extends AppCompatActivity{

    private TextView textViewName;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textViewName = (TextView) findViewById(R.id.textViewName);
        String nameFromIntent = getIntent().getStringExtra("EMAIL");
        textViewName.setText("Welcome " +nameFromIntent);

    }
}
