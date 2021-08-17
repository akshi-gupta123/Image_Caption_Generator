package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterPage extends AppCompatActivity {
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);

        DB = new DBHelper(this);

        EditText username=findViewById(R.id.username);
        EditText email=findViewById(R.id.email);
        EditText password1=findViewById(R.id.password1);
        EditText password2=findViewById(R.id.password2);
        Button btn = findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//register
                String user = username.getText().toString();
                String emails=email.getText().toString();
                String pass1=password1.getText().toString();
                String pass2= password2.getText().toString();
                if(user.equals("")||emails.equals("")||pass1.equals("")||pass2.equals("")){
                    Toast.makeText(RegisterPage.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(pass1.equals(pass2)){
                        Boolean checkuser=DB.checkUserName(user);
                        if(checkuser==false){
                            Boolean insert=DB.insertData(user,pass1);
                            if(insert==true){
                                Toast.makeText(RegisterPage.this, "Registered  Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterPage.this,FirstPage.class));
                            }
                            else{
                                Toast.makeText(RegisterPage.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(RegisterPage.this, "User already exists! Please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(RegisterPage.this, "Password not matching", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

        Button btn1 = findViewById(R.id.button);//sign in
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterPage.this,LoginPage.class));
            }
        });


    }
}