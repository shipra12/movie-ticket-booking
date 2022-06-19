package com.jpmc.theater;


import com.jpmc.theater.utilities.FormatUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Showing {
    private Movie movie;
    private int sequenceOfTheDay;
    private LocalDateTime showStartTime;

    public Showing(Movie movie, int sequenceOfTheDay, LocalDateTime showStartTime) {
        this.movie = movie;
        this.sequenceOfTheDay = sequenceOfTheDay;
        this.showStartTime = showStartTime;
    }

    public LocalDateTime getStartTime() {
        return showStartTime;
    }

    public double getMovieFee() {
        return movie.getTicketPrice();
    }

    public int getSequenceOfTheDay() {
        return sequenceOfTheDay;
    }

    public Movie getMovie() { return movie; }

    public String toString(){
        return "Sequence : " + getSequenceOfTheDay() +
                "\nMovie Name : " + movie.getTitle() +
                "\nStart Time : "  + FormatUtils.formatTime(getStartTime()) +
                "\nPrice : " + getMovieFee() +
                "\nDuration : " + FormatUtils.humanReadableFormat(movie.getRunningTime()) +
                "\n" + FormatUtils.lineseparator();

    }

    public Map<String, String> detailMap(){
        Map<String, String> result = new HashMap<>();
        result.put("Sequence", String.valueOf(getSequenceOfTheDay()) );
        result.put("Start Time", FormatUtils.formatTime(getStartTime()));
        result.put("Price", String.valueOf(getMovieFee()));
        result.put("Duration", FormatUtils.humanReadableFormat(movie.getRunningTime()));

        return result;
    }

}