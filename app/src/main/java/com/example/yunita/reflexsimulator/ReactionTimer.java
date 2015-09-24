package com.example.yunita.reflexsimulator;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class ReactionTimer extends AppCompatActivity {

    private DialogFragment newFragment;

    private TextView start_signal;
    private TextView reflex_result;
    private ImageButton reflex_button;

    private CountDownTimer timer;
    private int wait_time;
    private boolean isTickDone;

    static boolean isDismiss = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reaction_timer);

        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        start_signal = (TextView) findViewById(R.id.start_signal);
        reflex_result = (TextView) findViewById(R.id.reflex_result);
        reflex_button = (ImageButton) findViewById(R.id.reflex_button);

        Intent instruction_intent = new Intent(this, Instruction.class);
        startActivity(instruction_intent);

        reflex_button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) { //modify the motion event!
                    showResult();
                }
                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up custom_button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.w("STATE 1", "ONSTART");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.w("STATE 2", "ONRESUME");
        if (isDismiss) {
            Log.w("DIALOG STATE: ", "TRUE");
            start();
        } else {
            Log.w("DIALOG STATE: ", "FALSE");
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.w("STATE 3", "ONPAUSE");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.w("STATE 4", "ONSTOP");
        isDismiss = false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
    }

    public void start() {
        /*taken from http://developer.android.com/reference/android/os/CountDownTimer.html
          modified by yunita
        */
        timer = new CountDownTimer(randomWaitTime(), 1) {
            @Override
            public void onTick(long l) {
                isTickDone = false;
            }

            @Override
            public void onFinish() {
                isTickDone = true;
                start_signal.setText("START!");
            }
        }.start();
    }

    public int randomWaitTime() {
        Random random = new Random();
        wait_time = random.nextInt(1991) + 10;
        return wait_time;
    }

    public void showResult() {
        if (isTickDone == true) {
            start_signal.setText("");
            // show the result and the button has been clicked
            reflex_button.setSelected(true);
            reflex_result.setText(printOutResult(wait_time, 5000));
            // write result
            // NEED IMPLEMENTATION
        } else {
            start_signal.setText("Too fast!"); // >> NEED TO BE FIXED!
            start();
        }

    }

    public void removeResult(View view) {
        reflex_result.setText("");
        //start again the game
    }

    public String printOutResult(int wait_time, int reflex_time) {
        String result = "Waiting time: " + wait_time + " ms\n";
        result += "Reflex time: " + reflex_time + " ms\n";
        return result;
    }

}
