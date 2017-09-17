package com.hfad.stopwatch;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class StopwatchActivity extends AppCompatActivity {

    private long milliseconds;
    private boolean running;
    private long prevTime;
    TextView timeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        timeView = (TextView) findViewById(R.id.time_view);
        loadPreferences();
        if (running){
            addElapsedTimeToTimer();
            runTimer();
        }
        else{
            if (milliseconds != 0){
                timeView.setText(getTimeString(milliseconds));
            }
        }
    }

    @Override
    protected void onDestroy() {
        savePreferences();
        super.onDestroy();
    }

    public void onClickStart(View view){
        if (!running) {
            running = true;
            runTimer();
        }
    }

    public void onClickStop(View view){
        running = false;
    }

    public void onClickReset(View view){
        running = false;
        milliseconds = 0;
        clearTimeView();
    }

    private void clearTimeView(){
        timeView.setText(getTimeString(0));
    }

    private String getTimeString(long milliseconds){
        int millisecs = (int)(milliseconds % 1000);
        int secs = (int)((milliseconds/1000)% 60);
        int minutes = (int)((milliseconds/1000/60)%60);
        long hours = milliseconds/1000/60/60;


        String time = String.format("%d:%02d:%02d:%d", hours, minutes, secs,millisecs/100);
        return time;
    }

    private void savePreferences(){
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong("milliseconds", milliseconds);
        editor.putBoolean("running", running);
        if (running)
            editor.putLong("prevTime", System.currentTimeMillis());
        editor.commit();
    }

    private void loadPreferences(){
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        milliseconds = sharedPreferences.getLong("milliseconds",0);
        running = sharedPreferences.getBoolean("running",false);
        prevTime = sharedPreferences.getLong("prevTime",0);
    }

    private void addElapsedTimeToTimer(){
        long elapsedTime = System.currentTimeMillis() - prevTime;
        milliseconds += elapsedTime;
        timeView.setText(getTimeString(milliseconds));
    }

    private void runTimer() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (running) {
                    milliseconds+=100;
                    timeView.setText(getTimeString(milliseconds));
                    handler.postDelayed(this, 100);
                }
            }
        });
    }

}
