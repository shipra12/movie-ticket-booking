package com.jpmc.theater;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.javatuples.Pair;

public class Discount {


    public Discount(){
        DISCOUNT_SEQUENCE = new ArrayList<>();
        setDiscountSequence();

    }

    public static void setSpecialMovieList(List<String> specialMovieList) {
        SPECIAL_MOVIE_LIST = specialMovieList;
    }

    public static void setDiscountSequence() {
        DISCOUNT_SEQUENCE = discountSequence;
    }

    public static void setDiscountDayOfMonth(LocalDateTime discountDayOfMonth) {
        DISCOUNT_DAY_OF_MONTH = discountDayOfMonth;
    }

    public static void setStartTime(Pair<Integer, Integer> startTime) {
        START_TIME = startTime;
    }

    private static List<String> SPECIAL_MOVIE_LIST;
    private static List<Integer> DISCOUNT_SEQUENCE;
    private static LocalDateTime DISCOUNT_DAY_OF_MONTH;

    private static Pair<Integer, Integer> START_TIME;
    public static List<String> getSpecialMovieList() {
        return SPECIAL_MOVIE_LIST;
    }

    public static List<Integer> getDiscountSequence() {
        return DISCOUNT_SEQUENCE;
    }
    public static LocalDateTime getDiscountDayOfMonth() {
        return DISCOUNT_DAY_OF_MONTH;
    }

    public static Pair<Integer, Integer> getStartTime(){
        return START_TIME;
    }

}
