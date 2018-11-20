package com.example.indirasuthar.contacts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class MessageAdapter  extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder>{

    private static final String TAG= MessageAdapter.class.getSimpleName();
    private List<Messages> messageList ;
    private Context mContext;

    public MessageAdapter(List<Messages> messageList,Context context) {
        this.mContext = context;
        this.messageList= messageList;

    }

    public class MessageViewHolder extends RecyclerView.ViewHolder{

        public TextView nameOfContact,timeOfMessageSend,otpSent,mobileNumber;

        public  View view;
        public MessageViewHolder(View itemView) {
            super(itemView);

            view = itemView;
            nameOfContact = (TextView) itemView.findViewById(R.id.nameofContact);
            timeOfMessageSend = (TextView) itemView.findViewById(R.id.timeofMessage);
            otpSent=(TextView) itemView.findViewById(R.id.otpSendMessage);
            mobileNumber = (TextView) itemView.findViewById(R.id.mobileno);


        }


    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_item_list, parent, false);



        return new MessageViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder,final int position) {

        final Messages message = messageList.get(position);
        String Name = "Name : " +message.getFirstname() ,
                Time = "Time : " +message.getTime(),
                OTP_Number ="OTP Number : " +message.getOtp() ,
                Mobile_Number= "Mobile Number : "+message.getMobilenumber();
        holder.nameOfContact.setText(Name);
        holder.timeOfMessageSend.setText(Time);
        holder.otpSent.setText(OTP_Number);
        holder.mobileNumber.setText(Mobile_Number);
        Log.d(TAG,"Message Name"+ message.getFirstname());

    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

}
