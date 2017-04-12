package com.example.android.quizgameapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by gobov on 3/23/2017.
 */

public class ScoreActivity extends AppCompatActivity {

    // QUESTION SCORE TEXT VIEWS
    private TextView question1_score, question2_score, question3_score, question4_score, question5_score, question6_score, question7_score;

    // TO STORE THE ANSWER AND MATCH VALUES FROM PREFERENCES FOR FINAL SCORE INT
    private int question1_answer_value, question2_answer_value, question3_answer_value, question4_answer_value, question5_answer_value, question6_answer_value_1, question6_answer_value_2, question6_answer_value, question7_answer_value, quizScoreInt, courtInt, finalInt;

    // TO STORE THE NAME VALUE FROM PREFERENCES
    private String nameValue, playerNameToUse;

    // FOR DISPLAYING THE SCORES
    private TextView quizScoreView, courtScoreView, finalScoreView, endMsgView;

    // FOR EASIER ACCESS OF DATA
    private ArrayList<String> endMessages;

    // TRY AGAIN BUTTON
    private Button tryAgainBtn;

    // TO USE THE VALUES
    SharedPreferences my_preferences;

    // METHOD FOR RESTARTING THE WHOLE APPLICATION
    private void restartApp() {
        Intent reloadMainActivity = new Intent(this, MainActivity.class);
        startActivity(reloadMainActivity);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_activity);


        // GETTING ACCESS TO WRITEN FILE VALUES FROM SHARED PREFERENCES
        my_preferences = PreferenceManager.getDefaultSharedPreferences(this);

        // GETTING THE STORED VALUES FROM SHARED PREFERENCES
        courtInt = my_preferences.getInt("set_value_a", 0);
        nameValue = my_preferences.getString("name_value", "");

        // FINDING THE TEXT VIEWVS TO DISPLAY THE SCORE
        question1_score = (TextView) findViewById(R.id.answer_value_1);
        question2_score = (TextView) findViewById(R.id.answer_value_2);
        question3_score = (TextView) findViewById(R.id.answer_value_3);
        question4_score = (TextView) findViewById(R.id.answer_value_4);
        question5_score = (TextView) findViewById(R.id.answer_value_5);
        question6_score = (TextView) findViewById(R.id.answer_value_6);
        question7_score = (TextView) findViewById(R.id.answer_value_7);

        // FINDING THE VIEWS TO DISPLAY SCORES AND MASSAGES
        quizScoreView = (TextView) findViewById(R.id.final_quiz_score_tV);
        courtScoreView = (TextView) findViewById(R.id.final_court_score_tV);
        finalScoreView = (TextView) findViewById(R.id.final_final_score_tv);
        endMsgView = (TextView) findViewById(R.id.end_msg_view);

        // GETTING THE VALUES FOR CORRECT ANSWERS
        question1_answer_value = my_preferences.getInt("value1", 0);
        question2_answer_value = my_preferences.getInt("value2", 0);
        question3_answer_value = my_preferences.getInt("value3", 0);
        question4_answer_value = my_preferences.getInt("value4", 0);
        question5_answer_value = my_preferences.getInt("value5", 0);
        question6_answer_value_1 = my_preferences.getInt("value6_1", 0);
        question6_answer_value_2 = my_preferences.getInt("value6_2", 0);
        question7_answer_value = my_preferences.getInt("text_answer_value", 0);

        question6_answer_value = question6_answer_value_1 + question6_answer_value_2;

        // SETTING THE VALUE OF QUESTION 6 ANSWER VALUE AND CALCULATING THE SCORE
        if (question6_answer_value == 2){
            question6_answer_value = 1;
            // CALCULATING CORRECT AND INCORRECT ANSWERS
            quizScoreInt = question1_answer_value + question2_answer_value + question3_answer_value + question4_answer_value + question5_answer_value + question6_answer_value + question7_answer_value;
        } else {
            question6_answer_value = 0;
            // CALCULATING CORRECT AND INCORRECT ANSWERS
            quizScoreInt = question1_answer_value + question2_answer_value + question3_answer_value + question4_answer_value + question5_answer_value + question6_answer_value + question7_answer_value;
        }

        // CALCULATING THE FINAL SCORE
        finalInt = quizScoreInt + courtInt;

        // SETTING THE TEXT VALUES TO THE TEXT VIEWS
        quizScoreView.setText(quizScoreInt + "/7");
        courtScoreView.setText(courtInt + "/2");
        finalScoreView.setText(finalInt + "/9");


