package com.example.cconsiteapsn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
public Button button1,button2;
 public void init(){
     button1=(Button)findViewById(R.id.button1);
     button1.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent generate=new Intent(MainActivity.this,GenerateQR.class);
             startActivity(generate);
         }
     });
 }

    public void init1(){
        button2=(Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scanner=new Intent(MainActivity.this,Scan.class);
                startActivity(scanner);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        init1();
    }
}
