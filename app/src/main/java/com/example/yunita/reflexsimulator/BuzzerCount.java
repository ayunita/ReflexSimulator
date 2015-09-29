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

    public int getStartIndexMode(int mode){
        int startIndex = 0;
        switch(mode){
            case 2: startIndex = 0;
                break;
            case 3: startIndex = 2;
                break;
            case 4: startIndex = 5;
                break;
        }
        return startIndex;
    }

    public void updateCounter(){
        int startIndex = getStartIndexMode(mode);
        int playerIndex = startIndex + player - 1;
        int newCounter = counters[playerIndex] + 1; // index = player - 1
        counters[playerIndex] = newCounter;
    }

    public String getModeResult(int mode, String[] savedData){
        int start = getStartIndexMode(mode);
        int end = start + mode;
        int player = 1;
        String result = "MODE: " + mode + "\n";
        for(int i = start; i < end; i++){
            if(!savedData[i].equals("0")) {
                result += "Player " + player + ": " + savedData[i] + " buzzes\n";
            } else {
                result += "Player " + player + ": " + savedData[i] + " buzz\n";
            }
            player++;
        }
        return result;
    }

}
