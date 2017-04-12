package com.example.android.quizgameapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Toast;

import static android.support.v7.widget.AppCompatDrawableManager.get;

/**
 * Created by gobov on 3/10/2017.
 */

public class SimpleFragmAdapter extends FragmentPagerAdapter {

    private Context myContext;

    public SimpleFragmAdapter (Context context, FragmentManager fm){
        super(fm);
        myContext = context;
    }

    // GETTING THE MAIN FRAGMENTS TO BE PLACED IN THIS POSITIONS IN ADAPTER
    @Override
    public Fragment getItem(int position) {

        if (position == 0){
            return new OpeningFragment();
        } else if (position == 1){
            return new QuizApp();
        } else {
            return new CourtCounter();
        }
    }

    // GETTING THE OVERALL COUNT OF MAIN FRAGMENTS
    @Override
    public int getCount() {
        return 3;
    }

     //GETTING THE MAIN TITLES FROM STRING VALUES TO BE PLACED IN THIS POSITIONS IN TAB LAYOUT
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0){
            return myContext.getString(R.string.fragment_opening_fragment);
        } else if (position == 1){
            return myContext.getString(R.string.fragment_quiz_app);
        } else {
            return myContext.getString(R.string.fragment_court_counter);
        }
    }
}
