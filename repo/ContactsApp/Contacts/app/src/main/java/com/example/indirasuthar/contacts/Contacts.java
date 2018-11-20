package com.example.indirasuthar.contacts;

import com.google.gson.annotations.SerializedName;

public class Contacts {

    @SerializedName("firstname")
    private String firstName;
    @SerializedName("lastname")
    private String lastName;
    @SerializedName("mobilenumber")
    private String phoneNumber;

    public Contacts(String firstName, String lastName, String phoneNumer) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumer;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumer() {
        return phoneNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumer(String phoneNumer) {
        this.phoneNumber = phoneNumer;
    }
}


