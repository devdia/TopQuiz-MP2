package com.dia.david.topquiz.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dia.david.topquiz.R;
import com.dia.david.topquiz.model.Question;
import com.dia.david.topquiz.model.QuestionBank;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mQuestionTextView;
    private Button mAnswerButton1, mAnswerButton2, mAnswerButton3, mAnswerButton4;
    private QuestionBank mQuestionBank;
    private Question mCurrentQuestion;
    private int mScore;
    private int mNumberOfQuestions;
    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";
    private boolean mEnableTouchEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mScore = 0;
        mNumberOfQuestions = 5;
        mEnableTouchEvents = true;

        mQuestionBank = this.generateQuestions();
        mQuestionTextView = (TextView) findViewById(R.id.activity_game_question_text);
        mAnswerButton1 = (Button) findViewById(R.id.activity_game_answer1_btn);
        mAnswerButton2 = (Button) findViewById(R.id.activity_game_answer2_btn);
        mAnswerButton3 = (Button) findViewById(R.id.activity_game_answer3_btn);
        mAnswerButton4 = (Button) findViewById(R.id.activity_game_answer4_btn);


        mAnswerButton1.setTag(0);
        mAnswerButton2.setTag(1);
        mAnswerButton3.setTag(2);
        mAnswerButton4.setTag(3);

        mAnswerButton1.setOnClickListener(this);
        mAnswerButton2.setOnClickListener(this);
        mAnswerButton3.setOnClickListener(this);
        mAnswerButton4.setOnClickListener(this);

        mCurrentQuestion = mQuestionBank.getQuestion();
        this.displayQuestion(mCurrentQuestion);


    }


    @Override
    public void onClick(View v) {
        int responseIndex = (int) v.getTag();
        if (responseIndex == mCurrentQuestion.getAnswerIndex()){
            Toast.makeText(this,"Correct answer", Toast.LENGTH_SHORT).show();
            mScore++;
        }else {
            Toast.makeText(this, "Wrong answer", Toast.LENGTH_SHORT).show();
        }
        mEnableTouchEvents = false;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mEnableTouchEvents = true;

                if (--mNumberOfQuestions ==0){
                    endGame();

                }else {
                    mCurrentQuestion = mQuestionBank.getQuestion();
                    displayQuestion(mCurrentQuestion);
                }

            }
        },2000);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mEnableTouchEvents && super.dispatchTouchEvent(ev);
    }

    private void endGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Well done")
                .setMessage("Your score is "+mScore)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                })
                .create()
                .show();
    }

    private void displayQuestion(final Question question) {
        mQuestionTextView.setText(question.getQuestion());
        mAnswerButton1.setText(question.getChoiceList().get(0));
        mAnswerButton2.setText(question.getChoiceList().get(1));
        mAnswerButton3.setText(question.getChoiceList().get(2));
        mAnswerButton4.setText(question.getChoiceList().get(3));
    }



    private QuestionBank generateQuestions(){
        Question question1 = new Question("Kingdom Of Sarawak is founded by who and in what year?",
                Arrays.asList("Sir James Brooke in 1842","Charles Vyner Brooke in 1959","Sir Adam Brooke in 1730",
                        "Henry Vyner Brooke in 1835"),0);

        Question question2 = new Question("On December 10 1941, what are the two British battleship that were sunk by the Japanese?",
                Arrays.asList("HMS Winston and HMS Repulse","HMS Prince Of Wales and HMS John Mendela",
                        "HMS Prince Of Wales and HMS Repulse","HMS Birmingham and HMS Manchester"),2);

        Question question3 = new Question("What is the Kempeitai are responsible of?",
                Arrays.asList("Giving food to the local","Managing local news paper","Act as a cannon fodder in the frontline",
                        "Physcological operations and propaganda"),3);

        Question question4 = new Question("When did the Malayan Union became existence and who is the governor?",
                Arrays.asList("April 10 1946, Sir Howard Gent","April 1 1946, Sir Edward Gent",
                        "May 1 1946, Sir Johnny Gent","May 30 1946, Sir Henry Gent"),1);

        Question question5 = new Question("What is the objectives of the Briggs Plan?",
                Arrays.asList("To cut off the supply of communist among the support of the population",
                        "To lure the communist into the urban area and kill them",
                        "To make the communist surrender by providing them necessary supply",
                        "To imprisont any civillian who collaborate with the communist"),0);

        Question question6 = new Question("The Federation Of Malaysia that consist of Sabah, " +
                "Sarawak and  Singapore was officially declared on?",
                Arrays.asList("August 18 1957","August 31 1963","August 1 1964","August 29 1962"),1);

        Question question7 = new Question("What is the below mega project that was build under the leadership of " +
                "Dr Mahathir Bin Mohammed?",
                Arrays.asList("Empire States Building","Petronas Twin Tower","Maybank Tower","Millenium Tower"),1);

        Question question8 = new Question("Who is the British High Commisioner that had been killed by MNLA?",
                Arrays.asList("Sir Jerry Gurney","Sir Thomas Kerry","Sir Martin Jeremy","Sir Henry Gurney"),3);

        Question question9 = new Question("Why Malayan Union received strong opposion from the Malays?",
                Arrays.asList("The methods used by Sir Harold Michael to gain approval from the Sultans and cause Sultans to lose power",
                        "Malayan Union plan was to expel the Malay to the rural areas",
                        "Malayan Union plan was to cut off the suppy of local citizens and supply the British Crown Colony",
                        "Malayan Union prevented Malays from leaving the country freely"),0);

        Question question10 = new Question("The Malayan Union was replaced by what?",
                Arrays.asList("Federation Of The Union","Federation Of United","Federation Of Malaya","Federation Of The Malayan People"),2);

        return new QuestionBank(Arrays.asList(question1,
                question2,
                question3,
                question4,
                question5,
                question6,
                question7,
                question8,
                question9,
                question10));
    }
}
