/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integrated.ca;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rayen Bentemessek 2021378
 */
/*
Class used to read the movies data from the csv file and then store it in a map
 */
public class CSVReader {

    private String csvFile;
/// csv file will be inserted during the instantiation to add more flexibility in case we want to have another movies' data

    public CSVReader(String csvFile) {
        this.csvFile = csvFile;
    }

    /*
    Because some movies have the same title, each title in the map is associated with a list of movies instead of just a movie
    so in case user chooses a title that exists more than once he'll get to see the list of movies with that title and then he's able to choose whatever he like
     */
    public Map<String, List<Movie>> readMovies() {
        Map<String, List<Movie>> movies = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                // the csv file is comma-separated
                String[] elements = line.split(",");
                String title = elements[7].trim();
                double price = Double.parseDouble(elements[10].trim());
                Movie movie = new Movie(title, price);
                /*
                ComputeIfAbsent checks if there is already a list of movies with that title than, in case there is no movies with the same title it will create a new one
                 */
                movies.computeIfAbsent(title, k -> new ArrayList<>()).add(movie);
            }

        } catch (IOException ex) {
            Logger.getLogger(CSVReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return movies;

    }
}
