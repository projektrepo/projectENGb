package com.example.projecteng;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class engczasy extends AppCompatActivity {
    TextView simpletext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engczasy);

        simpletext = (TextView) findViewById(R.id.simpletext);
        simpletext.setMovementMethod(new ScrollingMovementMethod());
        String text="";
        try{
            InputStream is = getAssets().open("presentsimp.txt");
            //Scanner is = new Scanner(new File("filename.txt"));

            int size =is.available();
            byte[] buffer=new byte[size];
            is.read(buffer);
            is.close();
            text=new String(buffer);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        simpletext.setText(text);
        //StringBuffer sbuffer = new StringBuffer();
        //InputStream prestext = this.getResources().openRawResource(R.raw.presentsimp);


    }
}