        // CHECKING THE ANSWER VALUES TO SET THE TEXT ON EACH QUESTION SCORE VIEW
        if (question1_answer_value == 1){
            question1_score.setText(getString(R.string.correct));
            question1_score.setTextColor(Color.parseColor("#6695E7"));
        } else {
            question1_score.setText(getString(R.string.incorrect));
            question1_score.setTextColor(Color.BLACK);
        }

        if (question2_answer_value == 1){
            question2_score.setText(getString(R.string.correct));
            question2_score.setTextColor(Color.parseColor("#6695E7"));
        } else {
            question2_score.setText(getString(R.string.incorrect));
            question2_score.setTextColor(Color.BLACK);
        }

        if (question3_answer_value == 1){
            question3_score.setText(getString(R.string.correct));
            question3_score.setTextColor(Color.parseColor("#6695E7"));
        } else {
            question3_score.setText(getString(R.string.incorrect));
            question3_score.setTextColor(Color.BLACK);
        }

        if (question4_answer_value == 1){
            question4_score.setText(getString(R.string.correct));
            question4_score.setTextColor(Color.parseColor("#6695E7"));
        } else {
            question4_score.setText(getString(R.string.incorrect));
            question4_score.setTextColor(Color.BLACK);
        }

        if (question5_answer_value == 1){
            question5_score.setText(getString(R.string.correct));
            question5_score.setTextColor(Color.parseColor("#6695E7"));
        } else {
            question5_score.setText(getString(R.string.incorrect));
            question5_score.setTextColor(Color.BLACK);
        }

        if (question6_answer_value == 1){
            question6_score.setText(getString(R.string.correct));
            question6_score.setTextColor(Color.parseColor("#6695E7"));
        } else {
            question6_score.setText(getString(R.string.incorrect));
            question6_score.setTextColor(Color.BLACK);
        }

        if (question7_answer_value == 1){
            question7_score.setText(getString(R.string.correct));
            question7_score.setTextColor(Color.parseColor("#6695E7"));
        } else {
            question7_score.setText(getString(R.string.incorrect));
            question7_score.setTextColor(Color.BLACK);
        }


        // CHECKING THE NAME VALUE AND STORING IT IN A STRING TO USE IN ARRAY LIST
        if (nameValue.contentEquals("")){
            playerNameToUse = "Player";
        } else {
            playerNameToUse = nameValue;
        }

        // CREATING AN ARRAY LIST OF STRINGS AND ADDING MESSAGES TO IT
        endMessages = new ArrayList<String>();
        endMessages.add("Wow, we have a natural born wrestler here! " + playerNameToUse + " you're a future gold medal winner!");
        endMessages.add("Awesome work on  the mat " + playerNameToUse + "! Prove it's not luck and try again :D !");
        endMessages.add("Good job " + playerNameToUse + "! You should train just a bit more.");
        endMessages.add("Looks like you're almost a decent wrestler " + playerNameToUse + ".");
        endMessages.add("You'll be better next time " + playerNameToUse + ", practice makes perfect!");
        endMessages.add("You've got a long road ahead " + playerNameToUse + "!");
        endMessages.add(playerNameToUse + ", don't even bother trying again! Just kidding :D");
        endMessages.add("Maybe you should get some lessons first?");
        endMessages.add("C'mon " + playerNameToUse + ", you didn't even try! Shame on you!");

        // CHECKING THE VALUES TO DISPLAY END MASSAGES FROM THE LIST
        switch (finalInt){
            case 0: endMsgView.setText(endMessages.get(8));
                break;

            case 1: endMsgView.setText(endMessages.get(7));
                break;

            case 2: endMsgView.setText(endMessages.get(6));
                break;

            case 3: endMsgView.setText(endMessages.get(5));
                break;

            case 4: endMsgView.setText(endMessages.get(4));
                break;

            case 5: endMsgView.setText(endMessages.get(3));
                break;

            case 6: endMsgView.setText(endMessages.get(2));
                break;

            case 7: endMsgView.setText(endMessages.get(1));
                break;

            case 8: endMsgView.setText(endMessages.get(0));
                break;

            case 9: endMsgView.setText(endMessages.get(0));
                break;
        }

        /* FINDING THE BUTTON VIEW AND SETTING ON CLICK LISTENER ON IT
        TO RESTART THE WHOLE APP */
        tryAgainBtn = (Button) findViewById(R.id.try_again_btn);
        tryAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                restartApp();

            }
        });

    }
}
