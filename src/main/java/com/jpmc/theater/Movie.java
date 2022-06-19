package com.jpmc.theater;

import java.time.Duration;
import java.util.Objects;

public class Movie {
    private static int MOVIE_CODE_SPECIAL = 1;

    private String title;
    private String description;
    private Duration runningTime;
    private double ticketPrice;


    public Movie(String title, Duration runningTime, double ticketPrice) {
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
    }

    public String getTitle() {
        return title;
    }

    public Duration getRunningTime() {
        return runningTime;
    }
    public double getTicketPrice() {
        return ticketPrice;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, runningTime, ticketPrice);
    }

    public String toString(){
        return "Movie Name: " + getTitle() + "\n"+
                "Runtime: " + getRunningTime() + "\n" +
                "Ticket Price: " + getTicketPrice();
    }
}