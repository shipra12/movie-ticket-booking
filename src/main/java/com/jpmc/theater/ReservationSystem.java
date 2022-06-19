package com.jpmc.theater;

import java.lang.reflect.Type;
import java.util.*;

import com.google.gson.*;
import com.google.gson.reflect.*;

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
    public List<Reservation> searchReservation(Customer customer){
        List<Reservation> result = new ArrayList<>();
        for ( Reservation reservation : allReservations){
            if (reservation.getCustomer().equals(customer))
                result.add(reservation);
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
        applicable_discount.add(discount.getSpecialMovieList().contains(movie.getTitle()) ? 0.2 * basePrice : 0);
        applicable_discount.add(discount.getDiscountSequence().containsKey(showing.getSequenceOfTheDay()) ? discount.getDiscountSequence().get(showing.getSequenceOfTheDay()) : 0);
        applicable_discount.add(discount.getDiscountDayOfMonth().equals(showing.getStartTime().getDayOfMonth())? 1.0 : 0);
        int startTime = showing.getStartTime().getHour();
        applicable_discount.add(startTime >= discount.getStartTime().getValue0() && startTime <= discount.getStartTime().getValue0() ? 0.25 * basePrice: 0);

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
    public void printMovieScheduleinJson(Theater theater){
        Map<String, List<Map<String, String>>> jsonMap = new HashMap<>();
        for( Showing show: theater.getAllShows()){
            if (jsonMap.containsKey(show.getMovie().getTitle())){
                List<Map<String, String>> currentShows = jsonMap.get(show.getMovie().getTitle());
                currentShows.add(show.detailMap());
            }
            else{
                List<Map<String, String>> newList = new ArrayList<>();
                newList.add(show.detailMap());
                jsonMap.put(String.valueOf(show.getMovie().getTitle()), newList);
            }

        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(jsonMap);
        System.out.println(json);
    }

}
