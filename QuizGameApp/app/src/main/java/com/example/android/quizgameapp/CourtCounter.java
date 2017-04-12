package com.example.android.quizgameapp;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.renderscript.Sampler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static android.R.attr.fragment;
import static android.R.attr.state_accelerated;
import static android.R.id.button1;
import static android.os.Build.VERSION_CODES.N;
import static android.support.v7.widget.AppCompatDrawableManager.get;
import static com.example.android.quizgameapp.MainFragmentActivity.mInstanceMain;
import static com.example.android.quizgameapp.R.id.container;
import static com.example.android.quizgameapp.R.id.start_rnd_1;


/**
 * A simple {@link Fragment} subclass.
 */
public class CourtCounter extends Fragment {

    // SCORE FOR TEAM A
    private int scoreTeamA = 0;

    // SCORE FOR TEAM B
    private int scoreTeamB = 0;

    // DEFAULT SCORE FOR TEAM A
    private int aSet = 0;

    // DEFAULT SCORE FOR TEAM B
    private int bSet = 0;

    // TO STORE THE VALUE OF PLAYER NAME
    private String stringNameValue;

    // FOR DISPLAYING THE COUNTER TIME AND THE NAME OF THE PLAYER
    private TextView counterTextView, playerNameTextView, setViewA, setViewB;

    // BUTTONS FOR TEAM A
    private Button aTakedown_2, aReversal_2, aEscape_1, aNearFall_2, aNearFall_3, aPenalty;

    // BUTTONS FOR TEAM B
    private Button bTakedown_2, bReversal_2, bEscape_1, bNearFall_2, bNearFall_3, bPenalty;

    // TOGGLEBUTTONS FOR ROUNDS
    private ToggleButton round1btn, round2btn, round3btn;

    // SUBMIT SCORE BUTTON
    private Button submitFinalScore;

    // COUNTDOWN TIMER
    private CountDownTimer cTimer;

    // MEDIA PLAYER TO SOUND THE BEGINNING AND THE END OF THE ROUND
    private MediaPlayer mp;

