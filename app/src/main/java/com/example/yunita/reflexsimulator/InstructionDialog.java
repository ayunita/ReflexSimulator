package com.example.yunita.reflexsimulator;

/* http://developer.android.com/guide/topics/ui/dialogs.html#AlertDialog

 */

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

public class InstructionDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.activity_instruction_dialog, null))
                // Add action buttons
                .setNegativeButton("DISMISS", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        InstructionDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();


    }

}
