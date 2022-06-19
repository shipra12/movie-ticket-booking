package com.jpmc.theater;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class ReservationSystem {
    private List<Reservation> allReservations;
    private static int reservationId = 0;

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
        applicable_discount.add(Discount.getSpecialMovieList().contains(movie.getTitle()) ? 0.1 * basePrice : 0);
        applicable_discount.add(showing.getSequenceOfTheDay() == 1 ? 3.0 : showing.getSequenceOfTheDay() == 2 ? 2.0 : 0);
        applicable_discount.add(Discount.getDiscountDayOfMonth().equals(showing.getStartTime().getDayOfMonth())? 1.0 : 0);
        //Double timeOfDayDiscount = showing.getStartTime() == 11

        return Collections.max(applicable_discount);
    }
    public void setAllReservations(List<Reservation> allReservations) { this.allReservations = allReservations; }


}
