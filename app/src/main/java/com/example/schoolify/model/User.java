package com.example.schoolify.model;


public class User {

    public String fullName,confirmpassWord,emailAdd,passWord;

    public User(String fullname, String email, String confirmpassword, String password){

    }

    public User(String fullName,String emailAdd,String passWord){

        this.fullName = fullName;
        this.emailAdd = emailAdd;
        this.passWord = passWord;
        this.confirmpassWord = confirmpassWord;
    }
}
