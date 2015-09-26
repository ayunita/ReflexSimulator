package com.example.yunita.reflexsimulator;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by yunita on 26/09/15.
 */
public class ReactionTime {

    private int time;

    public ReactionTime(int time){
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

//    public ArrayList<ReactionTime> getSubList(ArrayList<ReactionTime> reactionTimes, int last){
//        int start = reactionTimes.size()-1-last;
//        int end = reactionTimes.size()-1;
//        ArrayList<ReactionTime> list = new ArrayList<ReactionTime>(reactionTimes.subList(start,end));
//        return list;
//    }
//
//    public int getMinimumTime(ArrayList<ReactionTime> subList){
//
//        return 0;
//    }

}
