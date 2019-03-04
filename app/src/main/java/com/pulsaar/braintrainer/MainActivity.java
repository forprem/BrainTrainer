package com.pulsaar.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity {
    Button btn;
    ArrayList <Integer> answers = new ArrayList<Integer>();
    int correctAnswerLocation;
    int scores = 0;
    int numberOfQuestions = 0;
    TextView resultText;
    TextView scoreText;
    Button btn0;
    Button btn1;
    Button btn2;
    Button btn3;
    TextView tv;
    TextView timerText;
    Button playAgain;

    public void start (View view){
        btn.setVisibility(View.INVISIBLE);
    }

    public void answer(View view){

        if (view.getTag().toString().equals(Integer.toString(correctAnswerLocation))) {
            resultText.setText("Correct");
            scores++;
        } else {
            resultText.setText("Wrong !");
        }
        numberOfQuestions++;
        scoreText.setText(scores+"/"+ numberOfQuestions);
        newQuestion();
    }

    public void newQuestion(){
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        correctAnswerLocation = rand.nextInt(4);

        tv.setText(Integer.toString(a)+ "+" +Integer.toString(b));

        answers.clear();

        for (int i=0;i<4; i++) {
            if (i==correctAnswerLocation){
                answers.add(a+b);
            } else {
                int wrongAnswer = rand.nextInt(41);
                while (wrongAnswer == a+b) {
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(wrongAnswer);

            }

        }
        btn0.setText(Integer.toString(answers.get(0)));
        btn1.setText(Integer.toString(answers.get(1)));
        btn2.setText(Integer.toString(answers.get(2)));
        btn3.setText(Integer.toString(answers.get(3)));

    }

    public void playAgain(View view){
        scores = 0;
        numberOfQuestions = 0;
        timerText.setText("30s");
        scoreText.setText(scores+"/"+ numberOfQuestions);
        newQuestion();
        playAgain.setVisibility(View.INVISIBLE);

        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String strTimerText = String.valueOf(millisUntilFinished/1000);
                timerText.setText(strTimerText + "s");
            }

            @Override
            public void onFinish() {
                resultText.setText("Done !");
                playAgain.setVisibility(View.VISIBLE);
            }
        }.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        tv = findViewById(R.id.sumText);
        resultText = findViewById(R.id.resultText);
        scoreText = findViewById(R.id.scoreText);
        timerText = findViewById(R.id.timerText);
        playAgain = findViewById(R.id.playAgain);

        btn0 = findViewById(R.id.gridBtn0);
        btn1 = findViewById(R.id.gridBtn1);
        btn2 = findViewById(R.id.gridBtn2);
        btn3 = findViewById(R.id.gridBtn3);

        playAgain(findViewById(R.id.timerText));

    }
}
