/*
BuzzerCount stores the information of gameshow mode,
and updates players' buzzer count.

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

public class BuzzerCount {

    private int counters[] = new int[9];
    private int mode;
    private int player;

    public BuzzerCount() {

    }

    public int[] getCounters() {
        return counters;
    }

    public void setCounters(int[] counters) {
        this.counters = counters;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getStartIndexMode(int mode) {
        int startIndex = 0;
        switch (mode) {
            case 2:
                startIndex = 0;
                break;
            case 3:
                startIndex = 2;
                break;
            case 4:
                startIndex = 5;
                break;
        }
        return startIndex;
    }

    public void updateCounter() {
        int startIndex = getStartIndexMode(mode);
        int playerIndex = startIndex + player - 1;
        int newCounter = counters[playerIndex] + 1; // index = player - 1
        counters[playerIndex] = newCounter;
    }

    public String getModeResult(int mode, String[] savedData) {
        int start = getStartIndexMode(mode);
        int end = start + mode;
        int player = 1;
        String result = "MODE: " + mode + "\n";
        for (int i = start; i < end; i++) {
            if (!savedData[i].equals("0") && !savedData[i].equals("1")) {
                result += "Player " + player + ": " + savedData[i] + " buzzes\n";
            } else {
                result += "Player " + player + ": " + savedData[i] + " buzz\n";
            }
            player++;
        }
        return result;
    }

}
