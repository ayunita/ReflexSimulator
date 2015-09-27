package com.example.yunita.reflexsimulator;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GameshowActivity extends Activity {

    private Button player1_button;
    private Button player2_button;
    private Button player3_button;
    private Button player4_button;

    private int playerMode;
    private int counters[] = new int[9];

    private final static String FILENAME = "file2.sav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameshow);

        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        player1_button = (Button)findViewById(R.id.player1_button);
        player2_button = (Button)findViewById(R.id.player2_button);
        player3_button = (Button)findViewById(R.id.player3_button);
        player4_button = (Button)findViewById(R.id.player4_button);

        player1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setButtonEvent(player1_button.getText().toString());
            }
        });

        player2_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setButtonEvent(player2_button.getText().toString());
            }
        });

        player3_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setButtonEvent(player3_button.getText().toString());
            }
        });

        player4_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setButtonEvent(player4_button.getText().toString());
            }
        });

        showDialog();

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
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
    }

    private void createTwoPlayersView() {
        player1_button.setVisibility(View.VISIBLE);
        player2_button.setVisibility(View.VISIBLE);
        playerMode = 2;
    }

    private void createThreePlayersView() {
        player1_button.setVisibility(View.VISIBLE);
        player2_button.setVisibility(View.VISIBLE);
        player3_button.setVisibility(View.VISIBLE);
        playerMode = 3;
    }

    private void createFourPlayersView() {
        player1_button.setVisibility(View.VISIBLE);
        player2_button.setVisibility(View.VISIBLE);
        player3_button.setVisibility(View.VISIBLE);
        player4_button.setVisibility(View.VISIBLE);
        playerMode = 4;
    }

    private void setButtonEvent(String player){
        int playerNumber = Integer.parseInt(player);
        showResult(playerNumber);
        loadFromFile();
        updateCounter(playerMode, playerNumber);
        saveInFile();
    }

    public void showResult(int playerNum){
        AlertDialog alertDialog = new AlertDialog.Builder(GameshowActivity.this).create();
        alertDialog.setMessage("PLAYER " + playerNum + "!");
        alertDialog.show();
    }

    private void updateCounter(int mode, int player){
        int startIndex = 0;
        switch(mode){
            case 2: startIndex = 0;
                break;
            case 3: startIndex = 2;
                break;
            case 4: startIndex = 5;
                break;
        }
        int playerIndex = startIndex + player - 1;
        int newCounter = counters[playerIndex] + 1; // index = player - 1
        counters[playerIndex] = newCounter;
    }

    /* taken from Ualberta CMPUT 301, CMPUT 301 Lab Materials
       https://github.com/joshua2ua/lonelyTwitter 2015 modified by Yunita */

    private void loadFromFile() {
        int i = 0;
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            String line = in.readLine();
            while (line != null) {
                if(line != ""){
                    counters[i] = Integer.parseInt(line);
                    i++;
                }
                line = in.readLine();
            }
        } catch (FileNotFoundException e) {
            createNewFileSave();
        } catch (IOException e) {
            Log.w("IOEXCEPTION", "EXCEPTION");
        }
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            for(int i = 0; i < 9; i++) {
                fos.write(new String(Integer.toString(counters[i]) + "\n").getBytes());
            }
            fos.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }

    private void createNewFileSave(){
        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            for(int i = 0; i < 9; i++) {
                counters[i] = 0; // initialize counters when file is not found
                fos.write(new String(Integer.toString(0) + "\n").getBytes());
            }
            fos.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }

    /* taken from Android Developers
        http://developer.android.com/reference/android/app/DialogFragment.html 2015
        modified by Yunita */

    private void showDialog() {
        DialogFragment newFragment = PlayerDialogFragment.newInstance(R.string.title_activity_gameshow);
        newFragment.show(getFragmentManager(), "dialog");
    }

    public static class PlayerDialogFragment extends DialogFragment {

        public static PlayerDialogFragment newInstance(int title) {
            PlayerDialogFragment frag = new PlayerDialogFragment();
            Bundle args = new Bundle();
            args.putInt("title", title);
            frag.setArguments(args);
            return frag;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            int title = getArguments().getInt("title");

            return new AlertDialog.Builder(getActivity())
                    .setTitle(title)
                    .setItems(R.array.players, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int index) {
                            index += 1;
                            switch (index) {
                                case 1:
                                    ((GameshowActivity) getActivity()).createTwoPlayersView();
                                    break;
                                case 2:
                                    ((GameshowActivity) getActivity()).createThreePlayersView();
                                    break;
                                case 3:
                                    ((GameshowActivity) getActivity()).createFourPlayersView();
                                    break;
                            }
                        }
                    })
                    .create();
        }
    }

}