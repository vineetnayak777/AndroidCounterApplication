package com.example.counterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    Button start, end;
    TextView counterValue;
    private static final String TAG = "thread";
    Handler mainHandler = new Handler();
    int count =0;
    boolean running = false;

    void startThread(){
        NewThread nObj = new NewThread();
        nObj.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterValue = findViewById(R.id.counterValue);

        start = findViewById(R.id.start);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 0;
                running = true;
                startThread();
            }
        });
        end = findViewById(R.id.stop);
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = false;
            }
        });
    }
    class NewThread extends Thread{
        @Override
        public void run() {

                while(running) {
                    count++;

                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            counterValue.setText(String.valueOf(count));
                        }
                    });

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        }
    }