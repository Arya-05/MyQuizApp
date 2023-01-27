package com.example.myquizapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;

public class LoginActivity extends AppCompatActivity {

    ImageView login;
    Button but1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        login = findViewById(R.id.img);
        but1=findViewById(R.id.but2);



        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popup1 = new PopupMenu(LoginActivity.this, but1);
                popup1.getMenuInflater().inflate(R.menu.poopup_menu, popup1.getMenu());

                popup1.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {


                        int id = item.getItemId();
                        switch (id) {
                            case R.id.i1:
                                Intent i1 = new Intent(LoginActivity.this, StartActivity.class);
                                startActivity(i1);
                                return true;

                            case R.id.i2:
                                Intent i2 = new Intent(LoginActivity.this, Ques_form.class);
                                startActivity(i2);
                                return true;

                        }
                        return true;


                    }

                });

                popup1.show();//showing popup menu
            }

        });
}
}