/*
ReactionTime stores user's reflex and count down timer information.

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

import java.util.Random;

public class ReactionTime {

    private int end;
    private int start;
    private int reflex;
    private int wait;
    private boolean isTick;

    public ReactionTime() {
        this.isTick = false;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd() {
        this.end = (int) System.currentTimeMillis();
    }

    public int getStart() {
        return start;
    }

    public void setStart() {
        this.start = (int) System.currentTimeMillis();
    }

    public int getReflex() {
        return reflex;
    }

    public void setReflex() {
        this.reflex = end - start;
    }

    public int getWait() {
        return wait;
    }

    public void setWait() {
        Random random = new Random();
        this.wait = random.nextInt(1991) + 10; // random waiting time in range 10-2000ms
    }

    public boolean isTick() {
        return isTick;
    }

    public void setIsTick(boolean isTick) {
        this.isTick = isTick;
    }

    public String getReactionTimerResult() {
        setEnd();
        setReflex();
        String result = "Waiting time: " + wait + " ms\n";
        result += "Reflex time: " + reflex + " ms\n";
        return result;
    }

}
