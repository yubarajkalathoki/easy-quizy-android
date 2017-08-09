package com.example.deepbhai.easyquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class SelectRange extends AppCompatActivity {
    EditText lower, upper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_range);
        lower=(EditText)findViewById(R.id.getLower);
        upper=(EditText)findViewById(R.id.getUpper);
    }

    public void activeGame(View view) {
        String getLower = lower.getText().toString();
        String getUpper = upper.getText().toString();
        if(getLower.length()==0 && getUpper.length()==0){
            lower.setError("Field cannot be empty");
            upper.setError("Field cannot be empty");
        }else if(getLower.length()==0){
            lower.setError("Field cannot be empty");
        }else if(getUpper.length()==0){
            upper.setError("Field cannot be empty");
        }else if(getLower.length()>4){
            lower.setError("Value Should not be greater than 9999");
        }else if(getUpper.length()>4){
            upper.setError("Value Should not be greater than 9999");
        }else{
            int lowerLimit = Integer.parseInt(getLower);
            int upperLimit = Integer.parseInt(getUpper);
            if(lowerLimit>upperLimit){
                lower.setError("Enter Valid Range");
                upper.setError("Enter Valid Range");
            }else if(lowerLimit==upperLimit){
                lower.setError("Value should not be equal");
                upper.setError("Value should not be equal");
            }else {
                Intent intent = new Intent(this, ActiveGame.class);
                intent.putExtra("lowerLimit", lowerLimit);
                intent.putExtra("upperLimit", upperLimit);
                startActivity(intent);
            }
        }

    }

    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
