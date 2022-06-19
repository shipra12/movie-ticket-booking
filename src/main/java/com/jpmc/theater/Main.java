package com.jpmc.theater;

/*Testing class*/


import com.jpmc.theater.utilities.LocalDateProvider;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String args[]){


        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer customer1 = new Customer( "Shipra", 1);
        Movie movie = new Movie( "Dil Se", Duration.ofMinutes(120), 12);
        ReservationSystem reservationSystem = new ReservationSystem();
        List<Showing> allShows = theater.getAllShows();
        Reservation reservation = reservationSystem.reserve(customer1,movie, theater, allShows.get(0), 5);
        System.out.println(reservationSystem.searchReservation(reservation.getReservationId()));
    }
}
