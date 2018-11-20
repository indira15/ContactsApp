package com.example.indirasuthar.contacts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetContactsDetails {

    @GET("5bf1108f300000630056f225")
    Call<List<Contacts>> getAllContacts();

    @GET("https://www.fast2sms.com/dev/bulk")
    Call<SMSResponse> sendSMS(@Query("authorization") String auth,
                                   @Query("sender_id") String senderId,
                                   @Query("message") String message,
                                   @Query("language") String lang,
                                   @Query("route") String route,
                                   @Query("numbers") String numbers);
}
