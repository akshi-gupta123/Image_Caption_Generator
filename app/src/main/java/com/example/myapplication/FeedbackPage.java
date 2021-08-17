package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FeedbackPage extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_page);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button fed_button = findViewById(R.id.button_feedback);
        EditText review = findViewById(R.id.edittext);


        fed_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                review.setText("");
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}