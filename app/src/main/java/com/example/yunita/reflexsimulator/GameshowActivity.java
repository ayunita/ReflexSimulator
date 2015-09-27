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
                showResult(player1_button.getText().toString());
            }
        });

        player2_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showResult(player2_button.getText().toString());
            }
        });

        player3_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showResult(player3_button.getText().toString());
            }
        });

        player4_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showResult(player4_button.getText().toString());
            }
        });

        showDialog();
        loadFromFile(2);
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

    public void showResult(String playerNum){
        AlertDialog alertDialog = new AlertDialog.Builder(GameshowActivity.this).create();
        alertDialog.setMessage("PLAYER " + playerNum + "!");
        alertDialog.show();
    }

    // taken from ...
    private void loadFromFile(int number) {
        ArrayList<Integer> counters = new ArrayList<Integer>();
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            String line = in.readLine();
            while (line != null) {
                counters.add(Integer.parseInt(line));
                line = in.readLine();
            }
        } catch (FileNotFoundException e) {
            Log.w("LOADFROMFILE", "FILENOFOUND");
            for(int i = 0; i < number; i++){
                counters.add(0);
                Log.w(">>>>>>>>>>>>>", counters.get(i).toString());
            }
        } catch (IOException e) {
            Log.w("IOEXCEPTION", "EXCEPTION");
        }
    }

    // taken from http://developer.android.com/reference/android/app/DialogFragment.html
    // modified by yunita

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