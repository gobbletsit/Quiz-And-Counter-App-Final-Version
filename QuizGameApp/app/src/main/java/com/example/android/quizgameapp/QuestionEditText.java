package com.example.android.quizgameapp;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.InputFilter;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionEditText extends Fragment {

    private TextView questionTextView7;

    private EditText answerInput;

    private String correctAnswer = "SUPLEX";


    public QuestionEditText() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        setRetainInstance(true);
        super.onResume();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView7 = inflater.inflate(R.layout.fragment_question_edit_text, container, false);

        //setRetainInstance(true);

        // FINDING THE TEXT VIEW TO DISPLAY THE ASSIGNED QUESTION
        questionTextView7 = (TextView) rootView7.findViewById(R.id.question_text_view_7);
        questionTextView7.setText("7. Which wrestling throw does the image represent ?");

        // USING PREFERENCE MANAGER TO WRITE TO THE FILE
        final SharedPreferences my_preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        // CREATING AN EDITOR TO USE FOR STORING VALUES
        final SharedPreferences.Editor editorEditText = my_preferences.edit();

        // FINDING THE VIEW PAGER FROM THE QUIZ APP ACTIVITY
        final ViewPager viewPager7 = (ViewPager) MainFragmentActivity.mInstanceMain.myPager.findViewById(R.id.pager);

        // TO GET THE USER EDIT TEXT ANSWER AND TO STORE THE VALUE
        answerInput = (EditText) rootView7.findViewById(R.id.answer_edit_text);
        answerInput.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
        answerInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    if (answerInput.getText().toString().equals(correctAnswer)) {
                        editorEditText.putInt("text_answer_value", 1);
                    }
                    editorEditText.apply();
                    viewPager7.setCurrentItem(2);
                }
                return false;
            }
        });

        return rootView7;

    }

}