package com.example.yunita.reflexsimulator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class ReactionTimerActivity extends Activity {

    private static final String FILENAME = "file1.sav";

    private TextView start_signal;
    private TextView reflex_result;
    private ImageButton reflex_button;
    private Button restart_button;

    static boolean isDismiss = false;

    private CountDownTimer timer;
    private int wait_time;
    private boolean isTickDone;

    private int startTime;
    private int endTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_timer);

        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        start_signal = (TextView) findViewById(R.id.start_signal);
        reflex_result = (TextView) findViewById(R.id.reflex_result);
        reflex_button = (ImageButton) findViewById(R.id.reflex_button);
        restart_button = (Button)findViewById(R.id.restart_button);

        Intent instruction_intent = new Intent(this, InstructionActivity.class);
        startActivity(instruction_intent);

        reflex_button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    showResult();
                }
                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up custom_reflex_button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isDismiss) {
            start();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        isDismiss = false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
    }

    public void start() {
        restart_button.setVisibility(View.INVISIBLE);
        reflex_button.setEnabled(true);

        /* taken from Android Developers
            http://developer.android.com/reference/android/os/CountDownTimer.html 2015
            modified by Yunita*/

        timer = new CountDownTimer(randomWaitTime(), 1) {
            @Override
            public void onTick(long l) {
                isTickDone = false;
                if(reflex_button.isPressed()){
                    start_signal.setText("Too fast!");
                    start();
                }
            }

            @Override
            public void onFinish() {
                isTickDone = true;
                start_signal.setText("START!");
                startTime = (int) System.currentTimeMillis();
            }
        }.start();
    }

    public int randomWaitTime() {
        Random random = new Random();
        wait_time = random.nextInt(1991) + 10; // random waiting time in range 10-2000ms
        return wait_time;
    }

    public int getReflexTime(){
        return endTime - startTime;
    }

    public void showResult() {
        if (isTickDone == true) {
            reflex_button.setEnabled(false);
            endTime = (int) System.currentTimeMillis();
            start_signal.setText("Good job!");

            // show the result and the button has been clicked
            reflex_button.setSelected(true);
            int reflexTime = getReflexTime();
            reflex_result.setText(printOutResult(wait_time, reflexTime));

            // write result into a file
            saveInFile(reflexTime);

            // restart game
            restartGame();
        }

    }

    public String printOutResult(int wait_time, int reflex_time) {
        String result = "Waiting time: " + wait_time + " ms\n";
        result += "Reflex time: " + reflex_time + " ms\n";
        return result;
    }

    public void restartGame(){
        restart_button.setVisibility(View.VISIBLE);
        restart_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reflex_button.setSelected(false);
                reflex_result.setText("");
                start_signal.setText("");
                reflex_button.setActivated(false);
                start();
            }
        });
    }

    /* taken from Ualberta CMPUT 301, CMPUT 301 Lab Materials
        https://github.com/joshua2ua/lonelyTwitter 2015 modified by Yunita */

    private void saveInFile(int reflexTime) {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_APPEND);
            fos.write(new String(Integer.toString(reflexTime)+"\n").getBytes());
            fos.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }

}
