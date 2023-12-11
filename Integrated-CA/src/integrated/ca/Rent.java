/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integrated.ca;

import java.time.LocalDateTime;

/**
 *
 * @author Rayen Bentemessek 2021378
 *
 */
public class Rent {

    private User user; //// User class not implemented yet

    private Movie movie;
    private LocalDateTime rentalStartTime;

//constructor for renting the movies
    public Rent(User user, Movie movie) {

        this.user = user;
        this.movie = movie;
        this.rentalStartTime = LocalDateTime.now();

    }

    public User getUser() {
        return user;
    }


    /*
return the selected movie
     */
    public Movie getMovie() {
        return movie;
    }

    /*
    return the duration of the movie rental
     */
    public LocalDateTime getRentalStartTime() {
        return rentalStartTime;
    }
    
    public boolean rentActive(){
    return rentalStartTime.plusMinutes(1).isAfter(LocalDateTime.now());
    }

}
