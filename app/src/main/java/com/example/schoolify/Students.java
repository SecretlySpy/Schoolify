package com.example.schoolify;

import android.os.Parcel;
import android.os.Parcelable;

public class Students implements Parcelable {
    private String username;
    private String email;
    private String stud_id;
    private String fname;
    private String lname;
    private String pass1;
    private String pass2;

    public static final Creator<Students> CREATOR = new Creator<Students>() {
        @Override
        public Students createFromParcel(Parcel in) {
            return new Students(in);
        }

        @Override
        public Students[] newArray(int size) {
            return new Students[size];
        }
    };

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStud_id(String stud_id) {
        this.stud_id = stud_id;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Students(String email, String stud_id, String fname, String lname,
                    String pass1, String pass2, String username) {

        this.email = email;
        this.stud_id = stud_id;
        this.fname = fname;
        this.lname = lname;
        this.pass1 = pass1;
        this.pass2 = pass2;
        this.username = username;
    }

    public Students() {

    }

    protected Students(Parcel in) {
        email = in.readString();
        stud_id = in.readString();
        fname = in.readString();
        lname = in.readString();
        pass1 = in.readString();
        pass2 = in.readString();
        username = in.readString();
    }


    public String getEmail() {
        return email;
    }

    public String getStud_id() {
        return stud_id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getPass1() {
        return pass1;
    }

    public String getPass2() {
        return pass2;
    }


    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", stud_id='" + stud_id + '\'' +
                ", username='" + username + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", pass1='" + pass1 + '\'' +
                ", pass2='" + pass2 + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(stud_id);
        dest.writeString(username);
        dest.writeString(fname);
        dest.writeString(lname);
        dest.writeString(pass1);
        dest.writeString(pass2);
    }

}