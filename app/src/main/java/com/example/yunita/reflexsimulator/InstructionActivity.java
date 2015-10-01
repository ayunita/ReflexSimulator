/*
InstructionActivity performs as a dialog that shows the instruction
to play Reaction Timer.

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
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

public class InstructionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_instruction);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        /*  taken from stackoverflow
            stackoverflow.com/questions/15022152/how-to-animate-gif-images-in-an-android
           (C) 2015 Shobhit Puri  modified by Yunita. */

        final ImageView gif = (ImageView)findViewById(R.id.instruction_gif);
        gif.setBackgroundResource(R.drawable.instruction_gif);
        gif.post(new Runnable() {
            @Override
            public void run() {
                AnimationDrawable frameAnimation = (AnimationDrawable) gif.getBackground();
                frameAnimation.start();
            }
        });
    }

    public void dismissInstruction(View view) {
        ReactionTimerActivity.isInstructionDismiss = true;
        finish();
    }

}
