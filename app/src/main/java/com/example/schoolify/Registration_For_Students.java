package com.example.schoolify;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

import static android.text.TextUtils.isEmpty;


public class Registration_For_Students extends AppCompatActivity {
    //Declaration Variables//
    private static final String TAG = "Registration_For_Students";
    public Button cancel;
    public EditText username, password1, password2, email1, fname, lname;
    public FirebaseFirestore firebaseFirestore;


    @Override
    //Content View//
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration__for__students);

        username = (EditText) findViewById(R.id.Username_input);
        email1 = (EditText)  findViewById(R.id.Email_input);
        password1 = (EditText) findViewById(R.id.Password_input);
        password2 = (EditText)  findViewById(R.id.ConfirmPassword_input);
        fname = (EditText) findViewById(R.id.First_Name_input);
        lname = (EditText) findViewById(R.id.Surname_input);

        findViewById(R.id.ProceedB).setOnClickListener(this::onClick);

        cancel = (Button) findViewById(R.id.CancelB);
        firebaseFirestore = FirebaseFirestore.getInstance();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancel = new Intent(Registration_For_Students.this,Login.class);
                startActivity(cancel);
                cancel.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            }
        });

}

    public void registerNewEmail(final String email, String fname, String lname,
                                 String pass1, String pass2){

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pass1)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createStudentWithEmail:onComplete:" + task.isSuccessful());

                        if (task.isSuccessful()){
                            Log.d(TAG, "onComplete: AuthState: " +
                                    FirebaseAuth.getInstance().getCurrentUser().getUid());

                            Students students = new Students();
                            students.setEmail(email);
                            students.setFname(fname);
                            students.setLname(lname);
                            students.setPass1(pass1);
                            students.setPass1(pass2);
                            students.setStud_id(FirebaseAuth.getInstance().getUid());

                            FirebaseFirestoreSettings settings =
                                    new FirebaseFirestoreSettings.Builder()
                                            .build();
                            firebaseFirestore.setFirestoreSettings(settings);

                            DocumentReference newUserRef = firebaseFirestore
                                    .collection(getString(R.string.collection_students))
                                    .document(FirebaseAuth.getInstance().getUid());

                            newUserRef.set(students)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if(task.isSuccessful()){
                                                FirebaseUser student = FirebaseAuth.getInstance().getCurrentUser();
                                                if (student == null)
                                                    return;
                                                student.sendEmailVerification().addOnCompleteListener
                                                        (new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()) {
                                                                    FirebaseAuth.getInstance().signOut();
                                                                    Toast.makeText(
                                                                            Registration_For_Students.this,
                                                                            "Verify your email address",
                                                                            Toast.LENGTH_SHORT).show();
                                                                    finish();
                                                                }
                                                                else{
                                                                    overridePendingTransition
                                                                            (0, 0);
                                                                    finish();
                                                                    overridePendingTransition
                                                                            (0, 0);
                                                                    startActivity(getIntent());
                                                                    Toast.makeText(
                                                                            Registration_For_Students.this,
                                                                            "Email sent failed",
                                                                            Toast.LENGTH_SHORT).show();
                                                                }
                                                            }
                                                        });

                                            }else{
                                                View parentLayout = findViewById(android.R.id.content);
                                                Snackbar.make(parentLayout,
                                                        "Please try again, something went wrong",
                                                        Snackbar.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                        }
                        else {
                            View parentLayout = findViewById(android.R.id.content);
                            Snackbar.make(parentLayout,
                                    "Please try again, something went wrong",
                                    Snackbar.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    @SuppressLint("LongLogTag")
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ProceedB:{
                Log.d(TAG, "onClick: attempting to register.");

                if(!Patterns.EMAIL_ADDRESS.matcher(email1.getText().toString().trim()).matches()){
                    email1.setError("Please provide valid email address");
                    email1.requestFocus();
                    return;
                }


                if(!isEmpty(email1.getText().toString())
                        && !isEmpty(password1.getText().toString())
                        && !isEmpty(password2.getText().toString())
                        && !isEmpty(fname.getText().toString())
                        && !isEmpty(lname.getText().toString()))
                {

                    if(doStringsMatch(password1.getText().toString(),
                            password2.getText().toString())){

                        registerNewEmail(email1.getText().toString(),
                                password1.getText().toString(),
                                password2.getText().toString(),
                                fname.getText().toString(),
                                lname.getText().toString());
                    }else{
                        Toast.makeText(Registration_For_Students.this,
                                "Passwords do not Match",
                                Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(Registration_For_Students.this,
                            "You must fill out all the fields",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    private boolean doStringsMatch(String password1, String password2) {

        return password1.equals(password2);
    }
}
