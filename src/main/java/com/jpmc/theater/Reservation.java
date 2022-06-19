package com.jpmc.theater;

public class Reservation {

    private int reservationId;
    private Customer customer;
    private int audienceCount;
    private Theater theater;
    private Showing showing;
    private Double totalFee;

    public Reservation(int reservationId, Customer customer, Theater theater, Showing showing, int audienceCount) {
        this.reservationId = reservationId;
        this.customer = customer;
        this.theater = theater;
        this.audienceCount = audienceCount;
        this.showing = showing;
    }

    public int getReservationId() { return reservationId; }

    public void setTotalFee(Double totalFee) { this.totalFee = totalFee; }

}