package com.unj.collegenoticeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {

    Button AdminLogin;
    String email = "xyz@gmail.com";
    String password = "1234567";
    EditText login_email, login_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);


        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);

        AdminLogin = findViewById(R.id.btn_login_admin);
        AdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String UEmail = login_email.getText().toString();
                String UPassword = login_password.getText().toString();

                if (UEmail.equals(email) && UPassword.equals(password)) {
                    Toast.makeText(AdminLogin.this, " Successfully Logged In", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(AdminLogin.this , AdminHome.class);
                    startActivity(i);
                    finish();

                } else {
                    Toast.makeText(AdminLogin.this, "Login failed", Toast.LENGTH_SHORT).show();
                }



            }
        });

    }
}