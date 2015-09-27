package com.example.yunita.reflexsimulator;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final static String ICON = "heydings_icons.ttf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainv2);

        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
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

    public void gotoSingleMode(View view) {
        Intent intent = new Intent(this, ReactionTimerActivity.class);
        startActivity(intent);
    }

    public void gotoPartyMode(View view) {
        Intent intent = new Intent(this, GameshowActivity.class);
        startActivity(intent);
    }

    public void gotoStatistic(View view) {
        Intent intent = new Intent(this, StatisticActivity.class);
        startActivity(intent);
    }
}
