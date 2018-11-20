package com.example.indirasuthar.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class SendMessageActivity  extends AppCompatActivity {


    private static final String TAG = SendMessageActivity.class.getSimpleName();

    private String strmobileNumber, message, strFirstName, strLastName, strTime, strOTPNumber;
    private Button sendButton;
    private TextView otpMessage;
    private ProgressBar mProgressBar;
    public static final String AUTHORIZATION = "Z74zDsPbfIReO02S8Q5oanJvXmjUGWrEg3ApkTuFdl1LyqNcHByFdv8M2kjRV6L4OwKglxC7uBsZpXIY";
    public static final String SENDER_ID = "FSTSMS";
    private static final String BASE_SMSURL = "https://www.fast2sms.com/dev/bulk/";
    private List<String> smsList = new ArrayList<String>();
    private List<String> sentMessageList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendmessage);


        loadContentView();
        smsContent();

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy HH:mm a", Locale.ENGLISH);
                Date time = new Date();
                sdf.setTimeZone(TimeZone.getTimeZone("IST"));
                strTime = sdf.format(time);
                mProgressBar.setVisibility(View.VISIBLE);
                Log.d(TAG, "Current time" + strTime);
                sendSMS();

            }
        });

    }


    private void sendSMS() {

        String language = "english";
        String route = "p";  //promotional route;

        smsList.add(AUTHORIZATION);
        smsList.add(SENDER_ID);
        smsList.add(message);
        smsList.add(language);
        smsList.add(route);
        smsList.add(strmobileNumber);


        GetContactsDetails service = RetrofitClientInstance.getRetrofitInstance(BASE_SMSURL).create(GetContactsDetails.class);
        Call call = service.sendSMS(AUTHORIZATION, SENDER_ID, message, language, route, strmobileNumber);
        Log.d(TAG, "calling sms interface");
        call.enqueue(new Callback<SMSResponse>() {
            @Override
            public void onResponse(Call<SMSResponse> call, Response<SMSResponse> response) {

                int statusCode = response.code();
                Log.d(TAG, "statuscode" + statusCode);
                if (response.isSuccessful()) {

                    SMSResponse responseBody = response.body();
                    Log.d(TAG, " onSuccess");
                    if (responseBody.isJsonMemberReturn()) {
                        if (response.isSuccessful())
                            Log.d(TAG, "onResponse->success");
                        mProgressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),"SMS Sent Successfully",Toast.LENGTH_LONG).show();
                        sentMessageDetails();

                    }


                } else {
                    Log.d(TAG, "onResponse Failure" + response.body());
                    Toast.makeText(getApplicationContext(),"SMS Fail",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d(TAG, "onfailure called");
            }
        });


    }

    private void sentMessageDetails() {

        MessageSqliteOpenHelper sqliteHelper = new MessageSqliteOpenHelper(getApplicationContext());

        // add
        Messages messages = new Messages();

        messages.setFirstname(strFirstName);
        messages.setLastname(strLastName);
        messages.setOtp(strOTPNumber);
        messages.setMobilenumber(strmobileNumber);
        messages.setTime(strTime);
        messages.setId(1);


        Log.d(TAG, "Message toString" + messages.toString());
        sqliteHelper.addMessage(messages);


        sentMessageList.add(strFirstName);
        sentMessageList.add(strLastName);
        sentMessageList.add(strOTPNumber);
        sentMessageList.add(strmobileNumber);
        sentMessageList.add(strTime);
        Log.d(TAG, " Sent Message list" + sentMessageList);


    }

    private void loadContentView() {

        otpMessage = findViewById(R.id.otpMessage);
        sendButton = findViewById(R.id.sendMessage);
        mProgressBar = findViewById(R.id.progress_bar);

    }

    private void smsContent() {


        String textMessage = "Hi. Your OTP is: ";

        Random rnd = new Random();
        int randomNumber = 100000 + rnd.nextInt(900000);
        strOTPNumber = String.valueOf(randomNumber);
        Log.d(TAG, "Random number" + strOTPNumber);
        message = textMessage + strOTPNumber;
        otpMessage.setText(message);

        strFirstName = getIntent().getExtras().getString("user_fname");
        strLastName = getIntent().getExtras().getString("user_lname");
        strmobileNumber = getIntent().getExtras().getString("user_number");

        Log.d(TAG, " MobileNumber" + strmobileNumber);

    }

}
