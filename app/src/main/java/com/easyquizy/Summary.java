package com.easyquizy;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class Summary extends AppCompatActivity {
    TextView total;
    TextView right;
    TextView wrong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        total = (TextView) findViewById(R.id.viewTotalQues);
        right = (TextView) findViewById(R.id.viewRightAnswer);
        wrong = (TextView) findViewById(R.id.viewWrongAnswer);
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

        pieChart(rightAnswer, wrongAnswer);
    }

    private void pieChart(int right, int wrong) {
        PieChart chart = (PieChart) findViewById(R.id.chart);

        Description description = new Description();
        description.setTextColor(ColorTemplate.VORDIPLOM_COLORS[2]);
        description.setText("");
        chart.setDescription(description);

        List<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(right, "Right Answer"));
        pieEntries.add(new PieEntry(wrong, "Wrong Answer"));

        PieDataSet dataSet = new PieDataSet(pieEntries,"");
        dataSet.setColors(ColorSet.Right_Wrong);
        PieData data = new PieData(dataSet);
        chart.setData(data);

        chart.setHoleRadius(20);
        chart.setTransparentCircleRadius(25);
        chart.setDrawHoleEnabled(true);

        data.setValueTextSize(20);
        data.setValueTextColor(Color.WHITE);
        dataSet.setSliceSpace(5);

        chart.setCenterTextSize(30);
        chart.animateY(1000);
        chart.invalidate();
    }

    public void playAgain(View view) {
        Intent intent = new Intent(getApplicationContext(), selectLevel.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void showSummary(int totalQue, int rightAns, int wrongAns){
        total.setText(String.valueOf(totalQue));
        right.setText(String.valueOf(rightAns));
        wrong.setText(String.valueOf(wrongAns));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
