/*
GameManager holds the reaction time and buzzer count data,
and stores them into files. It is also capable to load those
data from files.

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

import android.content.Context;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GameManager {

    private final static String FILENAME1 = "file1.sav";
    private final static String FILENAME2 = "file2.sav";

    private ReactionTime reactionTime;
    private BuzzerCount buzzerCount;

    public GameManager() {
        reactionTime = new ReactionTime();
        buzzerCount = new BuzzerCount();
    }

    public ReactionTime getReactionTime() {
        return reactionTime;
    }

    public void setReactionTime(ReactionTime reactionTime) {
        this.reactionTime = reactionTime;
    }

    public BuzzerCount getBuzzerCount() {
        return buzzerCount;
    }

    public void setBuzzerCount(BuzzerCount buzzerCount) {
        this.buzzerCount = buzzerCount;
    }

    /* taken from Ualberta CMPUT 301, CMPUT 301 Lab Materials
       https://github.com/joshua2ua/lonelyTwitter 2015 modified by Yunita */

    public void saveReflexTime(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME1, Context.MODE_APPEND);
            fos.write(new String(Integer.toString(reactionTime.getReflex())+"\n").getBytes());
            fos.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    public void getCurrentBuzzerCount(Context context) {
        try {
            int i = 0;
            int counters[] = buzzerCount.getCounters();
            FileInputStream fis = context.openFileInput(FILENAME2);
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

        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    public void saveNewBuzzerCount(Context context) {
        try {
            int counters[] = buzzerCount.getCounters();
            FileOutputStream fos = context.openFileOutput(FILENAME2, 0);
            for(int i = 0; i < 9; i++) {
                fos.write(new String(Integer.toString(counters[i]) + "\n").getBytes());
            }
            fos.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    public String[] loadFromFile(Context context,String filename) {
        ArrayList<String> results = new ArrayList<String>();
        try {
            FileInputStream fis = context.openFileInput(filename);
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
            //
        }
        return results.toArray(new String[results.size()]);
    }

}