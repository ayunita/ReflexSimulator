package com.example.yunita.reflexsimulator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class StatisticActivity extends AppCompatActivity {

    private TextView result;

    private BuzzerCount buzzerCount = new BuzzerCount();
    private ReactionTime reactionTime = new ReactionTime();

    private final static String FILENAME1 = "file1.sav";
    private final static String FILENAME2 = "file2.sav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        result = (TextView)findViewById(R.id.stats_result2);

        setResult(R.id.stats_result1, getReactionTimerResult());
        setResult(R.id.stats_result2, getGameshowResult());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_statistic, menu);
        return super.onCreateOptionsMenu(menu);
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

    public void setResult(int id, String gameResult){
        result = (TextView)findViewById(id);
        result.setText(gameResult);
    }

    public String getGameshowResult(){
        String results[] = loadFromFile(FILENAME2);
        String output = "";
        if(results.length != 0){
            output += buzzerCount.getModeResult(2, results);
            output += buzzerCount.getModeResult(3, results);
            output += buzzerCount.getModeResult(4, results);
        } else {
            output = "You haven't played Gameshow Buzzer :(";
        }
        return output;
    }

    public String getReactionTimerResult(){
        String results[] = loadFromFile(FILENAME1);
        String output = "";
        if(results.length != 0) {
            double lastTen[] = getRangedSortedArray(10, convertToDoubleArray(results));
            double lastHundred[] = getRangedSortedArray(100, convertToDoubleArray(results));
            output += "MAX 10: " + String.format("%.2f", reactionTime.max(lastTen)) + " ms\n";
            output += "MAX 100: " + String.format("%.2f", reactionTime.max(lastHundred)) + " ms\n";
            output += "MIN 10: " + String.format("%.2f", reactionTime.min(lastTen)) + " ms \n";
            output += "MIN 100: " + String.format("%.2f", reactionTime.min(lastHundred)) + " ms \n";
            output += "MED 10: " + String.format("%.2f", reactionTime.median(lastTen)) + " ms \n";
            output += "MED 100: " + String.format("%.2f", reactionTime.median(lastHundred)) + " ms \n";
            output += "AVE 10: " + String.format("%.2f", reactionTime.average(lastTen)) + " ms \n";
            output += "AVE 100: " + String.format("%.2f", reactionTime.average(lastHundred)) + " ms \n";
        } else {
            output = "You haven't played Reaction Timer :(";
        }
        return output;
    }

    public double[] convertToDoubleArray(String strings[]){
        double newArray[] = new double[strings.length];
        for(int i = 0; i < strings.length; i++){
            newArray[i] = Double.parseDouble(strings[i]);
        }
        return newArray;
    }

    public double[] getRangedSortedArray(int last, double savedData[]){
        if(savedData.length >= last) {
            int end = savedData.length; //exclusive
            int start = savedData.length - last;
            double subArray[] = Arrays.copyOfRange(savedData, start, end);
            Arrays.sort(subArray);
            return subArray;
        } else {
            Arrays.sort(savedData);
            return savedData;
        }
    }

    public void clearData(MenuItem item){
        try {
            FileOutputStream fos1 = openFileOutput(FILENAME1, 0);
            FileOutputStream fos2 = openFileOutput(FILENAME2, 0);
            fos1.close();
            fos2.close();
            setResult(R.id.stats_result1, getReactionTimerResult());
            setResult(R.id.stats_result2, getGameshowResult());
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }

    public void sendToEmail(MenuItem item){
        // CODE HERE
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
