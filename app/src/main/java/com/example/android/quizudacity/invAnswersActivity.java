package com.example.android.quizudacity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class invAnswersActivity extends AppCompatActivity implements View.OnClickListener {
    // Number of the current question
    int currentQuestion = 1;
    // Total number of questions
    int totalNumberOfQuestions = 10;
    // Here we declare variables to store question number, player's answer, correct answer
    TextView question_number, player_answer_tv, correct_answer_tv;
    // Here we declare variables to store strings "Question (current question number) out of (total number of questions)", "Your answer:...", "Correct answer:..."
    String qnum, planswer, coranswer, answer9, answer10;
    // Here we declare Button
    Button buttonToMain;
    // This is an array of id's of question numbers
    int[] question_numbers = {R.id.inv_question1_number, R.id.inv_question2_number, R.id.inv_question3_number, R.id.inv_question4_number, R.id.inv_question5_number,
            R.id.inv_question6_number, R.id.inv_question7_number, R.id.inv_question8_number, R.id.inv_question9_number, R.id.inv_question10_number};
    // This is an array of id's of correct answers
    int[] correct_ans_id = {R.id.inv_q1_correct_answer, R.id.inv_q2_correct_answer, R.id.inv_q3_correct_answer, R.id.inv_q4_correct_answer, R.id.inv_q5_correct_answer,
            R.id.inv_q6_correct_answer, R.id.inv_q7_correct_answer, R.id.inv_q8_correct_answer};
    int[] player_ans_id = {R.id.inv_q1_player_answer, R.id.inv_q2_player_answer, R.id.inv_q3_player_answer, R.id.inv_q4_player_answer, R.id.inv_q5_player_answer,
            R.id.inv_q6_player_answer, R.id.inv_q7_player_answer, R.id.inv_q8_player_answer};
    String[] checked_answers = new String[8];
    String[] correct_answers = new String[8];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inv_answers);
        // Here we initialize correct answer's images
        buttonToMain = (Button) findViewById(R.id.button_to_main);
        // Here we set the listener to the Button
        buttonToMain.setOnClickListener(this);
        showAnswers();
    }

    public void fillAnswers() {
        Intent invAnswersActivity = getIntent();
        checked_answers = invAnswersActivity.getStringArrayExtra("players_answer");
        correct_answers = invAnswersActivity.getStringArrayExtra("correct_answer");
        answer9 = invAnswersActivity.getStringExtra("answer9");
        answer10 = invAnswersActivity.getStringExtra("answer10");
    }

    // This method sets question numbers, shows player's answers and correct answers
    public void showAnswers() {
        fillAnswers();
        for (int i = 0; i < question_numbers.length; i++) {
            question_number = findViewById(question_numbers[i]);
            qnum = getString(R.string.questionNumber, currentQuestion, totalNumberOfQuestions);
            question_number.setText(qnum);
            currentQuestion++;
        }
        for (int j = 0; j < player_ans_id.length; j++) {
            player_answer_tv = findViewById(player_ans_id[j]);
            correct_answer_tv = findViewById(correct_ans_id[j]);
            planswer = getString(R.string.yourAnswer, checked_answers[j]);
            coranswer = getString(R.string.invCorrectAnswer, correct_answers[j]);
            player_answer_tv.setText(planswer);
            correct_answer_tv.setText(coranswer);
            if (checked_answers[j].equals(correct_answers[j])) {
                player_answer_tv.setTextColor(getResources().getColor(R.color.correct_answer_color));
            } else {
                player_answer_tv.setTextColor(getResources().getColor(R.color.wrong_answer_color));
            }
        }
        player_answer_tv = findViewById(R.id.inv_q9_player_answer);
        correct_answer_tv = findViewById(R.id.inv_q9_correct_answer);
        planswer = getString(R.string.yourAnswer, answer9);
        coranswer = getString(R.string.invCorrectAnswer, getResources().getText(R.string.inv_q9_var_1));
        player_answer_tv.setText(planswer);
        correct_answer_tv.setText(coranswer);
        if (answer9.equals(getResources().getText(R.string.inv_q9_var_1))) {
            player_answer_tv.setTextColor(getResources().getColor(R.color.correct_answer_color));
        } else {
            player_answer_tv.setTextColor(getResources().getColor(R.color.wrong_answer_color));
        }
        player_answer_tv = findViewById(R.id.inv_q10_player_answer);
        correct_answer_tv = findViewById(R.id.inv_q10_correct_answer);
        planswer = getString(R.string.yourAnswer, answer10);
        coranswer = getString(R.string.invCorrectAnswer, (getString(R.string.inv_q10_var_1) + "\n" + getString(R.string.inv_q10_var_4)));
        player_answer_tv.setText(planswer);
        correct_answer_tv.setText(coranswer);
        if (answer10.equals((getString(R.string.inv_q10_var_1) + "\n" + getString(R.string.inv_q10_var_4)))) {
            player_answer_tv.setTextColor(getResources().getColor(R.color.correct_answer_color));
        } else {
            player_answer_tv.setTextColor(getResources().getColor(R.color.wrong_answer_color));
        }
    }

    // This method is called by clicking on Main screen button. It returns the user to the Main screen.
    public void mainActivity() {
        Intent MainActivity = new Intent(this, MainActivity.class);
        startActivity(MainActivity);
        this.finish();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_to_main:
                mainActivity();
                break;
        }
    }

    // This method is called by clicking on Back button. It returns the user to the Main screen and kills this activity.
    @Override
    public void onBackPressed() {
        mainActivity();
    }
}