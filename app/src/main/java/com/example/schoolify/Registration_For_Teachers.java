package com.example.schoolify;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Registration_For_Teachers extends AppCompatActivity {
    //Declaration Variables//
    public Button cancel, proceed;

    @Override
    //Content View//
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration__for__teachers);

        cancel = (Button) findViewById(R.id.CancelB);
        cancel.setOnClickListener(v -> openLogin());
    }

    //Button function//
    public void openLogin() {
        Intent login = new Intent(this, Login.class);
        startActivity(login);
    }
}