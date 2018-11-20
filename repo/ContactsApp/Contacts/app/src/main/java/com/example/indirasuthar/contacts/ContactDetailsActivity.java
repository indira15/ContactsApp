package com.example.indirasuthar.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ContactDetailsActivity extends AppCompatActivity {

    private static final String TAG = ContactDetailsActivity.class.getName();
    String  strMobileNumber ,number;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactdetails);

        TextView firstName,lastName,mobileNumber;
        final Button sendMessage;

        firstName = findViewById(R.id.firstName_cd);
        lastName = findViewById(R.id.lastName_cd);
        mobileNumber = findViewById(R.id.phoneNumber_cd);
        sendMessage=findViewById(R.id.sendOTPMessage);

        final String strFirstName = getIntent().getExtras().getString("user_firstname");
        final String strLastName = getIntent().getExtras().getString("user_lastname");
        strMobileNumber= getIntent().getExtras().getString("user_mobilenumber");

       // number = strMobileNumber;

        Log.d(TAG,"String Name"+strFirstName +strLastName+strMobileNumber);

        firstName.setText(strFirstName);
        lastName.setText(strLastName);
        mobileNumber.setText(strMobileNumber);


        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),SendMessageActivity.class);
                intent.putExtra("user_fname",strFirstName);
                intent.putExtra("user_lname",strLastName);
                intent.putExtra("user_number",strMobileNumber);
                startActivity(intent);
            }
        });
    }
}
