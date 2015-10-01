/*
GameshowActivity served as buzzers for multiplayer trivia game.
It determines who gets to answer by who pressed the buzzer first.

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
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class GameshowActivity extends Activity {

    private Button player1_button;
    private Button player2_button;
    private Button player3_button;
    private Button player4_button;

    private GameManager gameManager = new GameManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameshow);

        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        player1_button = (Button) findViewById(R.id.player1_button);
        player2_button = (Button) findViewById(R.id.player2_button);
        player3_button = (Button) findViewById(R.id.player3_button);
        player4_button = (Button) findViewById(R.id.player4_button);

        player1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameManager.getBuzzerCount().setPlayer(1);
                showClickedButton(1);
                showResult();
            }
        });

        player2_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameManager.getBuzzerCount().setPlayer(2);
                showClickedButton(2);
                showResult();
            }
        });

        player3_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameManager.getBuzzerCount().setPlayer(3);
                showClickedButton(3);
                showResult();
            }
        });

        player4_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameManager.getBuzzerCount().setPlayer(4);
                showClickedButton(4);
                showResult();
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
        gameManager.getBuzzerCount().setMode(2);
    }

    private void createThreePlayersView() {
        player1_button.setVisibility(View.VISIBLE);
        player2_button.setVisibility(View.VISIBLE);
        player3_button.setVisibility(View.VISIBLE);
        gameManager.getBuzzerCount().setMode(3);
    }

    private void createFourPlayersView() {
        player1_button.setVisibility(View.VISIBLE);
        player2_button.setVisibility(View.VISIBLE);
        player3_button.setVisibility(View.VISIBLE);
        player4_button.setVisibility(View.VISIBLE);
        gameManager.getBuzzerCount().setMode(4);
    }

    public void showClickedButton(int playerNum) {
        AlertDialog alertDialog = new AlertDialog.Builder(GameshowActivity.this).create();
        alertDialog.setMessage("PLAYER " + playerNum + "!");
        alertDialog.show();
    }

    public void showResult() {
        gameManager.loadCurrentBuzzerCount(this);
        gameManager.getBuzzerCount().updateCounter();
        gameManager.saveNewBuzzerCount(this);
    }

    /* taken from Android Developers
       http://developer.android.com/reference/android/app/DialogFragment.html
       (C) 2015 Android Developers modified by Yunita */

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