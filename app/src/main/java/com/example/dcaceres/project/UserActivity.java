package com.example.dcaceres.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Log.i("User", "onCreate User");


    }
}
