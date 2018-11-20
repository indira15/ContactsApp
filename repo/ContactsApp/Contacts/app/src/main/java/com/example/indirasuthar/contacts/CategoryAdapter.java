package com.example.indirasuthar.contacts;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class CategoryAdapter extends FragmentPagerAdapter {

    private Context mcontext;


    public CategoryAdapter(Context context,FragmentManager fm) {
        super(fm);
        mcontext=context;

    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return new ContactsFragment();
        }else  {
            return new MessagesFragment();
            }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        if (position == 0) {
            return mcontext.getString(R.string.category_contacts);
        }else  {
            return mcontext.getString(R.string.category_messages);
        }
    }
}
