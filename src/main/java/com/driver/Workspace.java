package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId , Integer.MAX_VALUE);
        this.calendar = new ArrayList<>();
    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        calendar.add(meeting);
    }

    public int findMaxMeetings(){

        ArrayList<Pair<LocalTime, Integer>> endTimes = new ArrayList<>();

        for(int i=0; i<calendar.size();i++){
            endTimes.add(Pair.of(calendar.get(i).getEndTime(), i));
        }

        Collections.sort(endTimes);

        LocalTime timeLimit = endTimes.get(0).getLeft();
        int count = 1;

        for(int i=1; i<endTimes.size(); i++){
            if(calendar.get(endTimes.get(i).getRight()).getStartTime().compareTo(timeLimit) > 0){
                count += 1;
                timeLimit = endTimes.get(i).getLeft();
            }
        }
        return count;
    }
}