package com.example.android.quizgameapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by gobov on 3/31/2017.
 */

public class MainFragmentActivity extends AppCompatActivity {

    public static MainFragmentActivity mInstanceMain = null;

    SimpleFragmAdapter myAdapter;
    TabLayout myTabLayout;
    ViewPager myPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_fragment_activity);

        mInstanceMain = this;

        myAdapter = new SimpleFragmAdapter(this, getSupportFragmentManager());

        myPager = (ViewPager) findViewById(R.id.pager);
        myPager.setAdapter(myAdapter);

        myTabLayout = (TabLayout) findViewById(R.id.indicator);
        myTabLayout.setupWithViewPager(myPager);

    }
}

