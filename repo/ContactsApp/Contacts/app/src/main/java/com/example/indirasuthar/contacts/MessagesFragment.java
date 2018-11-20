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

import java.util.ArrayList;
import java.util.List;

public class MessagesFragment extends Fragment {

    private RecyclerView recyclerView;
    private MessageAdapter mAdapter;
    private Context mContext;
    private List messageList = new ArrayList();

    public static final String TAG = MessagesFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_message_recycler_view, container, false);

        recyclerView = (RecyclerView)rootview.findViewById(R.id.recycler_view);

        mAdapter = new MessageAdapter(messageList,this.getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        MessageSqliteOpenHelper sqliteHelper = new MessageSqliteOpenHelper(getContext());

        messageList.addAll(sqliteHelper.getAllMessages());
        Log.d(TAG,"Message list" + messageList);
        mAdapter.notifyDataSetChanged();

        return rootview;
    }


}
