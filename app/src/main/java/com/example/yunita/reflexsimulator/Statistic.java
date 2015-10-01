/*
Statistic performs calculation to measure the maximum, minimum, mean, and
median user's reflex and shows the result of gameshow buzzer.

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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Statistic {

    private Utilities util = new Utilities();
    private GameManager gameManager = new GameManager();

    public Statistic() {

    }

    public String getReactionTimerResult(Context context) {
        String results[] = gameManager.loadFromFile(context, "file1.sav");
        String output = "";
        if (results.length != 0) {
            double lastTen[] = util.getRangedSortedArray(10, util.convertToDoubleArray(results));
            double lastHundred[] = util.getRangedSortedArray(100, util.convertToDoubleArray(results));
            output += "MAX last 10: " + String.format("%.2f", max(lastTen)) + " ms\n";
            output += "MAX last 100: " + String.format("%.2f", max(lastHundred)) + " ms\n";
            output += "MIN last 10: " + String.format("%.2f", min(lastTen)) + " ms \n";
            output += "MIN last 100: " + String.format("%.2f", min(lastHundred)) + " ms \n";
            output += "MED last 10: " + String.format("%.2f", median(lastTen)) + " ms \n";
            output += "MED last 100: " + String.format("%.2f", median(lastHundred)) + " ms \n";
            output += "AVE last 10: " + String.format("%.2f", average(lastTen)) + " ms \n";
            output += "AVE last 100: " + String.format("%.2f", average(lastHundred)) + " ms \n";
        } else {
            output = "You haven't played Reaction Timer :(";
        }
        return output;
    }

    public String getGameshowResult(Context context) {
        String results[] = gameManager.loadFromFile(context, "file2.sav");
        String output = "";
        if (results.length != 0) {
            output += gameManager.getBuzzerCount().getModeResult(2, results);
            output += gameManager.getBuzzerCount().getModeResult(3, results);
            output += gameManager.getBuzzerCount().getModeResult(4, results);
        } else {
            output = "You haven't played Gameshow Buzzer :(";
        }
        return output;
    }

    public void clearStatistic(Context context) {
        try {
            FileOutputStream fos1 = context.openFileOutput("file1.sav", 0);
            FileOutputStream fos2 = context.openFileOutput("file2.sav", 0);
            fos1.close();
            fos2.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    public double max(double rangedSortedArray[]) {
        return rangedSortedArray[rangedSortedArray.length - 1];
    }

    public double min(double rangedSortedArray[]) {
        return rangedSortedArray[0];
    }

    public double median(double rangedSortedArray[]) {
        double median = 0;
        int size = rangedSortedArray.length;
        int mid = size / 2;
        if (size % 2 == 0) {
            double left = rangedSortedArray[mid - 1];
            double right = rangedSortedArray[mid];
            median = (left + right) / 2;
        } else {
            median = rangedSortedArray[mid];
        }
        return median;
    }

    public double average(double rangedSortedArray[]) {
        double mean = 0;
        for (double i : rangedSortedArray) {
            mean += i;
        }
        return (mean / rangedSortedArray.length);
    }
}
