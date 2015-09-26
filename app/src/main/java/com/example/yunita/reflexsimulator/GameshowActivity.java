package com.example.yunita.reflexsimulator;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class GameshowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameshow);

        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

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

    public void createTwoPlayersView() {
        TextView test = (TextView) findViewById(R.id.test);
        test.setText("2");

    }

    public void createThreePlayersView() {
        TextView test = (TextView) findViewById(R.id.test);
        test.setText("3");

    }

    public void createFourPlayersView() {
        TextView test = (TextView) findViewById(R.id.test);
        test.setText("4");

    }

    void showDialog() {
        DialogFragment newFragment = PlayerDialogFragment.newInstance(R.string.title_activity_gameshow);
        newFragment.show(getFragmentManager(), "dialog");
    }

    // taken from http://developer.android.com/reference/android/app/DialogFragment.html
    // modified by yunita

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