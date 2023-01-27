package com.example.myquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class ScoreActivity extends AppCompatActivity {

   ProgressBar ProgressBar;
    TextView progress_tv,pass,wro,corr;
    Button button;
    int correct,wrong;
    String passStatus;
    int b,a;

    Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        ProgressBar=findViewById(R.id.circular_determinative_pb);
        button=findViewById(R.id.button);
        progress_tv=findViewById(R.id.progress_tv);
        pass=findViewById(R.id.pass);
//        wro=findViewById(R.id.wro);
//        corr=findViewById(R.id.corr);

        correct=getIntent().getIntExtra("correct",0);
        wrong=getIntent().getIntExtra("wrong",0);
        passStatus=getIntent().getStringExtra("passStatus");
        b=correct/(correct+wrong);
        a=b*100;


        ProgressBar.setProgress(correct*15);
        pass.setText(passStatus);


        progress_tv.setText(correct*15+"%");


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(ScoreActivity.this,StartActivity.class);
                startActivity(i1);
            }
        });


    }
}