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
        int lowerLimit = Integer.parseInt(lower.getText().toString());
        int upperLimit = Integer.parseInt(upper.getText().toString());
        if(lowerLimit>upperLimit){
            lower.setError("Enter Valid Range");
            upper.setError("Enter Valid Range");
        }else if(lowerLimit==upperLimit){
            lower.setError("Value should not be equal");
            upper.setError("Value should not be equal");
        }else if(String.valueOf(lowerLimit).length() == 0){
            lower.setError("Enter Value here");
        }else if(String.valueOf(upperLimit).length() == 0){
            upper.setError("Enter Value here");
        }else{
            Intent intent = new Intent(this, ActiveGame.class);
            intent.putExtra("lowerLimit",lowerLimit);
            intent.putExtra("upperLimit",upperLimit);
            startActivity(intent);
        }

    }
}
