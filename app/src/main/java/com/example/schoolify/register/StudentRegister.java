package com.example.schoolify.register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.schoolify.R;
import com.example.schoolify.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;


public class StudentRegister extends AppCompatActivity{

    private static final String TAG = "StudentRegister";
    private TextInputEditText mFullname, mUsername, mEmailAddress,
            mPassword;
    private FirebaseFirestore mDb;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);

        mFullname =  findViewById(R.id.input_fullname);
        mUsername =  findViewById(R.id.input_username);
        mEmailAddress =  findViewById(R.id.input_emailadd);
        mPassword =  findViewById(R.id.input_password1);

        findViewById(R.id.reg_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
        findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mDb = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();



    }

    private void register() {

        String fullname = mFullname.getText().toString().trim();
        String username = mUsername.getText().toString().trim();
        String email = mEmailAddress.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        if(fullname.isEmpty()){
            mFullname.setError("Fullname is required");
            mFullname.requestFocus();
            return;
        }

        if(username.isEmpty()){
            mUsername.setError("Username is required");
            mUsername.requestFocus();
            return;
        }

        if(email.isEmpty()){
            mEmailAddress.setError("Email Address is required");
            mEmailAddress.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmailAddress.setError("Please provide valid email address");
            mEmailAddress.requestFocus();
            return;
        }

        if(password.isEmpty()){
            mPassword.setError("Password is required");
            mPassword.requestFocus();
            return;
        }

        if(password.length()<6){
            mPassword.setError("Min password length should be 6 characters!");
            mPassword.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(fullname,username,email,password);
                            FirebaseDatabase.getInstance().getReference("Students")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(StudentRegister.this, "Register Successfully", Toast.LENGTH_LONG).show();

                                    } else{
                                        Toast.makeText(StudentRegister.this, "Register Failed, Try Again", Toast.LENGTH_LONG).show();

                                    }

                                }
                            });
                        } else {
                            Toast.makeText(StudentRegister.this, "Register Failed, Try Again", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }



}