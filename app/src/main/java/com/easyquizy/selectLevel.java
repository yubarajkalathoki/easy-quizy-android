package com.easyquizy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class selectLevel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_level);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Easy Quizy");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        init();
    }

    private void init() {
        Button lvl1 = (Button) findViewById(R.id.level1);
        Button lvl2 = (Button) findViewById(R.id.level2);
        Button lvl3 = (Button) findViewById(R.id.level3);
    }


    private void nextActivity(int lowerLimit, int upperLimit){
        Intent intent = new Intent(this, ActiveGame.class);
        intent.putExtra("lowerLimit", lowerLimit);
        intent.putExtra("upperLimit", upperLimit);
        startActivity(intent);
    }

    public void level1(View view) {
        nextActivity(1,10);
    }

    public void level2(View view) {
        nextActivity(10,50);
    }

    public void level3(View view) {
        nextActivity(50,500);
    }
}
