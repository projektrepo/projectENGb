package com.example.projecteng;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class learnlan extends AppCompatActivity {


    public Button przyciskczasy;
    public Button przyciskzadania;
    public void init()
    {
        przyciskczasy=(Button)findViewById(R.id.czasy);
        przyciskczasy.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             Intent czlearn = new Intent(learnlan.this,engczasy.class);
                                             startActivity(czlearn);
                                         }
                                     }
        );
        przyciskzadania=(Button)findViewById(R.id.button4);
        przyciskzadania.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             Intent zadan = new Intent(learnlan.this,exercises.class);
                                             startActivity(zadan);
                                         }
                                     }
        );
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learnlan);
        init();
    }
}
