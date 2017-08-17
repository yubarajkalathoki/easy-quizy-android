package com.easyquizy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Easy Quizy");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                //What to do on back clicked
            }
        });
        showSummary(totalQuestion, rightAnswer, wrongAnswer);
    }

    public void playAgain(View view) {
        Intent intent = new Intent(getApplicationContext(), selectLevel.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void showSummary(int totalQue, int rightAns, int wrongAns){
        total.setText("Total Question Solved: " + totalQue);
        right.setText("Right Answer: " + rightAns);
        wrong.setText("Wrong Answer: " + wrongAns);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
