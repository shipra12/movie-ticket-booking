package com.jpmc.theater;

import com.jpmc.theater.utilities.LocalDateProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;

public class ReservationSystemTest {

    private static ReservationSystem reservationSystem;
    private static Customer customer1;
    private static Customer customer2;
    private static Customer customer3;
    private static Customer customer4;

    private static Theater theater1;
    private static Theater theater2;
    private static Theater theater3;
    private static Theater theater4;



    @BeforeAll
    public static void setup (){
        customer1 = new Customer( "User1", 1);
        customer2 = new Customer( "User2", 2);
        customer3 = new Customer( "User3", 3);
        customer4 = new Customer( "User4", 4);

        theater1 = new Theater(LocalDateProvider.singleton(), "First Theatre");
        theater2 = new Theater(LocalDateProvider.singleton(), "Second Theatre");
        theater3 = new Theater(LocalDateProvider.singleton(), "Third Theatre");
        theater4 = new Theater(LocalDateProvider.singleton(), "Forth Theatre");

        reservationSystem = new ReservationSystem();
        reservationSystem.addTheatre(theater1);
        reservationSystem.addTheatre(theater2);
        reservationSystem.addTheatre(theater3);
        reservationSystem.addTheatre(theater4);


    }


    /***
     * Test for special movie discount offer
     * Base Price = 12.5
     * Audience = 5
     * Total Price = 62.5
     * Discount Percent = 20%
     * Discounted Total Price = ( 12.5 - ( 0.2 * 12.5 ) ) * 5 = 50
     */
    @Test
    void  specialMovieWith20PercentDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5);
        List<Showing> allShows= theater1.getAllShows(spiderMan);
        Reservation reservation = reservationSystem.reserve(customer1,spiderMan, theater1, allShows.get(1), 5);
        Double expectedTotalPrice = 50.0;
        Assertions.assertEquals(expectedTotalPrice, reservation.getTotalFee());
    }


    /***
     * Test for special movie discount offer
     * Base Price = 12.5
     * Audience = 5
     * Total Price = 62.5
     * Special Discount Percent = 20%
     * Start time Discount Percent = 25%
     * Discounted Total Price = ( 12.5 - ( 0.25 * 12.5 ) ) * 5 = 46.875
     */
    @Test
    void  specialMovieWith25StartTimeDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5);
        List<Showing> allShows= theater1.getAllShows(spiderMan);
        Reservation reservation = reservationSystem.reserve(customer1,spiderMan, theater1, allShows.get(0), 5);
        Double expectedTotalPrice = 46.875;
        Assertions.assertEquals(expectedTotalPrice, reservation.getTotalFee());
    }

    /***
     * Test for special movie discount offer
     * Base Price = 9
     * Audience = 5
     * Total Price = 45
     * Discounted Total Price = ( 12.5 ) * 5 = 46.875
     */
    @Test
    void  regularMovieWithNoDiscount() {
        Movie batman = new Movie("The Batman", Duration.ofMinutes(95), 9);
        List<Showing> allShows= theater1.getAllShows(batman);
        Reservation reservation = reservationSystem.reserve(customer1,batman, theater1, allShows.get(0), 5);
        Double expectedTotalPrice = 45.0;
        Assertions.assertEquals(expectedTotalPrice, reservation.getTotalFee());
    }

    /***
     * Test for special movie discount offer
     * Base Price = 11
     * Audience = 5
     * Discount = 2
     * Discounted Total Price = ( 11 - 2 ) * 5 = 45
     */
    @Test
    void  regularMovieWithSequenceDiscount() {
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11);
        List<Showing> allShows= theater1.getAllShows(turningRed);
        Reservation reservation = reservationSystem.reserve(customer1,turningRed, theater1, allShows.get(0), 5);
        Double expectedTotalPrice = 40.0;
        Assertions.assertEquals(expectedTotalPrice, reservation.getTotalFee());
    }

    @Test
    void  searchReservationForValidCustomer() {
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11);
        List<Showing> allShows= theater1.getAllShows(turningRed);
        Reservation reservation = reservationSystem.reserve(customer1,turningRed, theater1, allShows.get(0), 5);
        Assertions.assertEquals(reservationSystem.searchReservation(customer1), reservation);
    }

    @Test
    void  searchReservationForInvalidCustomer() {
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11);
        List<Showing> allShows= theater1.getAllShows(turningRed);
        Reservation reservation = reservationSystem.reserve(customer1,turningRed, theater1, allShows.get(0), 5);
        Assertions.assertNotEquals(reservationSystem.searchReservation(customer3), reservation);
        Assertions.assertNull(reservationSystem.searchReservation(customer3));
    }

    @Test
    void  searchReservationForValidReservationId() {
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11);
        List<Showing> allShows= theater1.getAllShows(turningRed);
        Reservation reservation = reservationSystem.reserve(customer1,turningRed, theater1, allShows.get(0), 5);
        Assertions.assertEquals(reservationSystem.searchReservation(reservation.getReservationId()), reservation);
    }

    @Test
    void  searchReservationForInValidReservationId() {
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11);
        List<Showing> allShows= theater1.getAllShows(turningRed);
        Reservation reservation = reservationSystem.reserve(customer1,turningRed, theater1, allShows.get(0), 5);
        Assertions.assertNotEquals( reservationSystem.searchReservation( -99), reservation);
    }

    @Test
    void  searchAllReservationsForaCustomer() {
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11);
        List<Showing> allShows= theater1.getAllShows(turningRed);
        Reservation reservation1 = reservationSystem.reserve(customer1,turningRed, theater1, allShows.get(0), 5);
        Reservation reservation2 = reservationSystem.reserve(customer1,turningRed, theater1, allShows.get(0), 5);
        Reservation reservation3 = reservationSystem.reserve(customer1,turningRed, theater1, allShows.get(0), 5);
        List<Reservation> allReservation = reservationSystem.searchReservation(customer1);
        Assertions.assertEquals( allReservation.size(), 3);

        Assertions.assertEquals(reservationSystem.searchReservation(customer2).size(), 0);
    }

    @Test
    void  printJson() {
        reservationSystem.printMovieScheduleinJson(theater1);
    }

}
