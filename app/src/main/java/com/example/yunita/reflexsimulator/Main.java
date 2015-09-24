package com.example.yunita.reflexsimulator;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Main extends AppCompatActivity {

    private final static String ICON = "heydings_icons.ttf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setMainLayout();
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
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

    public void setMainLayout() {
        Typeface icon_font = Typeface.createFromAsset(getAssets(), ICON);
        TextView icon1 = (TextView) findViewById(R.id.reaction_timer_icon);
        TextView icon2 = (TextView) findViewById(R.id.gameshow_icon);
        TextView icon3 = (TextView) findViewById(R.id.statistic_icon);

        icon1.setTypeface(icon_font);
        icon2.setTypeface(icon_font);
        icon3.setTypeface(icon_font);
    }

    public void gotoSingleMode(View view) {
        Intent intent = new Intent(this, ReactionTimer.class);
        startActivity(intent);

    }

    public void gotoPartyMode(View view) {
        Intent intent = new Intent(this, Gameshow.class);
        startActivity(intent);
    }

    public void gotoStatistic(View view) {
        Intent intent = new Intent(this, Statistic.class);
        startActivity(intent);
    }
}
