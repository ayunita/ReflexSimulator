package com.example.yunita.reflexsimulator;

import java.util.Random;

/**
 * Created by yunita on 26/09/15.
 */

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

    public String printOutResult() {
        String result = "Waiting time: " + wait + " ms\n";
        result += "Reflex time: " + reflex + " ms\n";
        return result;
    }

}
