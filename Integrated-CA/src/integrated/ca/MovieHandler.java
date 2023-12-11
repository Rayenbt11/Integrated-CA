/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integrated.ca;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rayen bentemessek 2021378
 */
public class MovieHandler {

    private Map<String, List<Movie>> moviesByTitle;

    //loads movies from the csv file
    public MovieHandler(String csvFilePath) {
        CSVReader csvReader = new CSVReader(csvFilePath);
        this.moviesByTitle = csvReader.readMovies();
    }

    //searching for movies by their titles, mapping each title to a list of movies because titles are not unique so the user can choose
    public List<Movie> searchMoviesByTitle(String title) {
        return moviesByTitle.getOrDefault(title.toLowerCase(), new ArrayList<>());
    }

}
