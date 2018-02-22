package com.example.android.quizudacity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.android.quizudacity.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // This method calls Inventions Quiz Activity
    public void openInventionsQuiz(View view) {
        Intent openInventionsQuizIntent = new Intent(this, InventionsActivity.class);
        EditText nameField = findViewById(R.id.et_name);
        String message = nameField.getText().toString().trim();
        if (message.isEmpty()) {
            Toast.makeText(this, R.string.no_name, Toast.LENGTH_SHORT).show();
        } else {
            openInventionsQuizIntent.putExtra(EXTRA_MESSAGE, message);
            startActivity(openInventionsQuizIntent);
            this.finish();
        }
    }
}