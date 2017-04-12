package com.example.android.quizgameapp;

import android.graphics.Color;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

/**
 * Created by gobov on 3/12/2017.
 */

public class QuestionAndAnswers {

    private String myQuestion;

    private String myQuestionCheck;

    private RadioButton mBtn1;

    private RadioButton mBtn2;

    private RadioButton mBtn3;

    private  RadioGroup mBtnGroup;

    private CheckBox mCheckBtn1;

    private CheckBox mCheckBtn2;

    private CheckBox mCheckBtn3;

    private LinearLayout mLinearCheck;


    public QuestionAndAnswers(String question, ArrayList<String> answers, RadioButton btn1, RadioButton btn2, RadioButton btn3, RadioGroup grupa){

        myQuestion = question;

        btn1.setText(answers.get(0));
        btn1.setTextColor(Color.parseColor("#303F9F"));
        btn1.setTextSize(18);
        btn1.setPadding(20, 30, 20, 30);
        mBtn1 = btn1;

        btn2.setText(answers.get(1));
        btn2.setTextColor(Color.parseColor("#303F9F"));
        btn2.setTextSize(18);
        btn2.setPadding(20, 30, 20, 30);
        mBtn2 = btn2;

        btn3.setText(answers.get(2));
        btn3.setTextColor(Color.parseColor("#303F9F"));
        btn3.setTextSize(18);
        btn3.setPadding(20, 30, 20, 30);
        mBtn3 = btn3;

        grupa.addView(btn1);
        grupa.addView(btn2);
        grupa.addView(btn3);
        mBtnGroup = grupa;
    }

    public String getMyQuestion(){
        return myQuestion;
    }

    public RadioButton getMyBtn1(){return mBtn1;}

    public RadioButton getMyBtn2(){return mBtn2;}

    public RadioButton getMyBtn3(){return mBtn3;}

    public RadioGroup getMyGroup() {
        return mBtnGroup;
    }


    public QuestionAndAnswers(String question, ArrayList<String> answers, CheckBox checkBtn1, CheckBox checkBtn2, CheckBox checkBtn3, LinearLayout linearCheck){

        myQuestionCheck = question;

        checkBtn1.setText(answers.get(0));
        checkBtn1.setTextColor(Color.parseColor("#303F9F"));
        checkBtn1.setTextSize(18);
        checkBtn1.setPadding(20, 30, 20, 30);
        mCheckBtn1 = checkBtn1;

        checkBtn2.setText(answers.get(1));
        checkBtn2.setTextColor(Color.parseColor("#303F9F"));
        checkBtn2.setTextSize(18);
        checkBtn2.setPadding(20, 30, 20, 30);
        mCheckBtn2 = checkBtn2;

        checkBtn3.setText(answers.get(2));
        checkBtn3.setTextColor(Color.parseColor("#303F9F"));
        checkBtn3.setTextSize(18);
        checkBtn3.setPadding(20, 30, 20, 30);
        mCheckBtn3 = checkBtn3;

        linearCheck.addView(checkBtn1);
        linearCheck.addView(checkBtn2);
        linearCheck.addView(checkBtn3);
        mLinearCheck = linearCheck;

    }

    public String getMyQuestionCheck(){return myQuestionCheck;}

    public CheckBox getmCheckBtn1(){return mCheckBtn1;}

    public CheckBox getmCheckBtn2(){return mCheckBtn2;}

    public CheckBox getmCheckBtn3(){return mCheckBtn3;}


}
