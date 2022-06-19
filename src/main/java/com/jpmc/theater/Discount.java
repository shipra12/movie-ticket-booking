package com.jpmc.theater;

import java.time.LocalDateTime;
import java.time.MonthDay;
import java.util.*;

import org.javatuples.Pair;

public class Discount {


    private static List<String> SPECIAL_MOVIE_LIST;
    private static Map<Integer, Double> DISCOUNT_SEQUENCE;
    private static Integer DISCOUNT_DAY_OF_MONTH;
    private static Pair<Integer, Integer> START_TIME;


    public Discount(){
        setDiscountSequence();
        setDiscountDayOfMonth();
        setStartTime();
        setSpecialMovieList();

    }

    public static void setSpecialMovieList() {

        SPECIAL_MOVIE_LIST = new ArrayList<>(){
            {
                add("Spider-Man: No Way Home");
            }
        };
    }

    public static void setDiscountSequence() {
        DISCOUNT_SEQUENCE = new HashMap<>();
        DISCOUNT_SEQUENCE.put(1, 3.0);
        DISCOUNT_SEQUENCE.put(2, 2.0);
    }

    public static void setDiscountDayOfMonth() {
        DISCOUNT_DAY_OF_MONTH = 7;
    }

    public static void setStartTime() {
        START_TIME = new Pair<>( 11, 16);
    }

    public static List<String> getSpecialMovieList() {
        return SPECIAL_MOVIE_LIST;
    }

    public static Map<Integer, Double> getDiscountSequence() {
        return DISCOUNT_SEQUENCE;
    }
    public static Integer getDiscountDayOfMonth() {
        return DISCOUNT_DAY_OF_MONTH;
    }

    public static Pair<Integer, Integer> getStartTime(){
        return START_TIME;
    }

}
