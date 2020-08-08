package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class GoButton extends AppCompatActivity {

    Button answer1;
    Button answer2;
    Button answer3;
    Button answer4;

    // playagain button
    Button playagainBtn;

    // score variables
    int score = 0;
    int totalQuestion = 0;

    // declaring mediaplayer
    MediaPlayer mediaPlayer;
    int sum;

    TextView results;
    TextView scoreTracker;
    TextView showTimer;
    Button buttonSave;
//    CountDownTimer countDownTimer;
    private long timeLeftinMilliseconds = 5000;
    private boolean timerRunning;
    TextView showSum;

    Random generator;


    // array of integers that will store integers from 1 to 50. We will use this arraylist of integers
    // for the random creation of our sum.
    int[] rangeofNumbers = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,
                                     28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,
                                      49,50};


    // our arraylist will contain the answers of the sum. three numbers will be incorrect choice
    // and one of the elements will be correct answer.
    ArrayList<String> answers = new ArrayList<>();



    public void playAgain(View view){

        score = 0;
        totalQuestion = 0;
        showTimer.setText("30s");
        nextQuestion();
        playagainBtn.setVisibility(View.INVISIBLE);
        results.setText("");
        startTimer();


    }

    ///////////////////////////////////////////////////
///////////////////////////////
// event listener for the gobutton layout
    public void startGame(View view){
//        showTimer = (TextView) findViewById(R.id.showTimer);

//        startStop();

    }

    public void nextQuestion(){
        generator= new Random();
        int randomIndex1 = generator.nextInt(rangeofNumbers.length);
        int firstNumber = rangeofNumbers[randomIndex1];
        // second random number
        int randomIndex2 = generator.nextInt(rangeofNumbers.length);
        int secondNumber = rangeofNumbers[randomIndex2];


        // random numbers for button
        for (int i = 0; i < 3; i++){
            int randomIndex = generator.nextInt(rangeofNumbers.length);
            int randomNumberBtn = rangeofNumbers[randomIndex];
            String strrandomNumber = Integer.toString(randomNumberBtn);
            answers.add(strrandomNumber);
        }
        sum = firstNumber + secondNumber;
        String strSum = Integer.toString(sum);
        int a = generator.nextInt(4);
        answers.add(a, strSum);
        // calling buttonTextShowup
        buttonTextShowScreen(answers.get(0),answers.get(1),answers.get(2),answers.get(3));



        // showing the sum part
        showSum = (TextView) findViewById(R.id.showSum);
        String representedNumber = Integer.toString(firstNumber) + '+' + Integer.toString(secondNumber);
        // displaying the numbers into our sumView
        showSum.setText(representedNumber);

        // sum of two random number
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_button);
        // initializes the timer
        showTimer = (TextView) findViewById(R.id.showTimer);
        scoreTracker = (TextView) findViewById(R.id.scoreTracker);
        results = (TextView) findViewById(R.id.resultTextview);
        playagainBtn = (Button) findViewById(R.id.playAgain);
        // initializing mediaPlayer


        //ininitializing the showsum textview
        showSum = (TextView) findViewById(R.id.showSum);
        playAgain(findViewById(R.id.playAgain));

//        startTimer();
//        //
//
//        nextQuestion();




    }


    public void buttonTextShowScreen(String one, String two, String three, String four){
        answer1 = (Button) findViewById(R.id.answer1);
        answer2 = (Button) findViewById(R.id.answer2);
        answer3 = (Button) findViewById(R.id.answer3);
        answer4 = (Button) findViewById(R.id.answer4);

        answer1.setText(one);
        answer2.setText(two);
        answer3.setText(three);
        answer4.setText(four);
    }

    // this onclick listener responds to our click. This contains the logic of comparing the id of
    // our buttons to the index of the correctanswer which is present in our arraylist.
    public void calculationDetermination(View view){

        String result;
        int locationOfCorectAnswer = answers.indexOf(Integer.toString(sum));

        String strlocationOfCorectAnswer = Integer.toString(locationOfCorectAnswer);


        if (view.getTag().toString().equals(strlocationOfCorectAnswer)){
           result = "It is correct";
           mediaPlayer = MediaPlayer.create(this, R.raw.correctanswer1);
           mediaPlayer.start();
           score ++;

           results.setText("correct");



//           scoreTracker.setText(Integer.toString(score) + "/" + Integer.toString(totalQuestion));
        }
        else{
            results.setText(":(");
            mediaPlayer = MediaPlayer.create(this, R.raw.wrongbuzzer);
            mediaPlayer.start();
            totalQuestion ++;
            System.out.println("you are wrong");

        }
        scoreTracker.setText(Integer.toString(score) + "/" + Integer.toString(totalQuestion));
        nextQuestion();

    }

    // this function is responsible for starting our timer when we click the go button.
//    public void startStop(){
//        if (timerRunning){
//
//        }
//        else{
//            startTimer();
//        }
//
//    }
    ////////////////////////////////////
    //////////
    //this section contains the function to start the timer
    public void startTimer(){

         new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftinMilliseconds = l;
                showTimer.setText(String.valueOf(l / 1000) + "s");
//                    updateTimer();

            }

            @Override
            public void onFinish() {
                playagainBtn.setVisibility(View.VISIBLE);

            }
        }.start();




    }
    /////////////////////////////////////////////////////////////
/////////////////////////////////////////////////
// this function stops the timer
//    public void stopTimer(){
//        countDownTimer.cancel();
////        buttonGo.setText("Start");
//        timerRunning = false;
//    }

    public void updateTimer(){

        int seconds = (int) timeLeftinMilliseconds / 1000;

        String timeLeftText;

        timeLeftText = "" + seconds;

        showTimer.setText(timeLeftText);

    }


    public void updateQuestions(){



    }


}


