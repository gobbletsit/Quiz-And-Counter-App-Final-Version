package com.example.android.quizgameapp;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionCheckBox extends Fragment {

    private QuestionAndAnswers questionAndAnswers6;

    private TextView questionTextView6;

    private ArrayList<String> offeredAnswers6;

    private boolean check1, check2, check3;

    public QuestionCheckBox() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        setRetainInstance(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView6 = inflater.inflate(R.layout.fragment_question_check_box, container, false);

        // CREATING A LIST OF STRINGS (OFFERED ANSWERS)
        offeredAnswers6 = new ArrayList<String>();
        // ADDING THE STRINGS (ANSWERS)
        offeredAnswers6.add("Mark Schultz");
        offeredAnswers6.add("Hamid Sourian");
        offeredAnswers6.add("Alexander Karelin");

        CheckBox btn1 = new CheckBox(getContext());
        CheckBox btn2 = new CheckBox(getContext());
        CheckBox btn3 = new CheckBox(getContext());

        // FINDING THE LINEAR LAYOUT (CHECKBOX CONTAINER)
        final LinearLayout linearCheck = (LinearLayout) rootView6.findViewById(R.id.check_box_container);

        // CREATING A CUSTOM OBJECT
        questionAndAnswers6 = new QuestionAndAnswers("6. Who is considered the best wrestler of all time ?", offeredAnswers6, btn1, btn2, btn3, linearCheck);

        // FINDING THE TEXT VIEW TO DISPLAY THE ASSIGNED QUESTION
        questionTextView6 = (TextView) rootView6.findViewById(R.id.question_text_view_5);
        questionTextView6.setText(questionAndAnswers6.getMyQuestionCheck());

        // USING PREFERENCE MANAGER TO WRITE TO THE FILE
        final SharedPreferences my_preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        // FINDING THE VIEW PAGER FROM THE QUIZ APP ACTIVITY
        final ViewPager viewPager6 = (ViewPager) QuizApp.mInstance.childPager.findViewById(R.id.pager_quiz_app);

        // GETTING THE CHECKED VALUES FROM SHARED PREFERENCES
        check1 = my_preferences.getBoolean("checkPressed0", false);
        check2 = my_preferences.getBoolean("checkPressed1", false);
        check3 = my_preferences.getBoolean("checkPressed2", false);

        // CREATING AND GETTING THE CHECKBOXES FROM CUSTOM OBJECT
        CheckBox checkBox = (questionAndAnswers6.getmCheckBtn1());
        CheckBox checkBox1 = (questionAndAnswers6.getmCheckBtn2());
        CheckBox checkBox2 = (questionAndAnswers6.getmCheckBtn3());

        // CHECKING AND SETTING THE STATE OF BUTTONS IF PRESSED(CHECKED)
        if (check1) {
            checkBox.setChecked(true);
            checkBox.setEnabled(false);
            if (check2){
                checkBox2.setEnabled(false);
            }
            if (check3){
                checkBox1.setEnabled(false);
            }
        }
        if (check2){
            checkBox1.setChecked(true);
            checkBox1.setEnabled(false);
            if (check3){
                checkBox.setEnabled(false);
            }
        }
        if (check3){
            checkBox2.setChecked(true);
            checkBox2.setEnabled(false);
        } else {
            for (int a = 0; a < offeredAnswers6.size(); a++) {

                // SETTING ON CLICK LISTENERS ON BUTTONS TO STORE VALUES
                linearCheck.getChildAt(a).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final SharedPreferences.Editor sharedPrefEditor6 = my_preferences.edit();
                        if (linearCheck.getChildAt(0).isPressed()){
                            sharedPrefEditor6.putBoolean("checkPressed0", true);
                            linearCheck.getChildAt(0).setEnabled(false);
                        }else if (linearCheck.getChildAt(1).isPressed()) {
                            sharedPrefEditor6.putInt("value6_1", 1);
                            sharedPrefEditor6.putBoolean("checkPressed1", true);
                            linearCheck.getChildAt(1).setEnabled(false);
                        }else if (linearCheck.getChildAt(2).isPressed()) {
                            sharedPrefEditor6.putInt("value6_2", 1);
                            sharedPrefEditor6.putBoolean("checkPressed2", true);
                            linearCheck.getChildAt(2).setEnabled(false);
                        }

                        // TO DISABLE THE ONLY LEFT BUTTON AFTER 2 BUTTONS ARE CHECKED
                        if (linearCheck.getChildAt(0).isEnabled() == false && linearCheck.getChildAt(1).isEnabled() == false){
                            linearCheck.getChildAt(2).setEnabled(false);
                            viewPager6.setCurrentItem(6);
                        } else if (linearCheck.getChildAt(0).isEnabled() == false && linearCheck.getChildAt(2).isEnabled() == false){
                            linearCheck.getChildAt(1).setEnabled(false);
                            viewPager6.setCurrentItem(6);
                        } else if (linearCheck.getChildAt(1).isEnabled() == false && linearCheck.getChildAt(2).isEnabled() == false){
                            linearCheck.getChildAt(0).setEnabled(false);
                            viewPager6.setCurrentItem(6);
                        }

                        sharedPrefEditor6.apply();

                    }
                });
            }
        }

        return rootView6;
    }
}
