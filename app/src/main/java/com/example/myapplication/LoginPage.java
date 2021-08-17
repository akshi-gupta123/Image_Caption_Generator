package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class LoginPage extends AppCompatActivity {

    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        EditText username = findViewById(R.id.username1);
        EditText password= findViewById(R.id.Password);
        Button button1 = findViewById(R.id.signup);
        Button button2 = findViewById(R.id.login);
        DB= new DBHelper(this);

        button2.setOnClickListener(new View.OnClickListener() {//login
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass= password.getText().toString();
                if(user.equals("")||pass.equals("")){
                    Toast.makeText(LoginPage.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkuser=DB.checkUserNamePass(user,pass);
                    if(checkuser==true){
                        Toast.makeText(LoginPage.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginPage.this,FirstPage.class));
                    }
                    else{
                        Toast.makeText(LoginPage.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPage.this,RegisterPage.class));
            }
        });


    }

}