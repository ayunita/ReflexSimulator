package com.example.yunita.reflexsimulator;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class StatisticActivity extends Activity {

    private TextView result;

    private BuzzerCount buzzerCount = new BuzzerCount();
    private ReactionTime reactionTime = new ReactionTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        result = (TextView)findViewById(R.id.stats_result);

        showResult(R.id.stats_result, getGameshowResult());
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

    public void showResult(int id, String gameResult){
        result = (TextView)findViewById(id);
        result.setText(gameResult);
    }

    public String getGameshowResult(){
        String results[] = loadFromFile("file2.sav");
        String result = "";
        if(results.length != 0){
            result += buzzerCount.getModeResult(2, results);
            result += buzzerCount.getModeResult(3, results);
            result += buzzerCount.getModeResult(4, results);
        } else {
            result = "You haven't played Gameshow Buzzer :(";
        }
        return result;
    }

    public String getReactionTimerResult(){
        return "";
    }

    /* taken from Ualberta CMPUT 301, CMPUT 301 Lab Materials
       https://github.com/joshua2ua/lonelyTwitter 2015 modified by Yunita */

    private String[] loadFromFile(String filename) {
        ArrayList<String> results = new ArrayList<String>();
        try {
            FileInputStream fis = openFileInput(filename);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            String line = in.readLine();
            while (line != null) {
                if(line != ""){
                    results.add(line);
                }
                line = in.readLine();
            }
        } catch (FileNotFoundException e) {
            //
        } catch (IOException e) {
            Log.w("IOEXCEPTION", "EXCEPTION");
        }
        return results.toArray(new String[results.size()]);
    }

}
