package com.example.schoolify;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.schoolify.register.StudentRegister;


public class Login extends AppCompatActivity {
//Declaration Variables//
    public Button Reg_Stud, Reg_Teach;

    @Override
    //Content View//
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Reg_Stud = (Button) findViewById(R.id.ForStudents);
        Reg_Stud.setOnClickListener(v -> openRegistration_For_Students());

        Reg_Teach = (Button) findViewById(R.id.ForTeachers);
        Reg_Teach.setOnClickListener(v -> openRegistration_For_Teachers());
    }
//Button function//

    //Registration for Students//
public void openRegistration_For_Students() {
    Intent regstud = new Intent(this, StudentRegister.class);
    startActivity(regstud);
}
//Registration for Teachers//
public void openRegistration_For_Teachers() {
        Intent regteach = new Intent(this, Registration_For_Teachers.class);
    startActivity(regteach);
}
}