    // FOR DISPLAYING WHO WINS IF THE TIMER RUNS OUT
    private Toast counterEnd;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("privScoreTeamA", scoreTeamA);
        outState.putInt("privScoreTeamB", scoreTeamB);
        outState.putInt("privSetScoreA", aSet);
        outState.putInt("privSetScoreB", bSet);
    }

    @Override
    public void onResume() {
        super.onResume();
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
        displaySetA(aSet);
        displaySetB(bSet);
        setRetainInstance(true);
    }


    public CourtCounter() {
        // Required empty public constructor
    }


    // TEAM A - BEGINS THE CALCULATION METHODS FOR ADDING POINTS

    // ADD 1 POINT FOR TEAM A SCORE
    public void addOneForTeamA(View v) {

        if(scoreTeamA >= 14 ){
            return;
        } else if (scoreTeamA != 13) {
            scoreTeamA = scoreTeamA + 1;
        }else{
            scoreTeamA = scoreTeamA + 1;
            aSet += 1;
            displaySetA(aSet);
        }
        displayForTeamA(scoreTeamA);
    }

    // ADD 2 POINTS FOR TEAM A SCORE
    public void addTwoForTeamA(View v) {
        if (scoreTeamA >= 14){
            return;
        }

        if (scoreTeamA <= 13){
            scoreTeamA += 2;
        }

        if(scoreTeamA >= 14){
            if (aSet <= 1){
                aSet += 1;
            } else if (aSet == 3){
                return;
            }
            displaySetA(aSet);
        }
        displayForTeamA(scoreTeamA);
    }

    // ADD 3 POINTS FOR TEAM A SCORE
    public void addThreeForTeamA(View v) {
        if (scoreTeamA >= 14){
            return;
        }

        if (scoreTeamA <= 13){
            scoreTeamA += 3;
        }

        if(scoreTeamA >= 14){
            if (aSet <= 1){
                aSet += 1;
            } else if (aSet == 3){
                return;
            }
            displaySetA(aSet);
        }
        displayForTeamA(scoreTeamA);
    }

    // TEAM A - ENDS THE CALCULATION METHODS FOR ADDING POINTS


    // TEAM B - BEGINS THE CALCULATION METHODS FOR ADDING POINTS

    // ADDS 1 POINT FOR TEAM B SCORE
    public void addOneForTeamB(View v) {
        if(scoreTeamB >= 14 ){
            return;
        } else if (scoreTeamB != 13) {
            scoreTeamB = scoreTeamB + 1;
        } else{
            scoreTeamB = scoreTeamB + 1;
            bSet += 1;
            displaySetB(bSet);
        }
        displayForTeamB(scoreTeamB);
    }

    // ADDS 2 POINTS FOR TEAM B SCORE
    public void addTwoForTeamB(View v) {
        if (scoreTeamB >= 14){
            return;
        }

        if (scoreTeamB <= 13){
            scoreTeamB += 2;
        }

        if(scoreTeamB >= 14){
            if (bSet <= 1){
                bSet += 1;
            } else if (bSet == 3){
                return;
            }
            displaySetB(bSet);
        }
        displayForTeamB(scoreTeamB);
    }

    // ADDS 3 POINTS FOR TEAM B SCORE
    public void addThreeForTeamB(View v) {
        if (scoreTeamB >= 14){
            return;
        }

        if (scoreTeamB <= 13){
            scoreTeamB += 3;
        }

        if(scoreTeamB >= 14){
            if (bSet <= 1){
                bSet += 1;
            } else if (bSet == 3){
                return;
            }
            displaySetB(bSet);
        }
        displayForTeamB(scoreTeamB);
    }

    // TEAM B - ENDS THE CALCULATION METHODS FOR ADDING POINTS


    // ROUND RESET METHOD FOR BOTH TEAMS
    public void roundReset2 (View v) {
        scoreTeamA = 0;
        scoreTeamB = 0;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
    }

    // DISPLAYS THE SCORE FOR TEAM A
    public void displayForTeamA(int score) {
        TextView scoreViewA = (TextView) getActivity().findViewById(R.id.team_a_score);
        scoreViewA.setText(String.valueOf(score));
    }

    // DISPLAYS THE SCORE FOR TEAM B
    public void displayForTeamB(int score) {
        TextView scoreViewB = (TextView) getActivity().findViewById(R.id.team_b_score);
        scoreViewB.setText(String.valueOf(score));
    }

    // DISPLAYS THE SET SCORE FOR TEAM A
    public void displaySetA (int scoreSetA) {
        setViewA = (TextView) getActivity().findViewById(R.id.display_set_a);
        setViewA.setText(String.valueOf(scoreSetA));
        SharedPreferences my_preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = my_preferences.edit();


        if (scoreSetA == 2){
            editor.putInt("set_value_a", 2);
            Toast winnerA = Toast.makeText(getContext(), "You won the match!!", Toast.LENGTH_SHORT);
            winnerA.show();
            editor.apply();
        }
    }

    // DISPLAYS THE SET SCORE FOR TEAM B
    public void displaySetB (int scoreSet) {
        setViewB = (TextView) getActivity().findViewById(R.id.display_set_b);
        setViewB.setText(String.valueOf(scoreSet));
        SharedPreferences my_preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor2 = my_preferences.edit();


        if (scoreSet == 2){
            editor2.putInt("set_value_b", 2);
            editor2.apply();
            Toast winnerB = Toast.makeText(getContext(), "You lost, Nikola won the match!!", Toast.LENGTH_SHORT);
            winnerB.show();
        }
    }

    // FOR DETERMINING WHETHER THE TIMER SHOULD STOP
    public boolean isPaused (){
        if (scoreTeamA >= 14 || scoreTeamB >= 14){
            return true;
        } else {
            return false;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // INFLATE THE LAYOUT FOR THIS FRAGMENT
        View rootViewCourtCounter = inflater.inflate(R.layout.fragment_court_counter, container, false);

        setRetainInstance(true);

        // TO RESTORE DA STATE OF SCORES UPON ROTATING
        if (savedInstanceState != null) {
            scoreTeamA = savedInstanceState.getInt("privScoreTeamA", scoreTeamA);
            scoreTeamB = savedInstanceState.getInt("privScoreTeamB", scoreTeamB);
            aSet = savedInstanceState.getInt("privSetScoreA", aSet);
            bSet = savedInstanceState.getInt("privSetScoreB", bSet);
        }

        // GETTING ACCESS TO WRITEN FILE VALUES FROM SHARED PREFERENCES
        final SharedPreferences my_preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        // FINDING THE VIEW TO DISPLAY PLAYER'S NAME
        playerNameTextView = (TextView) rootViewCourtCounter.findViewById(R.id.name_textView);

        // GETTING THE STRING VALUE FROM SHARED PREFERENCES
        stringNameValue = my_preferences.getString("name_value", "");

        // CHECKING IF THERE IS ANY NAME INPUT
        if (stringNameValue.contentEquals("")){
            playerNameTextView.setText("Player");
        } else {
            playerNameTextView.setText(stringNameValue);
        }

        // FINDING THE VIEW TO DISPLAY THE COUNTER TIME
        counterTextView = (TextView) rootViewCourtCounter.findViewById(R.id.counter_text_view);

        // INSTANTIATING THE MEDIA PLAYER
        mp = MediaPlayer.create(getContext(), (R.raw.bell));

        // FINDING THE VIEWS FOR BUTTONS TEAM A
        aTakedown_2 = (Button) rootViewCourtCounter.findViewById(R.id.A_takedown_2_btn);
        aReversal_2 = (Button) rootViewCourtCounter.findViewById(R.id.A_reversal_2_btn);
        aEscape_1 = (Button) rootViewCourtCounter.findViewById(R.id.A_escape_1_btn);
        aNearFall_2 = (Button) rootViewCourtCounter.findViewById(R.id.A_nearfall_2_btn);
        aNearFall_3 = (Button) rootViewCourtCounter.findViewById(R.id.A_nearfall_3_btn);
        aPenalty = (Button) rootViewCourtCounter.findViewById(R.id.A_penalty_btn);

        // CREATING AN ARRAY LIST OF BUTTONS TEAM A FOR EASIER ACCESS TO DATA
        final ArrayList<Button> buttonsTeamA = new ArrayList<Button>();
        buttonsTeamA.add(aTakedown_2);
        buttonsTeamA.add(aReversal_2);
        buttonsTeamA.add(aEscape_1);
        buttonsTeamA.add(aNearFall_2);
        buttonsTeamA.add(aNearFall_3);
        buttonsTeamA.add(aPenalty);

        // SETTING THE TEXT IN TEAM A FOR EACH BUTTON IN A GROUP
        buttonsTeamA.get(0).setText("Takedown + 2");
        buttonsTeamA.get(1).setText("Reversal + 2");
        buttonsTeamA.get(2).setText("Escape +1");
        buttonsTeamA.get(3).setText("Near Fall(2 sec) + 2");
        buttonsTeamA.get(4).setText("Near fall(5 sec) + 3");
        buttonsTeamA.get(5).setText("Penalty + 1 (for b)");


        // FINDING THE VIEWS FOR BUTTONS TEAM B
        bTakedown_2 = (Button) rootViewCourtCounter.findViewById(R.id.B_takedown_2_btn);
        bReversal_2 = (Button) rootViewCourtCounter.findViewById(R.id.B_reversal_2_btn);
        bEscape_1 = (Button) rootViewCourtCounter.findViewById(R.id.B_escape_1_btn);
        bNearFall_2 = (Button) rootViewCourtCounter.findViewById(R.id.B_nearfall_2_btn);
        bNearFall_3 = (Button) rootViewCourtCounter.findViewById(R.id.B_nearfall_3_btn);
        bPenalty = (Button) rootViewCourtCounter.findViewById(R.id.B_penalty_btn);

        // CREATING AN ARRAY LIST OF BUTTONS TEAM B FOR EASIER ACCESS TO DATA
        final ArrayList<Button> buttonsTeamB = new ArrayList<Button>();
        buttonsTeamB.add(bTakedown_2);
        buttonsTeamB.add(bReversal_2);
        buttonsTeamB.add(bEscape_1);
        buttonsTeamB.add(bNearFall_2);
        buttonsTeamB.add(bNearFall_3);
        buttonsTeamB.add(bPenalty);

        // SETTING THE TEXT FOR TEAM B FOR EACH BUTTON IN A GROUP
        buttonsTeamB.get(0).setText("Takedown + 2");
        buttonsTeamB.get(1).setText("Reversal + 2");
        buttonsTeamB.get(2).setText("Escape +1");
        buttonsTeamB.get(3).setText("Near Fall(2 sec) + 2");
        buttonsTeamB.get(4).setText("Near fall(5 sec) + 3");
        buttonsTeamB.get(5).setText("Penalty + 1 (for a)");

        cTimer = new CountDownTimer(120000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (isPaused()) {
                    cancel();
                } else {
                    counterTextView.setText("" + String.format("%d : %d ",
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                    counterTextView.setTextSize(35);
                }
            }

            // ACTIONS TO DO ON TIMER FINISH
            @Override
            public void onFinish() {
                if (stringNameValue.contentEquals("")){
                    stringNameValue = "Player";
                    if (scoreTeamA > scoreTeamB) {
                        if (aSet <= 1){
                            aSet = aSet + 1;
                            displaySetA(aSet);
                        }
                        counterEnd = Toast.makeText(getContext(), (stringNameValue + " wins the round!"), Toast.LENGTH_LONG);
                        counterEnd.show();
                    } else if (scoreTeamA < scoreTeamB) {
                        if (bSet <= 1){
                            bSet = bSet + 1;
                            displaySetB(bSet);
                        }
                        counterEnd = Toast.makeText(getContext(), "Nikola wins the round!", Toast.LENGTH_LONG);
                        counterEnd.show();
                    } else if (scoreTeamA > scoreTeamB && aSet > bSet) {
                        counterEnd = Toast.makeText(getContext(), (stringNameValue + " wins the match due to overall score."), Toast.LENGTH_LONG);
                        counterEnd.show();
                    } else if (scoreTeamA < scoreTeamB && aSet < bSet) {
                        counterEnd = Toast.makeText(getContext(), "Nikola wins the match due to overall score.", Toast.LENGTH_LONG);
                        counterEnd.show();
                    } else {
                        counterEnd = Toast.makeText(getContext(), "Tied Round", Toast.LENGTH_LONG);
                        counterEnd.show();
                    }
                } else {
                    if (scoreTeamA > scoreTeamB) {
                        if (aSet <= 1){
                            aSet = aSet + 1;
                            displaySetA(aSet);
                        }
                        counterEnd = Toast.makeText(getContext(), (stringNameValue + " wins the round!"), Toast.LENGTH_LONG);
                        counterEnd.show();
                    } else if (scoreTeamA < scoreTeamB) {
                        if (bSet <= 1){
                            bSet = bSet + 1;
                            displaySetB(bSet);
                        }
                        counterEnd = Toast.makeText(getContext(), "Nikola wins the round!", Toast.LENGTH_LONG);
                        counterEnd.show();
                    } else if (scoreTeamA > scoreTeamB && aSet > bSet) {
                        counterEnd = Toast.makeText(getContext(), (stringNameValue + " wins the match due to overall score."), Toast.LENGTH_LONG);
                        counterEnd.show();
                    } else if (scoreTeamA < scoreTeamB && aSet < bSet) {
                        counterEnd = Toast.makeText(getContext(), "Nikola wins the match due to overall score.", Toast.LENGTH_LONG);
                        counterEnd.show();
                    } else {
                        counterEnd = Toast.makeText(getContext(), "Tied Round", Toast.LENGTH_LONG);
                        counterEnd.show();
                    }
                }
            }
        };

        // FINDING THE VIEWS FOR TOGGLEBUTTONS
        round1btn = (ToggleButton) rootViewCourtCounter.findViewById(R.id.start_rnd_1);
        round2btn = (ToggleButton) rootViewCourtCounter.findViewById(R.id.start_rnd_2);
        round3btn = (ToggleButton) rootViewCourtCounter.findViewById(R.id.start_rnd_3);

        // CREATING AN ARRAY LIST OF TOGGLEBUTTONS FOR EASIER DATA HANDLING
        final ArrayList <ToggleButton> roundButtons = new ArrayList<ToggleButton>();
        roundButtons.add(round1btn);
        roundButtons.add(round2btn);
        roundButtons.add(round3btn);

        // DISABLING 2 BUTTONS FOR ON CREATE
        roundButtons.get(1).setEnabled(false);
        roundButtons.get(2).setEnabled(false);

        // SETTING ON CLICK LISTENER FOR ROUND BUTTONS TO START TIMERS AND RESET SCORE
        for (int i = 0; i < roundButtons.size(); i++){
            roundButtons.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cTimer.start();
                    mp.start();
                    roundReset2(getView());
                }
            });
        }

        // SETTING THE ON CHECKED CHANGED LISTENER FOR ROUND BUTTONS
        for (int i = 0; i < roundButtons.size(); i++){
            roundButtons.get(i).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {

                        // SETTING THE ON CLICK LISTENERS FOR EACH TEAM A BUTTON IN A GROUP
                        for (int i = 0; i < buttonsTeamA.size(); i++) {
                            buttonsTeamA.get(i).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (buttonsTeamA.get(0).isPressed()) {
                                        addTwoForTeamA(getView());
                                    } else if (buttonsTeamA.get(1).isPressed()) {
                                        addTwoForTeamA(getView());
                                    } else if (buttonsTeamA.get(2).isPressed()) {
                                        addOneForTeamA(getView());
                                    } else if (buttonsTeamA.get(3).isPressed()) {
                                        addTwoForTeamA(getView());
                                    } else if (buttonsTeamA.get(4).isPressed()) {
                                        addThreeForTeamA(getView());
                                    } else {
                                        addOneForTeamB(getView());
                                    }
                                }
                            });
                        }

                        // SETTING THE ON CLICK LISTENER TO EACH TEAM B BUTTON IN A GROUP
                        for (int i = 0; i < buttonsTeamB.size(); i++) {
                            buttonsTeamB.get(i).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (buttonsTeamB.get(0).isPressed()) {
                                        addTwoForTeamB(getView());
                                    } else if (buttonsTeamB.get(1).isPressed()) {
                                        addTwoForTeamB(getView());
                                    } else if (buttonsTeamB.get(2).isPressed()) {
                                        addOneForTeamB(getView());
                                    } else if (buttonsTeamB.get(3).isPressed()) {
                                        addTwoForTeamB(getView());
                                    } else if (buttonsTeamB.get(4).isPressed()) {
                                        addThreeForTeamB(getView());
                                    } else {
                                        addOneForTeamA(getView());
                                    }
                                }
                            });
                        }

                        // ENABLING AND DISABLING BUTTONS ON CHEKS
                        if (roundButtons.get(0).isChecked()){
                            roundButtons.get(0).setEnabled(false);
                            roundButtons.get(1).setEnabled(true);
                        }

                        if (roundButtons.get(1).isChecked()){
                            roundButtons.get(1).setEnabled(false);
                            roundButtons.get(2).setEnabled(true);
                        }

                        if (roundButtons.get(2).isChecked()){
                            roundButtons.get(2).setEnabled(false);
                        }

                    }
                }
            });
        }



        // FINDING AND SETTING THE BUTTON VIEW FOR SUBMITING SCORE
        submitFinalScore = (Button) rootViewCourtCounter.findViewById(R.id.submit_final_score);
        submitFinalScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scoreActivityIntent = new Intent(getActivity(), ScoreActivity.class);
                startActivity(scoreActivityIntent);
            }
        });

        return rootViewCourtCounter;
    }

}


