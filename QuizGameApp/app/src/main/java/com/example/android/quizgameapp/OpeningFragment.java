package com.example.android.quizgameapp;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.os.Build.VERSION_CODES.M;


/**
 * A simple {@link Fragment} subclass.
 */
public class OpeningFragment extends Fragment {

    private Button startButton;


    public OpeningFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootStartingView = inflater.inflate(R.layout.fragment_opening_fragment, container, false);

        // FINDING THE MAIN VIEW PAGER TO SWITCH TO ANOTHER FRAGMENT (QUIZ GAME)
        final ViewPager viewPagerQuizGame = (ViewPager) MainFragmentActivity.mInstanceMain.myPager.findViewById(R.id.pager);

        // FINDING THE BUTTON VIEW TO PUT LISTENER ON IT TO SWITCH FRAGMENTS
        startButton = (Button) rootStartingView.findViewById(R.id.start_game_btn);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPagerQuizGame.setCurrentItem(1);
            }
        });

        return rootStartingView;
    }

}
