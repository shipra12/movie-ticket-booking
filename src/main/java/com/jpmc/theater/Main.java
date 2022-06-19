package com.jpmc.theater;

/*Testing class*/


import com.jpmc.theater.utilities.LocalDateProvider;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String args[]){


        Theater theater = new Theater(LocalDateProvider.singleton(), "First Theatre");
        Customer customer1 = new Customer( "Shipra", 1);
        Movie movie = new Movie( "Dil Se", Duration.ofMinutes(120), 12);
        ReservationSystem reservationSystem = new ReservationSystem();
        reservationSystem.addTheatre(theater);
        reservationSystem.printMovieSchedule(theater);
        Reservation reservation = reservationSystem.reserve(customer1,movie, theater, theater.getAllShows().get(0), 5);
        System.out.println(reservationSystem.searchReservation(reservation.getReservationId()));

        Theater theater2 = new Theater(LocalDateProvider.singleton(), "Second Theatre");
        theater2.addShow(movie, LocalDateTime.of(theater2.getProvider().currentDate(), LocalTime.of(9, 0)));
        reservationSystem.addTheatre(theater2);
        reservationSystem.printMovieSchedule();

    }
}
