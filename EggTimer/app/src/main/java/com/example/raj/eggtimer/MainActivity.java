package com.example.raj.eggtimer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar timerSeekBar = (SeekBar)findViewById(R.id.seekBar);
        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);

        final TextView tv = (TextView)findViewById(R.id.textView);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int minutes = (int)progress/60;
                int seconds = progress%60;
                String secondString  = Integer.toString(seconds);
                if(seconds<10)
                    secondString="0"+secondString;


                tv.setText(Integer.toString(minutes)+":"+ secondString);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
