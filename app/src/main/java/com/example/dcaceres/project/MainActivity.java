package com.example.dcaceres.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity {

    EditText etEmail;
    EditText etPassword;
    Button btLogin;
    Button btSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = (EditText)findViewById(R.id.mainEt_email);
        etPassword = (EditText)findViewById(R.id.mainEt_password);
        btLogin = (Button)findViewById(R.id.mainBt_login);
        btSignin = (Button)findViewById(R.id.mainBt_sigin);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Gson gson = new Gson();

                OkHttpClient cliente = new OkHttpClient();
                //RequestBody jsonBody = new FormBody.Builder().add()

                //ir a otra activity
                //Intent toUserActivity = new Intent(MainActivity.this, UserActivity.class);
                //toUserActivity.putExtra("id", )
                //startActivity(toUserActivity);
            }
        });

        btSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
