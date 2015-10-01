/*
ReactionTimerActivity performs the reaction times measurement
by clicking the button.

Copyright (C) 2015  Andriani Yunita

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.example.yunita.reflexsimulator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ReactionTimerActivity extends Activity {

    private TextView start_signal;
    private TextView reflex_result;
    private ImageButton reflex_button;
    private Button restart_button;

    static boolean isDismiss = false;
    private GameManager gameManager = new GameManager();

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

        gameManager.getReactionTime().setWait();
        CountDownTimer timer = new CountDownTimer(gameManager.getReactionTime().getWait(), 1) {
            @Override
            public void onTick(long l) {
                gameManager.getReactionTime().setIsTick(true);
                if(reflex_button.isPressed()){
                    start_signal.setText("Too fast!");
                    start();
                }
            }

            @Override
            public void onFinish() {
                gameManager.getReactionTime().setIsTick(false);
                start_signal.setText("START!");
                gameManager.getReactionTime().setStart();
            }
        }.start();
    }

    public void showResult() {
        if(!gameManager.getReactionTime().isTick()) {
            reflex_button.setEnabled(false);
            start_signal.setText("Good job!");
            reflex_button.setSelected(true);
            reflex_result.setText(gameManager.getReactionTimerResult());
            gameManager.saveReflexTime(this);
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

}
