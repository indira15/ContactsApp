package com.example.indirasuthar.contacts;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactsFragment extends Fragment {

    private List<Contacts> contactsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ContactsAdapter mAdapter;
    private ProgressBar mProgressBar;

    private static final String BASE_URL = "http://www.mocky.io/v2/";

    public static final String TAG = ContactsFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview;
        rootview = inflater.inflate(R.layout.fragment_contacts_recycler_view, container, false);
        recyclerView = (RecyclerView) rootview.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        attachAdapter();
        mProgressBar = rootview.findViewById(R.id.progress_bar);
        mProgressBar.setVisibility(View.VISIBLE);
        GetContactsDetails service = RetrofitClientInstance.getRetrofitInstance(BASE_URL).create(GetContactsDetails.class);
        Call<List<Contacts>> call = service.getAllContacts();
        Log.d(TAG,"calling interface");
        call.enqueue(new Callback<List<Contacts>>(){

                         @Override
                         public void onResponse(Call<List<Contacts>> call, Response<List<Contacts>> response) {
                             contactsList.clear();
                             mProgressBar.setVisibility(View.GONE);
                             contactsList.addAll(response.body());
                             mAdapter.notifyDataSetChanged();

                         }

                         @Override
                         public void onFailure(Call<List<Contacts>> call, Throwable t) {
                             Log.d(TAG,"on Failure ");
                             Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();

                         }
                     });



        return rootview;
    }


    public void attachAdapter(){
        mAdapter = new ContactsAdapter(contactsList,getContext());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


    }

}
