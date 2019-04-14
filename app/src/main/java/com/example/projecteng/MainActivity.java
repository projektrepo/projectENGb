package com.example.projecteng;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public Button buttonone;
    public Button secbutton;
    public void init()
    {
        buttonone=(Button)findViewById(R.id.button);
        buttonone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exerc = new Intent(MainActivity.this,learnlan.class);
                startActivity(exerc);
            }
        }
        );
        secbutton=(Button)findViewById(R.id.button2);
        secbutton.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             Intent slangd = new Intent(MainActivity.this,slangdict.class);
                                             startActivity(slangd);
                                         }
                                     }
        );
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
}
