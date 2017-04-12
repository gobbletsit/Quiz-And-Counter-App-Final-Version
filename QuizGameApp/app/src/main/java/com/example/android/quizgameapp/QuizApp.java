package com.example.android.quizgameapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viewpagerindicator.TitlePageIndicator;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuizApp extends Fragment {

    public static QuizApp mInstance = null;

    ChildFragmAdapter childAdapter;

    TitlePageIndicator childIndicator;

    ViewPager childPager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }

    public QuizApp () {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // INFLATE THE LAYOUT FOR THIS FRAGMENT
        View rootView = inflater.inflate(R.layout.fragment_quiz_app, container, false);

        mInstance = this;

        // CREATING THE ADAPTER TO USE FOR VIEW PAGER (CHILD PAGER)
        childAdapter = new ChildFragmAdapter(getContext(), getChildFragmentManager());
        childAdapter.setFragments(getContext());

        // FINDING AND SETTING THE VIEW PAGER TO DISPLAY THE CHILD FRAGMENTS
        childPager = (ViewPager) rootView.findViewById(R.id.pager_quiz_app);
        childPager.setAdapter(childAdapter);

        // FINDING AND SETTING THE TITLES OF FRAGMENTS TO DISPLAY IN INDICATOR
        childIndicator = (TitlePageIndicator) rootView.findViewById(R.id.indicator_quiz_app);
        childIndicator.setViewPager(childPager);

        return rootView;

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
