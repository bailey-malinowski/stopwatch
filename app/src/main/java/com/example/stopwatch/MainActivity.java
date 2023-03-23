package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity { // beginning of class >>>

    TextView slot1;
    TextView slot2;
    TextView slot3;
    Button start;
    Button clear;
    Button stop;
    TextView text;

    int time; // for slot 2
    int time2; // for slot 1
    int time3; // for slot 3

    CountEvent event;
    Handler handler;
    CountEvent slot1event;
    CountEvent slot3event;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // onCreate >>>
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        slot2 = findViewById(R.id.Slot2);
        slot1 = findViewById(R.id.Slot1);
        slot3 = findViewById(R.id.Slot3);
        start = findViewById(R.id.start);
        clear = findViewById(R.id.clear);
        stop = findViewById(R.id.stop);
        text = findViewById(R.id.f);

        time = 0;
        time2 = 0;
        time3 = 0;

        event = new CountEvent();
        slot1event = new CountEvent();
        slot3event = new CountEvent();
        handler = new Handler();

        if (savedInstanceState != null){
            time = savedInstanceState.getInt("time");
            time2 = savedInstanceState.getInt("time2");
            time3 = savedInstanceState.getInt("time3");

            slot2.setText(time+"");
            slot1.setText(time2+"");
            slot3.setText(time3+"");
        }

        start.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                handler.postDelayed(event, 475);
                handler.postDelayed(slot1event, 650);
                handler.postDelayed(slot3event, 350);
                if (time == time2){
                    if(time2 == time3){
                        if (time3 == time){
                        text.setText("!!JACKPOT!!");
                        }
                    }
                    if(time2 != time3){
                        text.setText("YOU LOSE!! HAHA!!");
                    }
                }
                if (time != time2){
                    text.setText("YOU LOSE!! HAHA!!");
                }
                if (time != time3){
                    text.setText("YOU LOSE!! HAHA!!");
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() { // is good
           public void onClick(View view){
               slot2.setText("0");
               slot1.setText("0");
               slot3.setText("0");
           }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                handler.postDelayed(event, 60000);
                handler.postDelayed(slot1event, 60000);
                handler.postDelayed(slot3event, 60000);
            }
        });

    } // end for onCreate ^^^

    // is good
    private class CountEvent implements Runnable { // this is for the second one
        public void run(){
            time++;
            if (time>3){
                time=0;
            }
            slot2.setText(time+"");
            handler.postDelayed(event, 1000);
        }
    }
    // is good
    private class CountEvent2 implements Runnable { // this is for the first one
        public void run() {
            time2++;
            if (time2 > 3) {
                time2 = 0;
            }
            slot1.setText(time2 + "");
            handler.postDelayed(slot1event, 1050);
        }
    }
    // is good
    private class CountEvent3 implements Runnable { // this is for the third one
        public void run(){
            time3++;
            if (time3 > 3){
                time3 = 0;
            }
            slot3.setText(time3 + "");
        }
    }
    // is good
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("time", time); // for slot 2
        savedInstanceState.putInt("time2", time2); // for slot 1
        savedInstanceState.putInt("time3", time3); // for slot 3
        super.onSaveInstanceState(savedInstanceState);
    }
} // end of class ^^^