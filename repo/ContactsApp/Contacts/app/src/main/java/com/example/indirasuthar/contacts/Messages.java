package com.example.indirasuthar.contacts;

public class Messages {

        private int id;
        private String firstname;
        private String lastname;
        private String mobilenumber;
        private String otp;
        private String time;



    public Messages(){}

        public Messages(String firstname,String lastname,String mobilenumber,String otp,String time) {
            super();
               this.firstname = firstname;
               this.lastname=lastname;
               this.mobilenumber=mobilenumber;
               this.otp=otp;
               this.time=time;
        }

        //getters & setters

        @Override
        public String toString() {
            return "Messages [ firstname = " + firstname + ", lastname=" + lastname + ", mobilenumber=" + mobilenumber +",otp =" +otp
                    + ",time =" +time + "]";
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
