package com.example.schoolify;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class Registration_For_Students extends AppCompatActivity {
    //Declaration Variables//
    public Button cancel, proceed;
    public EditText username, password1, password2, email, fname, lname;
    public FirebaseAuth firebauth;
    public FirebaseDatabase db;

    @Override
    //Content View//
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration__for__students);

        cancel = (Button) findViewById(R.id.CancelB);
        cancel.setOnClickListener(v -> openLogin());

        //Proceed Button//
        proceed = (Button) findViewById(R.id.ProceedB);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    firebauth=FirebaseAuth.getInstance();
                    String FirstName = fname.getText().toString();
                    String Surname = lname.getText().toString();
                    String Email = email.getText().toString();
                    String Password = password1.getText().toString();
                    String ConfirmedPass = password2.getText().toString();
                    String Username = username.getText().toString();
                    saveToDatabase(FirstName, Surname, Email, Password, ConfirmedPass, Username);
            }

        });

        username = (EditText) findViewById(R.id.Username_input);
        email = (EditText)  findViewById(R.id.Email_input);
        password1 = (EditText) findViewById(R.id.Password_input);
        password2 = (EditText)  findViewById(R.id.ConfirmPassword_input);
        fname = (EditText) findViewById(R.id.First_Name_input);
        lname = (EditText) findViewById(R.id.Surname_input);
if (firebauth.getCurrentUser() != null){
    startActivity(new Intent(getApplicationContext(), Login.class));
    finish();
}
    }

    //Button function//

    //Cancel Button//
    public void openLogin() {
        Intent login = new Intent(this, Login.class);
        startActivity(login);
    }
public void saveToDatabase(String Firstname, String Surname, String Email, String Password, String ConfirmedPass, String Username){
        if (TextUtils.isEmpty(Firstname)) {
        fname.setError("First Name is required!");
        return;
        }
        if (TextUtils.isEmpty(Surname)) {
        lname.setError("Last Name is required!");
        return;
        }
        if (TextUtils.isEmpty(Email)) {
        email.setError("Email is required!");
        return;
        }
        if (TextUtils.isEmpty(Username)) {
        username.setError("Username is required!");
        return;
        }
        if (TextUtils.isEmpty(Password)) {
        password1.setError("Password is required!");
        return;
        }
        if (TextUtils.isEmpty(ConfirmedPass)) {
        password2.setError("Confirm your password!");
        return;
        }
        if (Password != ConfirmedPass) {
        password2.setError("Your password does not match!");
        return;
        }

 firebauth.createUserWithEmailAndPassword(Username, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
     @Override
     public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
         if(task.isSuccessful()) {
             Toast.makeText(Registration_For_Students.this, "User Created", Toast.LENGTH_SHORT).show();
             startActivity(new Intent(getApplicationContext(), Login.class));
         }
     }
 });
        }

}
