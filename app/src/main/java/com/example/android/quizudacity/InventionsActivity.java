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

    // Total number of questions
    static final int TOTAL_QUESTIONS_COUNT = 10;
    // Correct answers counter
    int correctScore = 0;
    // Incorrect answers counter
    int incorrectScore = 0;
    // Here we declare a variable to store player's name
    String name;
    // Here we declare a variable for the quiz results toast
    String resultMessage;
    // Here we declare a variable to store a string "Question (current question number) out of (total number of questions)"
    String answer9, answer10;
    // This is an array of id's of question numbers
    int[] questionNumbers = {R.id.tv_question1_number, R.id.tv_question2_number, R.id.tv_question3_number, R.id.tv_question4_number, R.id.tv_question5_number,
            R.id.tv_question6_number, R.id.tv_question7_number, R.id.tv_question8_number, R.id.tv_question9_number, R.id.tv_question10_number};
    int[] correctAns = {R.id.rb_answer1_3, R.id.rb_answer2_1, R.id.rb_answer3_3, R.id.rb_answer4_3, R.id.rb_answer5_3,
            R.id.rb_answer6_2, R.id.rb_answer7_3, R.id.rb_answer8_3};
    int[] radioGroups = {R.id.rg_q1_variants, R.id.rg_q2_variants, R.id.rg_q3_variants, R.id.rg_q4_variants, R.id.rg_q5_variants,
            R.id.rg_q6_variants, R.id.rg_q7_variants, R.id.rg_q8_variants};
    String[] checkedAnswers = new String[8];
    String[] correctAnswers = new String[8];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityInventionsBinding = DataBindingUtil.setContentView(this, R.layout.activity_inventions);
        activityInventionsBinding.btnAnswersButton.setOnClickListener(this);
        activityInventionsBinding.btnSubmitButton.setOnClickListener(this);
        activityInventionsBinding.btnShareButton.setOnClickListener(this);
        // Here we set numbers of the questions
        setQuestionNumbers();
    }

    // This method sets numbers to the questions
    private void setQuestionNumbers() {
        int numberOfQuestion = 1;
        for (int i = 0; i < TOTAL_QUESTIONS_COUNT; i++) {
            String caption = getString(R.string.question) + " " + numberOfQuestion + " "
                    + getString(R.string.out_of) + " " + TOTAL_QUESTIONS_COUNT;
            ((TextView) findViewById(questionNumbers[i])).setText(caption);
            numberOfQuestion++;
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_answers_button:
                invAnswersActivity();
                this.finish();
                break;
            case R.id.btn_submit_button:
                submit();
                break;
            case R.id.btn_share_button:
                share();
                break;
        }
    }

    // This method is called by clicking on Submit button. It checks if at least one answer has been selected in the quiz, and if not, asks to choose, and if so, creates and displays a toast with the results of the game.
    public void submit() {
        correctScore = 0;
        incorrectScore = 0;
        answer10 = "";
        if (isAllFilled()) {
            Toast.makeText(this, R.string.not_chosen, Toast.LENGTH_SHORT).show();
        } else {
            activityInventionsBinding.btnAnswersButton.setVisibility(View.VISIBLE);
            activityInventionsBinding.btnShareButton.setVisibility(View.VISIBLE);
            for (int rg = 0; rg < radioGroups.length; rg++) {
                RadioButton correct_answer = findViewById(correctAns[rg]);
                correctAnswers[rg] = ((RadioButton) findViewById(correct_answer.getId())).getText().toString();
                RadioGroup checked_group = findViewById(radioGroups[rg]);
                if (checked_group.getCheckedRadioButtonId() == -1) {
                    checkedAnswers[rg] = getString(R.string.not_answered);
                } else {
                    checkedAnswers[rg] = ((RadioButton) findViewById(checked_group.getCheckedRadioButtonId())).getText().toString();
                }
                if (correct_answer.isChecked()) {
                    correctScore++;
                } else {
                    incorrectScore++;
                }
            }
            if (activityInventionsBinding.etAnswer9.getText().toString().trim().equals("")) {
                answer9 = getString(R.string.not_answered);
                incorrectScore++;
            } else if (activityInventionsBinding.etAnswer9.getText().toString().trim().equals(getString(R.string.q_9_var_1))) {
                answer9 = getString(R.string.q_9_var_1);
                correctScore++;
            } else {
                answer9 = activityInventionsBinding.etAnswer9.getText().toString().trim();
                incorrectScore++;
            }
            if (!activityInventionsBinding.cbkAnswer1.isChecked() && !activityInventionsBinding.cbkAnswer2.isChecked() && !activityInventionsBinding.cbkAnswer3.isChecked() && !activityInventionsBinding.cbkAnswer4.isChecked()) {
                answer10 = getString(R.string.not_answered);
            }
            if (activityInventionsBinding.cbkAnswer1.isChecked()) {
                answer10 = activityInventionsBinding.cbkAnswer1.getText().toString();
            }
            if (activityInventionsBinding.cbkAnswer2.isChecked()) {
                answer10 = answer10 + "\n" + activityInventionsBinding.cbkAnswer2.getText().toString();
            }
            if (activityInventionsBinding.cbkAnswer3.isChecked()) {
                answer10 = answer10 + "\n" + activityInventionsBinding.cbkAnswer3.getText().toString();
            }
            if (activityInventionsBinding.cbkAnswer4.isChecked()) {
                answer10 = answer10 + "\n" + activityInventionsBinding.cbkAnswer4.getText().toString();
            }
            if (activityInventionsBinding.cbkAnswer1.isChecked() && !activityInventionsBinding.cbkAnswer2.isChecked() && !activityInventionsBinding.cbkAnswer3.isChecked() && activityInventionsBinding.cbkAnswer4.isChecked()) {
                correctScore++;
            } else {
                incorrectScore++;
            }
            Intent openInventionsQuizIntent = getIntent();
            name = openInventionsQuizIntent.getStringExtra(MainActivity.EXTRA_MESSAGE);
            String resultMessage = createQuizSummary(name, correctScore, incorrectScore, TOTAL_QUESTIONS_COUNT);
            Toast.makeText(getApplicationContext(), resultMessage, Toast.LENGTH_LONG).show();
        }
    }

    //This method checks whether at least one answer is selected in at least one question.
    private boolean isAllFilled() {
        return activityInventionsBinding.rgQ1Variants.getCheckedRadioButtonId() == -1 &&
                activityInventionsBinding.rgQ2Variants.getCheckedRadioButtonId() == -1 &&
                activityInventionsBinding.rgQ3Variants.getCheckedRadioButtonId() == -1 &&
                activityInventionsBinding.rgQ4Variants.getCheckedRadioButtonId() == -1 &&
                activityInventionsBinding.rgQ5Variants.getCheckedRadioButtonId() == -1 &&
                activityInventionsBinding.rgQ6Variants.getCheckedRadioButtonId() == -1 &&
                activityInventionsBinding.rgQ7Variants.getCheckedRadioButtonId() == -1 &&
                activityInventionsBinding.rgQ8Variants.getCheckedRadioButtonId() == -1 &&
                activityInventionsBinding.etAnswer9.getText().toString().trim().equals("") &&
                (!activityInventionsBinding.cbkAnswer1.isChecked() &&
                        !activityInventionsBinding.cbkAnswer2.isChecked() &&
                        !activityInventionsBinding.cbkAnswer3.isChecked() &&
                        !activityInventionsBinding.cbkAnswer4.isChecked());
    }

    // This method creates Quiz summary.
    private String createQuizSummary(String name, int correctScore, int incorrectScore, int totalNumberOfQuestions) {
        resultMessage = getString(R.string.name_summary) + " " + name + "!" + "\n" + getString(R.string.well_done) +
                "\n" + getString(R.string.results) + "\n" + correctScore + " " + getString(R.string.total_correct) + " " + getString(R.string.out_of) + " " + totalNumberOfQuestions + "!" +
                "\n" + incorrectScore + " " + getString(R.string.total_incorrect) + " " + getString(R.string.out_of) + " " + totalNumberOfQuestions + "!";
        return resultMessage;
    }

    // This method is called by clicking on Answers button. It opens Answers Activity.
    public void invAnswersActivity() {
        Intent invAnswersActivityIntent = new Intent(this, InvAnswersActivity.class);
        invAnswersActivityIntent.putExtra("players_answer", checkedAnswers);
        invAnswersActivityIntent.putExtra("correct_answer", correctAnswers);
        invAnswersActivityIntent.putExtra("answer9", answer9);
        invAnswersActivityIntent.putExtra("answer10", answer10);
        startActivity(invAnswersActivityIntent);
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
}