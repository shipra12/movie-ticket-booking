package com.jpmc.theater;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class ReservationSystem {
    Discount discount;
    private List<Reservation> allReservations;
    private List<Theater> allTheatres;
    private static int reservationId = 0;

    public ReservationSystem() {
        Discount discount = new Discount();
        allReservations = new ArrayList<>();
        allTheatres = new ArrayList<>();
    }

    public Reservation reserve( Customer customer, Movie movie, Theater theater, Showing showing, int audienceCount){
        Reservation reservation = new Reservation(reservationId++, customer, theater, showing, audienceCount);
        reservation.setTotalFee(calculateReservationFee(movie, showing, audienceCount));
        allReservations.add(reservation);
        return reservation;
    }

    public Reservation searchReservation(int reservationId){
        Reservation result = null;
        for ( Reservation reservation : allReservations){
            if (reservation.getReservationId() == reservationId)
                result = reservation;
        }
        return result;
    }

    private Double calculateReservationFee( Movie movie, Showing showing, int audienceCount){
        Double basePrice = movie.getTicketPrice();
        Double discountedAmount = applicableDiscount( basePrice, movie, showing);
        return (basePrice - discountedAmount) * audienceCount;
    }

    private Double applicableDiscount( Double basePrice, Movie movie, Showing showing){
        List<Double> applicable_discount = new ArrayList<>();
        applicable_discount.add(discount.getSpecialMovieList().contains(movie.getTitle()) ? 0.1 * basePrice : 0);
        applicable_discount.add(discount.getDiscountSequence().containsKey(showing.getSequenceOfTheDay()) ? discount.getDiscountSequence().get(showing.getSequenceOfTheDay()) : 0);
        applicable_discount.add(discount.getDiscountDayOfMonth().equals(showing.getStartTime().getDayOfMonth())? 1.0 : 0);
        int startTime = showing.getStartTime().getHour();
        applicable_discount.add(startTime > discount.getStartTime().getValue0() && startTime < discount.getStartTime().getValue0() ? 0.25 * basePrice: 0);

        return Collections.max(applicable_discount);
    }
    public void addTheatre(Theater theater){
        allTheatres.add(theater);
    }

    public void printMovieSchedule(Theater theater){
        System.out.println("Movie Schedule for " + theater.getTheatreName());
        List<Showing> allShows = theater.getAllShows();
        for ( Showing show: allShows ) {
            System.out.println(show.toString());
        }
    }

    public void printMovieSchedule(){
        System.out.println("Schedule for all theaters");
        for ( Theater theatre: allTheatres ) {
            printMovieSchedule(theatre);
        }
    }

}
