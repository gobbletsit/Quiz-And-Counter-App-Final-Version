package com.example.android.quizgameapp;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionRadioButton extends Fragment {

    private SharedPreferences my_preferences;

    // SETTING THE STRINGS TO USE
    private String questionString, first, second, third;

    // TO USE FOR CHANGING THE BOOLEANS TO USE FROM SHARED PREFERENCES
    private int fragPosition;

    private QuestionAndAnswers questionAndAnswers;

    private TextView questionTextView;

    public RadioGroup buttonsGroup;

    private ArrayList<String> offeredAnswers = new ArrayList<>();

    public boolean btn1Pressed, btn2Pressed, btn3Pressed;

    // TO USE FOR SWITCHING FRAGMENTS
    private ViewPager viewPager1;

    // LISTENER TO STORE VALUES OF EACH QUESTION RADIO FRAGMENT AND SWITCH THEM
    private View.OnClickListener questionButtonListener;

    public QuestionRadioButton() {
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
        View rootView1 = inflater.inflate(R.layout.fragment_question_radio_button, container, false);

        // CREATING A LIST OF STRINGS (OFFERED ANSWERS)
        offeredAnswers = new ArrayList<>();

        // ADDING THE STRINGS (ANSWERS)
        offeredAnswers.add(first);
        offeredAnswers.add(second);
        offeredAnswers.add(third);

        // CREATING THE BUTTONS AND GROUP FOR USAGE IN AN QUESTIONS AND ANSWERS OBJECT
        RadioButton btn1 = new RadioButton(getContext());
        RadioButton btn2 = new RadioButton(getContext());
        RadioButton btn3 = new RadioButton(getContext());

        // FINDING THE RADIO GROUP TO DISPLAY THE BUTTONS FOR CHOOSING THE ANSWER
        buttonsGroup = (RadioGroup) rootView1.findViewById(R.id.radio_group);

        // CREATING A CUSTOM OBJECT
        questionAndAnswers = new QuestionAndAnswers(questionString, offeredAnswers, btn1, btn2, btn3, buttonsGroup);

        // FINDING THE TEXT VIEW TO DISPLAY THE ASSIGNED QUESTION
        questionTextView = (TextView) rootView1.findViewById(R.id.question_text_view);
        questionTextView.setText(questionAndAnswers.getMyQuestion());

        // USING PREFERENCE MANAGER TO WRITE TO THE FILE
        my_preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        // FINDING THE VIEW PAGER FROM PARENT FRAGMENT
        viewPager1 = (ViewPager) QuizApp.mInstance.childPager.findViewById(R.id.pager_quiz_app);

        // TO GET THE VALUE OF PRESSED BUTTON FROM PREFERENCES
        switch (fragPosition){
            case 1:
                btn1Pressed = my_preferences.getBoolean("pressed1", false);
                btn2Pressed = my_preferences.getBoolean("pressed2", false);
                btn3Pressed = my_preferences.getBoolean("pressed3", false);
                break;
            case 2:
                btn1Pressed = my_preferences.getBoolean("pressed11", false);
                btn2Pressed = my_preferences.getBoolean("pressed22", false);
                btn3Pressed = my_preferences.getBoolean("pressed33", false);
                break;
            case 3:
                btn1Pressed = my_preferences.getBoolean("pressed111", false);
                btn2Pressed = my_preferences.getBoolean("pressed222", false);
                btn3Pressed = my_preferences.getBoolean("pressed333", false);
                break;
            case 4:
                btn1Pressed = my_preferences.getBoolean("pressed1111", false);
                btn2Pressed = my_preferences.getBoolean("pressed2222", false);
                btn3Pressed = my_preferences.getBoolean("pressed3333", false);
                break;
            case 5:
                btn1Pressed = my_preferences.getBoolean("pressed11111", false);
                btn2Pressed = my_preferences.getBoolean("pressed22222", false);
                btn3Pressed = my_preferences.getBoolean("pressed33333", false);
                break;
        }

        // CREATING AND GETTING THE RADIO BUTTONS FROM A CUSTOM OBJECT
        RadioButton buttons = (questionAndAnswers.getMyBtn1());
        RadioButton buttons1 = (questionAndAnswers.getMyBtn2());
        RadioButton buttons2 = (questionAndAnswers.getMyBtn3());

        // CREATING AND ADDING THE GROUP FROM A CUSTOM OBJECT
        buttonsGroup = (questionAndAnswers.getMyGroup());

        // FOR SETTING THE STATES OF BUTTONS IF CHECKED(PRESSED)
        if (btn1Pressed){
            buttons.setChecked(true);
            for (int i = 0; i < buttonsGroup.getChildCount(); i++)
                buttonsGroup.getChildAt(i).setEnabled(false);
        }else if (btn2Pressed){
            buttons1.setChecked(true);
            for (int i = 0; i < buttonsGroup.getChildCount(); i++)
                buttonsGroup.getChildAt(i).setEnabled(false);
        }else if (btn3Pressed){
            buttons2.setChecked(true);
            for (int i = 0; i < buttonsGroup.getChildCount(); i++)
                buttonsGroup.getChildAt(i).setEnabled(false);
        } else {
            for (int a = 0; a < buttonsGroup.getChildCount(); a++){

                // SETTING ON CLICK LISTENER FOR BUTTONS TO SWITCH FRAGMENT
                buttonsGroup.getChildAt(a).setOnClickListener(questionButtonListener);
            }
        }

        return rootView1;
    }

    // TO USE IN ADAPTER FOR CHANGING THE ARRAY LIST ANSWERS
    public void changeStringArray (String firstAnswer, String secondAnswer, String thirdAnswer){
        first = firstAnswer;
        second = secondAnswer;
        third = thirdAnswer;
    }

    // TO USE IN ADAPTER FOR CHANGING THE STRING QUESTION
    public void changeQuestion (String question){
        questionString = question;
    }

    // TO USE IN ADAPTER FOR CHANGING THE BOOLEANS TO GET FROM SHARED PREFERENCES
    public void changeBooleans (int position){
        fragPosition = position;
    }

    // TO USE IN ADAPTER FOR CHANGING THE LISTENERS ON BUTTONS BECAUSE OF DIFFERENT STORED VALUES
    public void changeListener (int position){

        fragPosition = position;
        switch (fragPosition){
            case 1:
                questionButtonListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final SharedPreferences.Editor sharedPrefEditor = my_preferences.edit();
                        if (buttonsGroup.getChildAt(0).isPressed()){
                            sharedPrefEditor.putBoolean("pressed1", true);
                            for (int i = 0; i < buttonsGroup.getChildCount(); i++)
                                buttonsGroup.getChildAt(i).setEnabled(false);
                        }
                        if (buttonsGroup.getChildAt(1).isPressed()) {
                            sharedPrefEditor.putInt("value1", 1);
                            sharedPrefEditor.putBoolean("pressed2", true);
                            for (int i = 0; i < buttonsGroup.getChildCount(); i++)
                                buttonsGroup.getChildAt(i).setEnabled(false);
                        }
                        if (buttonsGroup.getChildAt(2).isPressed()){
                            sharedPrefEditor.putBoolean("pressed3", true);
                            for (int i = 0; i < buttonsGroup.getChildCount(); i++)
                                buttonsGroup.getChildAt(i).setEnabled(false);
                        }
                        sharedPrefEditor.apply();

                        // SETTING THE VIEW PAGER TO SWITCH TO NEXT FRAGMENT UPON CLICKING THE BUTTON
                        viewPager1.setCurrentItem(1);
                    }
                };
                break;

            case 2:
                questionButtonListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final SharedPreferences.Editor sharedPrefEditor2 = my_preferences.edit();
                        if (buttonsGroup.getChildAt(0).isPressed()){
                            sharedPrefEditor2.putInt("value2", 1);
                            sharedPrefEditor2.putBoolean("pressed11", true);
                            for (int i = 0; i < buttonsGroup.getChildCount(); i++)
                                buttonsGroup.getChildAt(i).setEnabled(false);
                        }
                        if (buttonsGroup.getChildAt(1).isPressed()) {
                            sharedPrefEditor2.putBoolean("pressed22", true);
                            for (int i = 0; i < buttonsGroup.getChildCount(); i++)
                                buttonsGroup.getChildAt(i).setEnabled(false);
                        }
                        if (buttonsGroup.getChildAt(2).isPressed()){
                            sharedPrefEditor2.putBoolean("pressed33", true);
                            for (int i = 0; i < buttonsGroup.getChildCount(); i++)
                                buttonsGroup.getChildAt(i).setEnabled(false);
                        }
                        sharedPrefEditor2.apply();

                        // SETTING THE VIEW PAGER TO SWITCH TO NEXT FRAGMENT UPON CLICKING THE BUTTON
                        viewPager1.setCurrentItem(2);
                    }
                };
                break;

            case 3:
                questionButtonListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final SharedPreferences.Editor sharedPrefEditor3 = my_preferences.edit();
                        if (buttonsGroup.getChildAt(0).isPressed()){
                            sharedPrefEditor3.putBoolean("pressed111", true);
                            for (int i = 0; i < buttonsGroup.getChildCount(); i++)
                                buttonsGroup.getChildAt(i).setEnabled(false);
                        }
                        if (buttonsGroup.getChildAt(1).isPressed()) {
                            sharedPrefEditor3.putBoolean("pressed222", true);
                            for (int i = 0; i < buttonsGroup.getChildCount(); i++)
                                buttonsGroup.getChildAt(i).setEnabled(false);
                        }
                        if (buttonsGroup.getChildAt(2).isPressed()){
                            sharedPrefEditor3.putInt("value3", 1);
                            sharedPrefEditor3.putBoolean("pressed333", true);
                            for (int i = 0; i < buttonsGroup.getChildCount(); i++)
                                buttonsGroup.getChildAt(i).setEnabled(false);
                        }
                        sharedPrefEditor3.apply();

                        // SETTING THE VIEW PAGER TO SWITCH TO NEXT FRAGMENT UPON CLICKING THE BUTTON
                        viewPager1.setCurrentItem(3);
                    }
                };
                break;

            case 4:
                questionButtonListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final SharedPreferences.Editor sharedPrefEditor4 = my_preferences.edit();
                        if (buttonsGroup.getChildAt(0).isPressed()){
                            sharedPrefEditor4.putBoolean("pressed1111", true);
                            for (int i = 0; i < buttonsGroup.getChildCount(); i++)
                                buttonsGroup.getChildAt(i).setEnabled(false);
                        }
                        if (buttonsGroup.getChildAt(1).isPressed()) {
                            sharedPrefEditor4.putBoolean("pressed2222", true);
                            for (int i = 0; i < buttonsGroup.getChildCount(); i++)
                                buttonsGroup.getChildAt(i).setEnabled(false);
                        }
                        if (buttonsGroup.getChildAt(2).isPressed()){
                            sharedPrefEditor4.putInt("value4", 1);
                            sharedPrefEditor4.putBoolean("pressed3333", true);
                            for (int i = 0; i < buttonsGroup.getChildCount(); i++)
                                buttonsGroup.getChildAt(i).setEnabled(false);
                        }
                        sharedPrefEditor4.apply();

                        // SETTING THE VIEW PAGER TO SWITCH TO NEXT FRAGMENT UPON CLICKING THE BUTTON
                        viewPager1.setCurrentItem(4);
                    }
                };
                break;

            case 5:
                questionButtonListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final SharedPreferences.Editor sharedPrefEditor5 = my_preferences.edit();
                        if (buttonsGroup.getChildAt(0).isPressed()){
                            sharedPrefEditor5.putInt("value5", 1);
                            sharedPrefEditor5.putBoolean("pressed11111", true);
                            for (int i = 0; i < buttonsGroup.getChildCount(); i++)
                                buttonsGroup.getChildAt(i).setEnabled(false);
                        }
                        if (buttonsGroup.getChildAt(1).isPressed()) {
                            sharedPrefEditor5.putBoolean("pressed22222", true);
                            for (int i = 0; i < buttonsGroup.getChildCount(); i++)
                                buttonsGroup.getChildAt(i).setEnabled(false);
                        }
                        if (buttonsGroup.getChildAt(2).isPressed()){
                            sharedPrefEditor5.putBoolean("pressed33333", true);
                            for (int i = 0; i < buttonsGroup.getChildCount(); i++)
                                buttonsGroup.getChildAt(i).setEnabled(false);
                        }
                        sharedPrefEditor5.apply();

                        // SETTING THE VIEW PAGER TO SWITCH TO NEXT FRAGMENT UPON CLICKING THE BUTTON
                        viewPager1.setCurrentItem(5);
                    }
                };
        }
    }
}
