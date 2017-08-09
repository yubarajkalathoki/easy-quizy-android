package com.example.deepbhai.easyquiz;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;
import android.os.Handler;
import android.widget.Toast;


public class ActiveGame extends AppCompatActivity {
    TextView viewValue1, viewValue2, viewans1, viewans2, viewans3, viewans4, operator;
    int max, min;
    int ans1=0;
    int ans2=0;
    int ans3=0;
    int ans4=0;
    int value1=0;
    int value2=0;
    int answer = 0;
    int rightAns = 0;
    int wrongAns = 0;
    int totalQues = 0;
    long timeCountInMilliSeconds = 60000;
    private ProgressBar progressBarCircle;
    private TextView textViewTime;
    private CountDownTimer countDownTimer;
    private boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_game);
        min = getIntent().getIntExtra("lowerLimit", 0);
        max = getIntent().getIntExtra("upperLimit", 0);
        viewValue1 = (TextView) findViewById(R.id.viewValue1);
        viewValue2 = (TextView) findViewById(R.id.viewValue2);
        viewans1 = (TextView) findViewById(R.id.viewAnswer1);
        viewans2 = (TextView) findViewById(R.id.viewAnswer2);
        viewans3 = (TextView) findViewById(R.id.viewAnswer3);
        viewans4 = (TextView) findViewById(R.id.viewAnswer4);
        operator = (TextView) findViewById(R.id.viewOperator);
        randomOperator();
        initViews();
        startCountDownTimer();
    }

    public int randomOperator(){
        int maxx;
        if((min!=1 && min*2>max) || max<=3 || max>999){
            maxx = 3;
        }else{
            maxx = 4;
        }
        Random random = new Random();
        int randomOperator = random.nextInt(maxx - 1 + 1) + 1;
        assignOperator(randomOperator);
        return randomOperator;
        /*if(fl<=3 && mth_opn == 4){
            randomOperator();
            opn_btn();
        }*/
    }

    public void assignOperator(int randomOperator){
        switch(randomOperator){
            case 1: //add
                operator.setText("+");
                add();
                break;
            case 2: //subtract
                operator.setText("-");
                subtract();
                break;
            case 3: //multiply
                operator.setText("x");
                multiply();
                break;
            case 4:
                //divide
                operator.setText("/");
                divide();
                break;
            default: break;
        }
    }

    public void generateRandom(){
        value1 = number1();
        value2 = number2();
    }

    public int number1(){
        Random random = new Random();
        int value1 = random.nextInt(max - min + 1) + min;
        return value1;
    }

    public int number2(){
        Random random = new Random();
        int value2 = random.nextInt(max - min + 1) + min;
        return value2;
    }

    public void add(){
        generateRandom();
        answer = value1 + value2;
        viewValue1.setText(String.valueOf(value1));
        viewValue2.setText(String.valueOf(value2));
        generateAnswer();
    }

    public void subtract(){
        generateRandom();
        answer = value1 - value2;
        viewValue1.setText(String.valueOf(value1));
        viewValue2.setText(String.valueOf(value2));
        generateAnswer();
    }


    public void multiply(){
        generateRandom();
        //range();
        answer = value1 * value2;
        viewValue1.setText(String.valueOf(value1));
        viewValue2.setText(String.valueOf(value2));
        generateAnswer();
    }
    /*
    public void range() {
        if (min <= 999) {
            if (max > 999) {
                max = 999;
                generateRandom();
            }
        }
    }*/
    public void divide(){
        Random random = new Random();
        int divideCount=0;
        divideRandom();
        int ran = random.nextInt(50 - 2 + 1) + 2;
        if(value2 * ran <= max){
            value1 = value2 * ran;
            answer= value1/value2;
            viewValue1.setText(String.valueOf(value1));
            viewValue2.setText(String.valueOf(value2));
            generateAnswer();
        }else{
            divideCount++;
            if(divideCount==3){
                assignOperator(randomOperator());
            }
            divide();
        }
    }

    public void divideRandom(){
        Random random = new Random();
        value2 = random.nextInt(max - min + 1) + min;
        if(String.valueOf(value2).length()> 3 || value2 == 1){
            divideRandom();
        }
    }


    public void generateAnswer() {
        Random random = new Random();
        while(true){
            int opt1=answer;
            if(opt1<40 || opt1<-40){
                int opt2=random.nextInt((opt1+5)-(opt1-5)+1)+(opt1-5);
                int opt3=random.nextInt((opt1+5)-(opt1-5)+1)+(opt1-5);
                int opt4=random.nextInt((opt1+5)-(opt1-5)+1)+(opt1-5);
                if(opt1!=opt2 && opt1!=opt3 && opt1!=opt4 && opt2!=opt3 && opt2!=opt4 && opt3!=opt4){
                    int num1=random.nextInt((100-1+1)+(1))%4;
                    if(num1==0){
                        ans1=opt1;
                        ans2=opt2;
                        ans3=opt3;
                        ans4=opt4;
                        break;
                    }
                    else if(num1==1){
                        ans1=opt2;
                        ans2=opt3;
                        ans3=opt4;
                        ans4=opt1;
                        break;
                    }
                    else if(num1==2){
                        ans1=opt3;
                        ans2=opt4;
                        ans3=opt1;
                        ans4=opt2;
                        break;
                    }
                    else{
                        ans1=opt4;
                        ans2=opt1;
                        ans3=opt2;
                        ans4=opt3;
                        break;
                    }
                }
            }
            else{
                int opt2=opt1-10;
                int opt3=opt1+10;
                int opt4=opt1+20;
                int opt5=opt1-20;
                int num1=random.nextInt((1000000-1+1)+(1))%72;
                if(num1==0){
                    ans1=opt1;
                    ans2=opt2;
                    ans3=opt3;
                    ans4=opt4;
                    break;
                }
                else if(num1==1){
                    ans1=opt1;
                    ans2=opt3;
                    ans3=opt2;
                    ans4=opt4;
                    break;
                }
                else if(num1==2){
                    ans1=opt1;
                    ans2=opt3;
                    ans3=opt4;
                    ans4=opt2;
                    break;
                }
                else if(num1==3){
                    ans1=opt1;
                    ans2=opt2;
                    ans3=opt4;
                    ans4=opt3;
                    break;
                }
                else if(num1==4){
                    ans1=opt1;
                    ans2=opt4;
                    ans3=opt2;
                    ans4=opt3;
                    break;
                }////from here
                else if(num1==5){
                    ans1=opt1;
                    ans2=opt4;
                    ans3=opt3;
                    ans4=opt2;
                    break;
                }
                else if(num1==6){
                    ans1=opt2;
                    ans2=opt1;
                    ans3=opt3;
                    ans4=opt4;
                    break;
                }
                else if(num1==7){
                    ans1=opt2;
                    ans2=opt1;
                    ans3=opt4;
                    ans4=opt3;
                    break;
                }
                else if(num1==8){
                    ans1=opt3;
                    ans2=opt1;
                    ans3=opt2;
                    ans4=opt4;
                    break;
                }else if(num1==9){
                    ans1=opt3;
                    ans2=opt1;
                    ans3=opt4;
                    ans4=opt2;
                    break;
                }
                else if(num1==10){
                    ans1=opt4;
                    ans2=opt1;
                    ans3=opt2;
                    ans4=opt3;
                    break;
                }
                else if(num1==11){
                    ans1=opt4;
                    ans2=opt1;
                    ans3=opt3;
                    ans4=opt2;
                    break;
                }
                else if(num1==12){
                    ans1=opt2;
                    ans2=opt3;
                    ans3=opt1;
                    ans4=opt4;
                    break;
                }else if(num1==13){
                    ans1=opt3;
                    ans2=opt2;
                    ans3=opt1;
                    ans4=opt4;
                    break;
                }
                else if(num1==14){
                    ans1=opt4;
                    ans2=opt3;
                    ans3=opt1;
                    ans4=opt2;
                    break;
                }
                else if(num1==15){
                    ans1=opt3;
                    ans2=opt4;
                    ans3=opt1;
                    ans4=opt2;
                    break;
                }
                else if(num1==16){
                    ans1=opt4;
                    ans2=opt2;
                    ans3=opt1;
                    ans4=opt3;
                    break;
                }else if(num1==17){
                    ans1=opt2;
                    ans2=opt4;
                    ans3=opt1;
                    ans4=opt3;
                    break;
                }
                else if(num1==18){
                    ans1=opt1;
                    ans2=opt2;
                    ans3=opt5;
                    ans4=opt4;
                    break;
                }
                else if(num1==19){
                    ans1=opt1;
                    ans2=opt5;
                    ans3=opt2;
                    ans4=opt4;
                    break;
                }
                else if(num1==20){
                    ans1=opt1;
                    ans2=opt5;
                    ans3=opt4;
                    ans4=opt2;
                    break;
                }else if(num1==21){
                    ans1=opt1;
                    ans2=opt2;
                    ans3=opt4;
                    ans4=opt5;
                    break;
                }
                else if(num1==22){
                    ans1=opt1;
                    ans2=opt4;
                    ans3=opt2;
                    ans4=opt5;
                    break;
                }
                else if(num1==23){
                    ans1=opt1;
                    ans2=opt4;
                    ans3=opt5;
                    ans4=opt2;
                    break;
                }
                else if(num1==24){
                    ans1=opt2;
                    ans2=opt1;
                    ans3=opt5;
                    ans4=opt4;
                    break;
                }else if(num1==25){
                    ans1=opt2;
                    ans2=opt1;
                    ans3=opt4;
                    ans4=opt5;
                    break;
                }
                else if(num1==26){
                    ans1=opt5;
                    ans2=opt1;
                    ans3=opt2;
                    ans4=opt4;
                    break;
                }
                else if(num1==27){
                    ans1=opt5;
                    ans2=opt1;
                    ans3=opt4;
                    ans4=opt2;
                    break;
                }
                else if(num1==28){
                    ans1=opt5;
                    ans2=opt1;
                    ans3=opt2;
                    ans4=opt3;
                    break;
                }else if(num1==29){
                    ans1=opt5;
                    ans2=opt1;
                    ans3=opt3;
                    ans4=opt2;
                    break;
                }
                else if(num1==30){
                    ans1=opt1;
                    ans2=opt2;
                    ans3=opt3;
                    ans4=opt5;
                    break;
                }
                else if(num1==31){
                    ans1=opt1;
                    ans2=opt3;
                    ans3=opt2;
                    ans4=opt5;
                    break;
                }
                else if(num1==32){
                    ans1=opt1;
                    ans2=opt3;
                    ans3=opt5;
                    ans4=opt2;
                    break;
                }else if(num1==33){
                    ans1=opt1;
                    ans2=opt2;
                    ans3=opt5;
                    ans4=opt3;
                    break;
                }
                else if(num1==34){
                    ans1=opt1;
                    ans2=opt5;
                    ans3=opt2;
                    ans4=opt3;
                    break;
                }
                else if(num1==35){
                    ans1=opt1;
                    ans2=opt5;
                    ans3=opt3;
                    ans4=opt2;
                    break;
                }
                else if(num1==36){
                    ans1=opt2;
                    ans2=opt1;
                    ans3=opt3;
                    ans4=opt5;
                    break;
                }else if(num1==37){
                    ans1=opt2;
                    ans2=opt1;
                    ans3=opt5;
                    ans4=opt3;
                    break;
                }
                else if(num1==38){
                    ans1=opt3;
                    ans2=opt1;
                    ans3=opt2;
                    ans4=opt5;
                    break;
                }
                else if(num1==39){
                    ans1=opt3;
                    ans2=opt1;
                    ans3=opt5;
                    ans4=opt2;
                    break;
                }
                else if(num1==40){
                    ans1=opt5;
                    ans2=opt1;
                    ans3=opt2;
                    ans4=opt3;
                    break;
                }else if(num1==41){
                    ans1=opt5;
                    ans2=opt1;
                    ans3=opt3;
                    ans4=opt2;
                    break;
                }
                else if(num1==42){
                    ans1=opt2;
                    ans2=opt3;
                    ans3=opt1;
                    ans4=opt5;
                    break;
                }
                else if(num1==43){
                    ans1=opt3;
                    ans2=opt2;
                    ans3=opt1;
                    ans4=opt5;
                    break;
                }
                else if(num1==44){
                    ans1=opt5;
                    ans2=opt3;
                    ans3=opt1;
                    ans4=opt2;
                    break;
                }else if(num1==45){
                    ans1=opt3;
                    ans2=opt5;
                    ans3=opt1;
                    ans4=opt2;
                    break;
                }
                else if(num1==46){
                    ans1=opt5;
                    ans2=opt2;
                    ans3=opt1;
                    ans4=opt3;
                    break;
                }
                else if(num1==47){
                    ans1=opt2;
                    ans2=opt5;
                    ans3=opt1;
                    ans4=opt3;
                    break;
                }
                else if(num1==48){
                    ans1=opt2;
                    ans2=opt5;
                    ans3=opt1;
                    ans4=opt4;
                    break;
                }else if(num1==49){
                    ans1=opt5;
                    ans2=opt2;
                    ans3=opt1;
                    ans4=opt4;
                    break;
                }
                else if(num1==50){
                    ans1=opt4;
                    ans2=opt5;
                    ans3=opt1;
                    ans4=opt2;
                    break;
                }
                else if(num1==51){
                    ans1=opt5;
                    ans2=opt4;
                    ans3=opt1;
                    ans4=opt2;
                    break;
                }
                else if(num1==52){
                    ans1=opt4;
                    ans2=opt2;
                    ans3=opt1;
                    ans4=opt5;
                    break;
                }else if(num1==53){
                    ans1=opt2;
                    ans2=opt4;
                    ans3=opt1;
                    ans4=opt5;
                    break;
                }
                else if(num1==54){
                    ans1=opt1;
                    ans2=opt5;
                    ans3=opt3;
                    ans4=opt4;
                    break;
                }
                else if(num1==55){
                    ans1=opt1;
                    ans2=opt3;
                    ans3=opt5;
                    ans4=opt4;
                    break;
                }
                else if(num1==56){
                    ans1=opt1;
                    ans2=opt3;
                    ans3=opt4;
                    ans4=opt5;
                    break;
                }else if(num1==57){
                    ans1=opt1;
                    ans2=opt5;
                    ans3=opt4;
                    ans4=opt3;
                    break;
                }
                else if(num1==58){
                    ans1=opt1;
                    ans2=opt4;
                    ans3=opt5;
                    ans4=opt3;
                    break;
                }
                else if(num1==59){
                    ans1=opt1;
                    ans2=opt4;
                    ans3=opt3;
                    ans4=opt5;
                    break;
                }
                else if(num1==60){
                    ans1=opt5;
                    ans2=opt1;
                    ans3=opt3;
                    ans4=opt4;
                    break;
                }else if(num1==61){
                    ans1=opt5;
                    ans2=opt1;
                    ans3=opt4;
                    ans4=opt3;
                    break;
                }
                else if(num1==62){
                    ans1=opt3;
                    ans2=opt1;
                    ans3=opt5;
                    ans4=opt4;
                    break;
                }
                else if(num1==63){
                    ans1=opt3;
                    ans2=opt1;
                    ans3=opt4;
                    ans4=opt5;
                    break;
                }
                else if(num1==64){
                    ans1=opt4;
                    ans2=opt1;
                    ans3=opt5;
                    ans4=opt3;
                    break;
                }else if(num1==65){
                    ans1=opt4;
                    ans2=opt1;
                    ans3=opt3;
                    ans4=opt5;
                    break;
                }
                else if(num1==66){
                    ans1=opt5;
                    ans2=opt3;
                    ans3=opt1;
                    ans4=opt4;
                    break;
                }
                else if(num1==67){
                    ans1=opt3;
                    ans2=opt5;
                    ans3=opt1;
                    ans4=opt4;
                    break;
                }
                else if(num1==68){
                    ans1=opt4;
                    ans2=opt3;
                    ans3=opt1;
                    ans4=opt5;
                    break;
                }
                else if(num1==69){
                    ans1=opt3;
                    ans2=opt4;
                    ans3=opt1;
                    ans4=opt5;
                    break;
                }
                else if(num1==70){
                    ans1=opt4;
                    ans2=opt5;
                    ans3=opt1;
                    ans4=opt3;
                    break;
                }
                else{
                    ans1=opt5;
                    ans2=opt4;
                    ans3=opt1;
                    ans4=opt3;
                    break;
                }
            }
        }
        viewans1.setText(String.valueOf(ans1));
        viewans2.setText(String.valueOf(ans2));
        viewans3.setText(String.valueOf(ans3));
        viewans4.setText(String.valueOf(ans4));
    }

    //Checks Either Answer is correct or not
    public void checkAnswerOne(View view) {
        disableButton(false);
        totalQues++;
        if(ans1 == answer){
            viewans1.setBackgroundDrawable(getResources().getDrawable(R.drawable.roundtextviewgreen));
            rightAns++;
            delay();

        }else{
            viewans1.setBackgroundDrawable(getResources().getDrawable(R.drawable.roundtextviewred));
            wrongAns++;
            delay();
        }
    }
    public void checkAnswerTwo(View view) {
        disableButton(false);
        totalQues++;
        if(ans2 == answer){
            viewans2.setBackgroundDrawable(getResources().getDrawable(R.drawable.roundtextviewgreen));
            rightAns++;
            delay();
        }else{
            viewans2.setBackgroundDrawable(getResources().getDrawable(R.drawable.roundtextviewred));
            wrongAns++;
            delay();
        }
    }

    public void checkAnswerThree(View view) {
        disableButton(false);
        totalQues++;
        if(ans3 == answer){
            viewans3.setBackgroundDrawable(getResources().getDrawable(R.drawable.roundtextviewgreen));
            rightAns++;
            delay();
        }else{
            viewans3.setBackgroundDrawable(getResources().getDrawable(R.drawable.roundtextviewred));
            wrongAns++;
            delay();
        }
    }

    public void checkAnswerFour(View view) {
        disableButton(false);
        totalQues++;
        if(ans4 == answer){
            viewans4.setBackgroundDrawable(getResources().getDrawable(R.drawable.roundtextviewgreen));
            rightAns++;
            delay();
        }else{
            viewans4.setBackgroundDrawable(getResources().getDrawable(R.drawable.roundtextviewred));
            wrongAns++;
            delay();
        }
    }

    public void delay(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                randomOperator();
                viewans1.setBackgroundDrawable(getResources().getDrawable(R.drawable.roundtextviewblue));
                viewans2.setBackgroundDrawable(getResources().getDrawable(R.drawable.roundtextviewblue));
                viewans3.setBackgroundDrawable(getResources().getDrawable(R.drawable.roundtextviewblue));
                viewans4.setBackgroundDrawable(getResources().getDrawable(R.drawable.roundtextviewblue));
                disableButton(true);
            }
        }, 500);
    }
    public void startCountDownTimer() {

        countDownTimer = new CountDownTimer(timeCountInMilliSeconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(millisUntilFinished /1000 <=10){
                    progressBarCircle.setProgressDrawable(getResources().getDrawable(R.drawable.drawable_circle_red));
                    textViewTime.setTextColor(Color.RED);
                    blink();
                }
                textViewTime.setText(String.valueOf(millisUntilFinished / 1000));

                progressBarCircle.setProgress((int) (millisUntilFinished / 1000));

            }

            @Override
            public void onFinish() {
                summary(totalQues, rightAns, wrongAns);
            }

        }.start();
    }

    private void initViews() {
        progressBarCircle = (ProgressBar) findViewById(R.id.progressBarCircle);
        textViewTime = (TextView) findViewById(R.id.textViewTime);
    }

    public void disableButton(Boolean a){
        viewans1.setClickable(a);
        viewans2.setClickable(a);
        viewans3.setClickable(a);
        viewans4.setClickable(a);
    }
    public void summary(int totalQuestion, int rightAnswer, int wrongAnswer){
        Intent intent = new Intent(this, Summary.class);
        intent.putExtra("totalQuestion",totalQuestion);
        intent.putExtra("rightAnswer",rightAnswer);
        intent.putExtra("wrongAnswer",wrongAnswer);
        startActivity(intent);
    }
    public void blink(){
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(200); //You can manage the blinking time with this parameter
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        textViewTime.startAnimation(anim);
    }



    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            countDownTimer.cancel();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to stop", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }


}
