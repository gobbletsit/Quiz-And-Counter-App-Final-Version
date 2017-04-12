package com.example.android.quizgameapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by gobov on 3/12/2017.
 */

public class ChildFragmAdapter extends FragmentPagerAdapter {

    private Context childContext;

    private QuestionRadioButton questionRadioButton, question2, question3, question4, question5;

    private QuestionCheckBox questionCheckBox;

    private QuestionEditText questionEditText;


    public ChildFragmAdapter(Context context, FragmentManager fm){
        super(fm);
        childContext = context;
    }

    // TO USE WHEN SETTING THE ADAPTER ON VIEW PAGER IN QUIZ APP
    public void setFragments(Context c){
        questionRadioButton = new QuestionRadioButton();
        question2 = new QuestionRadioButton();
        question3 = new QuestionRadioButton();
        question4 = new QuestionRadioButton();
        question5 = new QuestionRadioButton();
        questionCheckBox = new QuestionCheckBox();
        questionEditText = new QuestionEditText();

        /*
        CALLING THE CHANGE METHODS FROM QUESTION 1 FRAGMENT
        */

        // CHANGING THE QUESTION STRINGS FOR EACH FRAGMENT
        questionRadioButton.changeQuestion("1. Wrestling is a :");
        question2.changeQuestion("2. What is the minimum number of rounds every wrestling bout is consisted of ? ");
        question3.changeQuestion("3. How long does a wrestling round last ?");
        question4.changeQuestion("4. First wrestling Olympic medal was awarded on which Olympic games ?");
        question5.changeQuestion("5. Who won it ?");

        // CHANGING THE ANSWER STRINGS FOR EACH FRAGMENT
        questionRadioButton.changeStringArray("Boring sport", "Combat sport", "Team sport");
        question2.changeStringArray("2", "3", "5");
        question3.changeStringArray("5 min", "3 min", "2 min");
        question4.changeStringArray("1924. Paris", "1908. London", "1904. St. Louis");
        question5.changeStringArray("Edmond Sparon", "Aubert Cote", "Isidor Niflot");

        // CHANGING THE VALUES TO GET FROM SHARED PREFERENCES
        questionRadioButton.changeBooleans(1);
        question2.changeBooleans(2);
        question3.changeBooleans(3);
        question4.changeBooleans(4);
        question5.changeBooleans(5);

        // CHANGING THE LISTENERS FOR EACH FRAGMENT
        questionRadioButton.changeListener(1);
        question2.changeListener(2);
        question3.changeListener(3);
        question4.changeListener(4);
        question5.changeListener(5);
    }

    // GETTING THE FRAGMENTS TO BE PLACED IN THIS POSITIONS IN ADAPTER
    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;
        if (position == 0){
            frag = questionRadioButton;
        } else if (position == 1){
            frag = question2;
        } else if (position == 2){
            frag = question3;
        } else if (position == 3){
            frag = question4;
        } else if (position == 4){
            frag = question5;
        } else if (position == 5){
            frag = questionCheckBox;
        } else {
            frag = questionEditText;
        }
        return frag;
    }

    // OVERALL COUNT OF FRAGMENTS
    @Override
    public int getCount() {
        return 7;
    }

    // GETTING THE CHILD TITLES FROM STRING VALUES TO BE PLACED IN THIS POSITION IN INDICATOR
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0){
            return childContext.getString(R.string.fragment_question1);
        } else if (position == 1){
            return childContext.getString(R.string.fragment_question2);
        } else if (position == 2){
            return childContext.getString(R.string.fragment_question3);
        } else if (position == 3){
            return childContext.getString(R.string.fragment_question4);
        } else if (position == 4){
            return childContext.getString(R.string.fragment_question5);
        } else if (position == 5){
            return childContext.getString(R.string.fragment_question6);
        } else {
            return childContext.getString(R.string.fragment_question7);
        }
    }
}
