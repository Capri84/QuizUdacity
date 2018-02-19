package com.example.android.quizudacity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.quizudacity.databinding.ActivityInventionsBinding;

public class InventionsActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityInventionsBinding activityInventionsBinding;

    // Number of the current question
    int currentQuestion = 1;
    // Total number of questions
    int totalNumberOfQuestions = 10;
    // Correct answers counter
    int correct_score = 0;
    // Incorrect answers counter
    int incorrect_score = 0;
    // Here we declare a variable to store the question number
    TextView question_number;
    // Here we declare a variable to store player's name
    String name;
    // Here we declare a variable for the quiz results toast
    String resultMessage;
    // Here we declare a variable to store a string "Question (current question number) out of (total number of questions)"
    String qnum, answer9, answer10;
    // This is an array of id's of question numbers
    int[] question_numbers = {R.id.inv_question1_number, R.id.inv_question2_number, R.id.inv_question3_number, R.id.inv_question4_number, R.id.inv_question5_number,
            R.id.inv_question6_number, R.id.inv_question7_number, R.id.inv_question8_number, R.id.inv_question9_number, R.id.inv_question10_number};
    int[] correct_ans = {R.id.answer1_3, R.id.answer2_1, R.id.answer3_3, R.id.answer4_3, R.id.answer5_3,
            R.id.answer6_2, R.id.answer7_3, R.id.answer8_3};
    int[] radioGroups = {R.id.radio_group1, R.id.radio_group2, R.id.radio_group3, R.id.radio_group4, R.id.radio_group5,
            R.id.radio_group6, R.id.radio_group7, R.id.radio_group8};
    String[] checked_answers = new String[8];
    String[] correct_answers = new String[8];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityInventionsBinding = DataBindingUtil.setContentView(this, R.layout.activity_inventions);
        activityInventionsBinding.answersButton.setOnClickListener(this);
        activityInventionsBinding.submitButton.setOnClickListener(this);
        activityInventionsBinding.shareButton.setOnClickListener(this);
        // Here we set numbers of the questions
        setQuestion_numbers();
    }

    // This method sets numbers to the questions
    public void setQuestion_numbers() {
        for (int j = 0; j < question_numbers.length; j++) {
            question_number = findViewById(question_numbers[j]);
            qnum = getString(R.string.questionNumber, currentQuestion, totalNumberOfQuestions);
            question_number.setText(qnum);
            currentQuestion++;
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.answers_button:
                invAnswersActivity();
                this.finish();
                break;
            case R.id.submit_button:
                submit();
                break;
            case R.id.share_button:
                share();
                break;
        }
    }

    // This method is called by clicking on Submit button. It checks if at least one answer has been selected in the quiz, and if not, asks to choose, and if so, creates and displays a toast with the results of the game.
    public void submit() {
        correct_score = 0;
        incorrect_score = 0;
        answer10 = "";
        if (activityInventionsBinding.radioGroup1.getCheckedRadioButtonId() == -1 && activityInventionsBinding.radioGroup2.getCheckedRadioButtonId() == -1 &&
                activityInventionsBinding.radioGroup3.getCheckedRadioButtonId() == -1 && activityInventionsBinding.radioGroup4.getCheckedRadioButtonId() == -1 &&
                activityInventionsBinding.radioGroup5.getCheckedRadioButtonId() == -1 && activityInventionsBinding.radioGroup6.getCheckedRadioButtonId() == -1 &&
                activityInventionsBinding.radioGroup7.getCheckedRadioButtonId() == -1 && activityInventionsBinding.radioGroup8.getCheckedRadioButtonId() == -1 &&
                activityInventionsBinding.invAnswer9.getText().toString().trim().equals("") && (!activityInventionsBinding.checkbox1.isChecked() && !activityInventionsBinding.checkbox2.isChecked() && !activityInventionsBinding.checkbox3.isChecked() && !activityInventionsBinding.checkbox4.isChecked())) {
            Toast.makeText(this, R.string.not_chosen1, Toast.LENGTH_SHORT).show();
            return;
        } else {
            activityInventionsBinding.answersButton.setVisibility(View.VISIBLE);
            activityInventionsBinding.shareButton.setVisibility(View.VISIBLE);
            for (int rg = 0; rg < radioGroups.length; rg++) {
                RadioButton correct_answer = (RadioButton) findViewById(correct_ans[rg]);
                correct_answers[rg] = ((RadioButton) findViewById(correct_answer.getId())).getText().toString();
                RadioGroup checked_group = (RadioGroup) findViewById(radioGroups[rg]);
                if (checked_group.getCheckedRadioButtonId() == -1) {
                    checked_answers[rg] = getString(R.string.not_answered);
                } else {
                    checked_answers[rg] = ((RadioButton) findViewById(checked_group.getCheckedRadioButtonId())).getText().toString();
                }
                if (correct_answer.isChecked()) {
                    correct_score++;
                } else {
                    incorrect_score++;
                }
            }
            if (activityInventionsBinding.invAnswer9.getText().toString().trim().equals("")) {
                answer9 = getString(R.string.not_answered);
                incorrect_score++;
            } else if (activityInventionsBinding.invAnswer9.getText().toString().trim().equals(getString(R.string.inv_q9_var_1))) {
                answer9 = getString(R.string.inv_q9_var_1);
                correct_score++;
            } else {
                answer9 = activityInventionsBinding.invAnswer9.getText().toString().trim();
                incorrect_score++;
            }
            if (!activityInventionsBinding.checkbox1.isChecked() && !activityInventionsBinding.checkbox2.isChecked() && !activityInventionsBinding.checkbox3.isChecked() && !activityInventionsBinding.checkbox4.isChecked()) {
                answer10 = getString(R.string.not_answered);
            }
            if (activityInventionsBinding.checkbox1.isChecked()) {
                answer10 = activityInventionsBinding.checkbox1.getText().toString();
            }
            if (activityInventionsBinding.checkbox2.isChecked()) {
                answer10 = answer10 + "\n" + activityInventionsBinding.checkbox2.getText().toString();
            }
            if (activityInventionsBinding.checkbox3.isChecked()) {
                answer10 = answer10 + "\n" + activityInventionsBinding.checkbox3.getText().toString();
            }
            if (activityInventionsBinding.checkbox4.isChecked()) {
                answer10 = answer10 + "\n" + activityInventionsBinding.checkbox4.getText().toString();
            }
            if (activityInventionsBinding.checkbox1.isChecked() && !activityInventionsBinding.checkbox2.isChecked() && !activityInventionsBinding.checkbox3.isChecked() && activityInventionsBinding.checkbox4.isChecked()) {
                correct_score++;
            } else {
                incorrect_score++;
            }
            Intent openInventionsQuiz = getIntent();
            name = openInventionsQuiz.getStringExtra(MainActivity.EXTRA_MESSAGE);
            String resultMessage = createQuizSummary(name, correct_score, incorrect_score, totalNumberOfQuestions);
            Toast.makeText(getApplicationContext(), resultMessage, Toast.LENGTH_LONG).show();
        }
    }

    // This method creates Quiz summary.
    private String createQuizSummary(String name, int correct_score, int incorrect_score, int totalNumberOfQuestions) {
        resultMessage = getString(R.string.nameSummary, name);
        resultMessage += "\n" + getString(R.string.well_done);
        resultMessage += "\n" + getString(R.string.results);
        resultMessage += "\n" + getString(R.string.total_correct, correct_score, totalNumberOfQuestions);
        resultMessage += "\n" + getString(R.string.total_incorrect, incorrect_score, totalNumberOfQuestions);
        return resultMessage;
    }

    // This method is called by clicking on Answers button. It opens Answers Activity.
    public void invAnswersActivity() {
        Intent invAnswersActivity = new Intent(this, invAnswersActivity.class);
        invAnswersActivity.putExtra("players_answer", checked_answers);
        invAnswersActivity.putExtra("correct_answer", correct_answers);
        invAnswersActivity.putExtra("answer9", answer9);
        invAnswersActivity.putExtra("answer10", answer10);
        startActivity(invAnswersActivity);
    }

    // This method is called by clicking on Share button. It allows to share your game results via e-mail, social apps, etc.
    public void share() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, resultMessage);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Quiz results");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    // This method is called by clicking on Back button. It returns the user to the Main screen and kills this activity.
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        this.finish();
    }
}