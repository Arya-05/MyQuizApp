package com.example.myquizapp;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Ques_form extends AppCompatActivity {



    Button but1;
    TextView text1,text2,text3,que;
    EditText edt1,edt2,edt3,edt4,edt5,edt6;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ques_fom);


        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        text3 = findViewById(R.id.text3);
        que = findViewById(R.id.que);

        edt1 = findViewById(R.id.op);
        edt2 = findViewById(R.id.op1);
        edt3 = findViewById(R.id.op2);
        edt4 = findViewById(R.id.op3);
        edt5 = findViewById(R.id.op4);
        edt6 = findViewById(R.id.op5);


        but1 = findViewById(R.id.but1);

        but1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "Question added successfully", Toast.LENGTH_LONG).show();

//
//                ConnectionHelper myDB = new ConnectionHelper(Ques_form.this);
//                myDB.addQue(edt1.getText().toString().trim(),
//                        edt2.getText().toString().trim(),
//                        edt3.getText().toString().trim(),
//                        edt4.getText().toString().trim(),
//                        edt5.getText().toString().trim(),
//                        edt6.getText().toString().trim());

            }
        });

    }
}