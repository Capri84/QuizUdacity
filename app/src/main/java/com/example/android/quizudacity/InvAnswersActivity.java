package com.example.android.quizudacity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InvAnswersActivity extends AppCompatActivity implements View.OnClickListener {
    // Total number of questions
    private final int TOTAL_QUESTIONS_COUNT = 10;
    // Here we declare variables to store question number, player's answer, correct answer
    TextView playerAnswerTv, correctAnswerTv;
    // Here we declare variables to store strings "Question (current question number) out of (total number of questions)", "Your answer:...", "Correct answer:..."
    String plAnswer, corAnswer, answer9, answer10;
    // Here we declare Button
    Button buttonToMain;
    // This is an array of id's of question numbers
    int[] questionNumbers = {R.id.tv_question1_number, R.id.tv_question2_number, R.id.tv_question3_number, R.id.tv_question4_number, R.id.tv_question5_number,
            R.id.tv_question6_number, R.id.tv_question7_number, R.id.tv_question8_number, R.id.tv_question9_number, R.id.tv_question10_number};
    // This is an array of id's of correct answers
    int[] correctAnsId = {R.id.tv_q1_correct_answer, R.id.tv_q2_correct_answer, R.id.tv_q3_correct_answer, R.id.tv_q4_correct_answer, R.id.tv_q5_correct_answer,
            R.id.tv_q6_correct_answer, R.id.tv_q7_correct_answer, R.id.tv_q8_correct_answer};
    int[] playerAnsId = {R.id.tv_q1_player_answer, R.id.tv_q2_player_answer, R.id.tv_q3_player_answer, R.id.tv_q4_player_answer, R.id.tv_q5_player_answer,
            R.id.tv_q6_player_answer, R.id.tv_q7_player_answer, R.id.tv_q8_player_answer};
    String[] checkedAnswers = new String[8];
    String[] correctAnswers = new String[8];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inv_answers);
        buttonToMain = findViewById(R.id.btn_home);
        // Here we set the listener to the Button
        buttonToMain.setOnClickListener(this);
        showAnswers();
    }

    public void fillAnswers() {
        Intent invAnswersActivityIntent = getIntent();
        checkedAnswers = invAnswersActivityIntent.getStringArrayExtra("players_answer");
        correctAnswers = invAnswersActivityIntent.getStringArrayExtra("correct_answer");
        answer9 = invAnswersActivityIntent.getStringExtra("answer9");
        answer10 = invAnswersActivityIntent.getStringExtra("answer10");
    }

    // This method sets question numbers, shows player's answers and correct answers
    public void showAnswers() {
        fillAnswers();
        int numberOfQuestion = 1;
        for (int i = 0; i < TOTAL_QUESTIONS_COUNT; i++) {
            String caption = getString(R.string.question) + " " + numberOfQuestion + " "
                    + getString(R.string.out_of) + " " + TOTAL_QUESTIONS_COUNT;
            ((TextView) findViewById(questionNumbers[i])).setText(caption);
            numberOfQuestion++;
        }

        for (int j = 0; j < playerAnsId.length; j++) {
            playerAnswerTv = findViewById(playerAnsId[j]);
            correctAnswerTv = findViewById(correctAnsId[j]);
            plAnswer = getString(R.string.your_answer) + " " + checkedAnswers[j];
            corAnswer = getString(R.string.inv_correct_answer) + " " + correctAnswers[j];
            playerAnswerTv.setText(plAnswer);
            correctAnswerTv.setText(corAnswer);
            if (checkedAnswers[j].equals(correctAnswers[j])) {
                playerAnswerTv.setTextColor(getResources().getColor(R.color.correct_answer_color));
            } else {
                playerAnswerTv.setTextColor(getResources().getColor(R.color.wrong_answer_color));
            }
        }
        playerAnswerTv = findViewById(R.id.tv_q9_player_answer);
        correctAnswerTv = findViewById(R.id.tv_q9_correct_answer);
        plAnswer = getString(R.string.your_answer) + " " + answer9;
        corAnswer = getString(R.string.inv_correct_answer) + " " + getResources().getText(R.string.q_9_var_1);
        playerAnswerTv.setText(plAnswer);
        correctAnswerTv.setText(corAnswer);
        if (answer9.equals(getResources().getText(R.string.q_9_var_1))) {
            playerAnswerTv.setTextColor(getResources().getColor(R.color.correct_answer_color));
        } else {
            playerAnswerTv.setTextColor(getResources().getColor(R.color.wrong_answer_color));
        }
        playerAnswerTv = findViewById(R.id.tv_q10_player_answer);
        correctAnswerTv = findViewById(R.id.tv_q10_correct_answer);
        plAnswer = getString(R.string.your_answer) + " " + answer10;
        corAnswer = getString(R.string.inv_correct_answer) + "\n" + (getString(R.string.q_10_var_1) + "\n" + getString(R.string.q_10_var_4));
        playerAnswerTv.setText(plAnswer);
        correctAnswerTv.setText(corAnswer);
        if (answer10.equals((getString(R.string.q_10_var_1) + "\n" + getString(R.string.q_10_var_4)))) {
            playerAnswerTv.setTextColor(getResources().getColor(R.color.correct_answer_color));
        } else {
            playerAnswerTv.setTextColor(getResources().getColor(R.color.wrong_answer_color));
        }
    }

    // This method is called by clicking on Main screen button. It returns the user to the Main screen.
    public void mainActivity() {
        Intent MainActivityIntent = new Intent(this, MainActivity.class);
        startActivity(MainActivityIntent);
        this.finish();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_home:
                mainActivity();
                break;
        }
    }
}