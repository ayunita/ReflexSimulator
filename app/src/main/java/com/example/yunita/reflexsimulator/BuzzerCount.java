package com.example.yunita.reflexsimulator;

/**
 * Created by yunita on 26/09/15.
 */
public class BuzzerCount {

    private int counters[] = new int[9];
    private int mode;
    private int player;


    public BuzzerCount(){

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

    public void updateCounter(){
        int startIndex = 0;
        switch(mode){
            case 2: startIndex = 0;
                break;
            case 3: startIndex = 2;
                break;
            case 4: startIndex = 5;
                break;
        }
        int playerIndex = startIndex + player - 1;
        int newCounter = counters[playerIndex] + 1; // index = player - 1
        counters[playerIndex] = newCounter;
    }

}
