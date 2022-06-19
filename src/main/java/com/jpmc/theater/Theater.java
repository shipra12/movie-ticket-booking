package com.jpmc.theater;

import com.jpmc.theater.utilities.LocalDateProvider;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class Theater {
    String theatreName;
    LocalDateProvider provider;
    Map<Movie, List<Showing>> allShows = new HashMap<>();
    private Integer currentSequence=1;

    public Theater(LocalDateProvider provider, String theatreName) {
        this.provider = provider;
        this.theatreName = theatreName;
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11);
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9);
        addShow(turningRed, LocalDateTime.of(provider.currentDate(), LocalTime.of(9, 0)));
        addShow(spiderMan, LocalDateTime.of(provider.currentDate(), LocalTime.of(11, 0)));
        addShow(theBatMan, LocalDateTime.of(provider.currentDate(), LocalTime.of(12, 50)));
        addShow(turningRed, LocalDateTime.of(provider.currentDate(), LocalTime.of(14, 30)));
        addShow(spiderMan, LocalDateTime.of(provider.currentDate(), LocalTime.of(16, 10)));
        addShow(theBatMan, LocalDateTime.of(provider.currentDate(), LocalTime.of(17, 50)));
        addShow(turningRed, LocalDateTime.of(provider.currentDate(), LocalTime.of(19, 30)));
        addShow(spiderMan, LocalDateTime.of(provider.currentDate(), LocalTime.of(21, 10)));
        addShow(theBatMan, LocalDateTime.of(provider.currentDate(), LocalTime.of(23, 0)));

    }
    public void addShow(Movie movie, LocalDateTime startTime){
        allShows.computeIfAbsent(movie, k -> new ArrayList<>()).add(new Showing(movie, currentSequence, startTime));
        currentSequence++;
    }

    public List<Showing> getAllShows() {
        List<Showing> result = new ArrayList<>();
        for( Movie movie: allShows.keySet()){
            List<Showing> showingList = allShows.get(movie);
            result.addAll(showingList);
        }
        Collections.sort(result, Comparator.comparingInt(Showing::getSequenceOfTheDay));
        return result;
    }
    public String getTheatreName() { return theatreName; }

    public LocalDateProvider getProvider() { return provider; }



}
