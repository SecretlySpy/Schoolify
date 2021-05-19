package com.example.schoolify;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Registration_For_Students extends AppCompatActivity {
    //Declaration Variables//
    public Button cancel, proceed;

    @Override
    //Content View//
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration__for__students);

        cancel = (Button) findViewById(R.id.CancelB);
        cancel.setOnClickListener(v -> openLogin());

        proceed = (Button) findViewById(R.id.ProceedB);
        proceed.setOnClickListener(v -> openDashboard());
    }

    //Button function//

    //Cancel Button//
    public void openLogin() {
        Intent login = new Intent(this, Login.class);
        startActivity(login);
    }

    //Proceed Button//
    public void openDashboard() {


        Intent dashboardS = new Intent(this, Dashboard_Student.class);
        startActivity(dashboardS);
    }
}