package com.example.myquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    CountDownTimer countDownTimer;
     private ProgressBar pb;
    int progressStatus = 0;
    TextView totalQuestionsTextView;
    TextView questionTextView,current_question;
    Button ansA, ansB, ansC, ansD;
    Button submitBtn;
    int correctCount=0;
    int wrongCount=0;
    Integer count;
    int totalQuestion = QuestionAnswer.question.length;
    int currentQuestionIndex = 1;
    String selectedAnswer = "";

    String passStatus = "";
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestionsTextView = findViewById(R.id.total_question);
        current_question = findViewById(R.id.current_question);
        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);

        pb = (ProgressBar) findViewById(R.id.progressBar);
        pb.setMax(10);

        submitBtn = findViewById(R.id.submit_btn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

//            textView = (TextView) findViewById(R.id.textView);



        totalQuestionsTextView.setText("Total questions : " + totalQuestion);


        loadNewQuestion();

//
    }


    @Override
    public void onClick(View view) {

        count = 0;
        pb.setVisibility(View.VISIBLE);

        new MyTask().execute(10);

        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.submit_btn){
            if(selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex])){
                correctCount++;
            }
            else {
                wrongCount++;
            }
            currentQuestionIndex++;
            loadNewQuestion();
            current_question.setText("Current questions : " + currentQuestionIndex);

        }else{
            //choices button clicked
            selectedAnswer  = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);

        }

    }

    void loadNewQuestion(){

        if(currentQuestionIndex == totalQuestion ){
//            Intent i1 = new Intent(MainActivity.this,ScoreActivity.class);
//            i1.putExtra("correct",correctCount);
//            i1.putExtra("wrong",wrongCount);
//            startActivity(i1);
//
//            correctCount = 0;
//            currentQuestionIndex =0;
//            loadNewQuestion();
            finishQuiz();
            return;

        }

        questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
        ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);

    }

    void finishQuiz(){

        if(correctCount > totalQuestion*0.60){
            passStatus = "Passed";
        }else{
            passStatus = "Failed";
        }

        new AlertDialog.Builder(this)
                .setTitle("Yeah!!!")
                .setMessage(" Quiz Completed"+"\n correct question "+correctCount+" \nwrong question "+wrongCount)
//                .setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz() )
                .setPositiveButton("View Score",(dialogInterface, i) -> ScoreQuiz() )
                .setCancelable(false)
                .show();
//        Intent i1 = new Intent(MainActivity.this,ScoreActivity.class);
//        i1.putExtra("correct",correctCount);
//        i1.putExtra("wrong",wrongCount);
//        startActivity(i1);

    }

    void Timeup(){
        new AlertDialog.Builder(this)
                .setTitle("Oooops!!!")
                .setMessage("Time up")
//                .setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz() )
                .setPositiveButton("Try Again",(dialogInterface, i) -> restartQuiz() )
                .setCancelable(false)
                .show();
    }

    void restartQuiz(){
        correctCount = 0;
        currentQuestionIndex =0;

        loadNewQuestion();
        Intent i1 = new Intent(MainActivity.this,StartActivity.class);

       startActivity(i1);
    }
    void ScoreQuiz(){


        Intent i1 = new Intent(MainActivity.this,ScoreActivity.class);
        i1.putExtra("correct",correctCount);
        i1.putExtra("wrong",wrongCount);
        i1.putExtra("passStatus",passStatus);
        startActivity(i1);

    }

    class MyTask extends AsyncTask <Integer, Integer, String> {
        @Override
        protected String doInBackground(Integer... params) {
            while(count <= params[0]) {
                try {
                    Thread.sleep(1000);
                    publishProgress(count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
            }

            return "Time up.";
        }
        @Override
        protected void onPostExecute(String result) {
//            txt.setText(result);
//            btn.setEnabled(true);
            Timeup();
            return;

        }
        @Override
        protected void onPreExecute() {
//            txt.setText("Starting now...");
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
//            txt.setText(" Counting "+ values[0]);
            pb.setProgress(values[0]);
        }
    }
}