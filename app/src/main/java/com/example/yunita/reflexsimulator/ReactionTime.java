package com.example.yunita.reflexsimulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

    public double max(double rangedSortedArray[]){
        return rangedSortedArray[rangedSortedArray.length-1];
    }

    public double min(double rangedSortedArray[]){
        return rangedSortedArray[0];
    }

    public double median(double rangedSortedArray[]){
        double median = 0;
        int size = rangedSortedArray.length;
        int mid = size / 2;
        if(size % 2 == 0){
            double left = rangedSortedArray[mid - 1];
            double right = rangedSortedArray[mid];
            median = (left + right)/2;
        } else {
            median = rangedSortedArray[mid];
        }
        return median;
    }

    public double average(double rangedSortedArray[]){
        double mean = 0;
        for(double i : rangedSortedArray){
            mean += i;
        }
        return (mean/rangedSortedArray.length);
    }

}
