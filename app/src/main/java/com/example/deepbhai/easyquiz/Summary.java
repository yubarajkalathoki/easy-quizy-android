package com.example.deepbhai.easyquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Summary extends AppCompatActivity {
    Button total;
    Button right;
    Button wrong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        total = (Button) findViewById(R.id.btnTotalQuestion);
        right = (Button) findViewById(R.id.btnRightAnswer);
        wrong = (Button) findViewById(R.id.btnWrongAnswer);
        int totalQuestion = getIntent().getIntExtra("totalQuestion", 0);
        int rightAnswer = getIntent().getIntExtra("rightAnswer", 0);
        int wrongAnswer = getIntent().getIntExtra("wrongAnswer", 0);
        showSummary(totalQuestion, rightAnswer, wrongAnswer);
    }

    public void playAgain(View view) {
        Intent intent = new Intent(this, SelectRange.class);
        startActivity(intent);
    }

    public void showSummary(int totalQue, int rightAns, int wrongAns){
        total.setText("Total Question Solved: " + totalQue);
        right.setText("Right Answer: " + rightAns);
        wrong.setText("Wrong Answer: " + wrongAns);
    }
}
