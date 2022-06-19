package com.jpmc.theater;


import com.jpmc.theater.utilities.FormatUtils;

import java.time.LocalDateTime;

public class Showing {
    private Movie movie;
    private int sequenceOfTheDay;
    private LocalDateTime showStartTime;

    public Showing(Movie movie, int sequenceOfTheDay, LocalDateTime showStartTime) {
        this.movie = movie;
        this.sequenceOfTheDay = sequenceOfTheDay;
        this.showStartTime = showStartTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getStartTime() {
        return showStartTime;
    }

    public boolean isSequence(int sequence) {
        return this.sequenceOfTheDay == sequence;
    }

    public double getMovieFee() {
        return movie.getTicketPrice();
    }

    public int getSequenceOfTheDay() {
        return sequenceOfTheDay;
    }

    public String toString(){
        return "Sequence : " + getSequenceOfTheDay() +
                "\nMovie Name : " + movie.getTitle() +
                "\nStart Time : "  + FormatUtils.formatTime(getStartTime()) +
                "\nPrice : " + getMovieFee() +
                "\nDuration : " + FormatUtils.humanReadableFormat(movie.getRunningTime()) +
                "\n" + FormatUtils.lineseparator();

    }

}