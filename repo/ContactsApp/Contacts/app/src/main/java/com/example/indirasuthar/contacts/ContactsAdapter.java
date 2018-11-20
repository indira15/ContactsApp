package com.example.indirasuthar.contacts;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.MyViewHolder> {

        private List<Contacts> contactsList;
        public Context mContext;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView firstName, lastName, mobilenumber;
            public View item;

            public MyViewHolder(View view) {
                super(view);
                firstName= (TextView) view.findViewById(R.id.firstName);
                lastName = (TextView) view.findViewById(R.id.lastName);
                mobilenumber = (TextView) view.findViewById(R.id.phoneNumber);
                item = view;
            }
        }


        public ContactsAdapter(List<Contacts> contactsList,Context mContext) {
            this.contactsList = contactsList;
            this.mContext = mContext;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.contacts_item_list, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder,final int position) {
            final Contacts contacts = contactsList.get(position);

            holder.firstName.setText(contacts.getFirstName());
            holder.lastName.setText(contacts.getLastName());
            holder.mobilenumber.setText(contacts.getPhoneNumer());

            holder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext,ContactDetailsActivity.class);
                    intent.putExtra("user_firstname", contactsList.get(position).getFirstName());
                    intent.putExtra("user_lastname",contactsList.get(position).getLastName());
                    intent.putExtra("user_mobilenumber", contactsList.get(position).getPhoneNumer());
                    mContext.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return contactsList.size();
        }

}
