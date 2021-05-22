package com.example.schoolify;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;


public class Registration_For_Students extends AppCompatActivity {
    //Declaration Variables//
    public Button cancel, proceed;
    public EditText username, password1, password2, email1, fname, lname;
    public FirebaseFirestore firebaseFirestore;
    public FirebaseAuth firebaseAuth;

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

        cancel = (Button) findViewById(R.id.CancelB);
        cancel.setOnClickListener(v -> openLogin());
        firebaseFirestore = FirebaseFirestore.getInstance();
        //Proceed Button//
        proceed = (Button) findViewById(R.id.ProceedB);
        proceed.setOnClickListener(v -> {

           String Username = username.getText().toString();
           String Email = email1.getText().toString();
           String Password = password1.getText().toString();
           String ConfirmedPass = password2.getText().toString();
           String First_Name = fname.getText().toString();
           String Last_Name = lname.getText().toString();
           String id = UUID.randomUUID().toString();
        saveToFireStore(id, Username, Email, Password, ConfirmedPass, First_Name, Last_Name);

        });



}

    //Button function//

    //Cancel Button//
    public void openLogin() {
        Intent login = new Intent(this, Login.class);
        startActivity(login);
    }
public void saveToFireStore(String id, String Username, String Email, String Password, String ConfirmedPass,String First_Name, String Last_Name){
    if (!Username.isEmpty()){
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("Username", Username);
        firebaseFirestore.collection("Documents").document(Username).set(map);
    }
    else{
        Toast.makeText(this, "Username is empty", Toast.LENGTH_SHORT).show();
        return;
    }
    if (!Email.isEmpty()){
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("Email", Email);
        firebaseFirestore.collection("Documents").document(Email).set(map);
    }
    else{
        Toast.makeText(this, "Email is empty", Toast.LENGTH_SHORT).show();
        return;
    }
    if (!Password.isEmpty()){
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("Password", Password);
        firebaseFirestore.collection("Documents").document(Password).set(map);
    }
    else{
        Toast.makeText(this, "Password is empty", Toast.LENGTH_SHORT).show();
        return;
    }
    if (!ConfirmedPass.isEmpty()){
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("ConfirmedPass", ConfirmedPass);
        firebaseFirestore.collection("Documents").document(ConfirmedPass).set(map);
    }
    else{
        Toast.makeText(this, "Please confirm your password", Toast.LENGTH_SHORT).show();
        return;
    }
    if (!First_Name.isEmpty()){
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("First_Name", First_Name);
        firebaseFirestore.collection("Documents").document(First_Name).set(map);
    }
    else {
        Toast.makeText(this, "First Name is empty", Toast.LENGTH_SHORT).show();
        return;
    }
    if (!Last_Name.isEmpty()){
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("Last_Name", Last_Name);
        firebaseFirestore.collection("Documents").document(Last_Name).set(map);

    }
    else {
        Toast.makeText(this, "last Name is empty", Toast.LENGTH_SHORT).show();
        return;
    }
}

}
