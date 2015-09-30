package com.example.yunita.reflexsimulator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReactionTimerActivity extends Activity {

    private TextView start_signal;
    private TextView reflex_result;
    private ImageButton reflex_button;
    private Button restart_button;

    private static final String FILENAME = "file1.sav";
    static boolean isDismiss = false;

    private ReactionTime reactionTime = new ReactionTime();;

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
            reflex_button.setVisibility(View.VISIBLE);
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

    private void setToDefaultState(){
        reflex_button.setSelected(false);
        reflex_result.setText("");
        start_signal.setText("");
        reflex_button.setActivated(false);
    }

    public void start() {
        restart_button.setVisibility(View.INVISIBLE);
        reflex_button.setEnabled(true);

        /* taken from Android Developers
            http://developer.android.com/reference/android/os/CountDownTimer.html 2015
            modified by Yunita*/
        reactionTime.setWait();
        CountDownTimer timer = new CountDownTimer(reactionTime.getWait(), 1) {
            @Override
            public void onTick(long l) {
                reactionTime.setIsTick(false);
                if(reflex_button.isPressed()){
                    start_signal.setText("Too fast!");
                    start();
                }
            }

            @Override
            public void onFinish() {
                reactionTime.setIsTick(true);
                start_signal.setText("START!");
                reactionTime.setStart();
            }
        }.start();
    }

    public void showResult() {
        if (reactionTime.isTick()) {
            reflex_button.setEnabled(false);
            reactionTime.setEnd();
            start_signal.setText("Good job!");

            // show the result and the button has been clicked
            reflex_button.setSelected(true);
            reactionTime.setReflex();
            reflex_result.setText(reactionTime.printOutResult());

            // write result into a file
            saveInFile(reactionTime.getReflex());

            // restart game
            restart_button.setVisibility(View.VISIBLE);
            restartGame();
        }

    }

    public void restartGame(){
        restart_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setToDefaultState();
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
