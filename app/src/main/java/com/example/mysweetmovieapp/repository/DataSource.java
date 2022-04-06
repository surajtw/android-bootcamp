package com.example.mysweetmovieapp.repository;

import com.example.mysweetmovieapp.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class DataSource {
    private static DataSource instance = null;
    private List<Movie> localMovies = new ArrayList<Movie>();

    private DataSource() {
        Movie shawShankRedemption =
                new Movie(1, "The Shawshank Redemption",
                        "http://i.imgur.com/DvpvklRpng", 9.2f);

        Movie lotr =
                new Movie(2, "The Lord of the Rings: The Return of the King ",
                        "https://i.imgur.com/DvpvklR.png", 8.9f);

        Movie inception  =
                new Movie(3, "Inception ",
                        "https://i.imgur.com/DvpvklR.png", 9.2f);

        Movie interstellar =
                new Movie(4, "Interstellar",
                        "https://i.imgur.com/DvpvklR.png", 9.2f);


        localMovies.add(shawShankRedemption);
        localMovies.add(lotr);
        localMovies.add(inception);
        localMovies.add(interstellar);
        localMovies.add(interstellar);
        localMovies.add(interstellar);
        localMovies.add(interstellar);
        localMovies.add(interstellar);
        localMovies.add(interstellar);
        localMovies.add(interstellar);
        localMovies.add(interstellar);
        localMovies.add(interstellar);
        localMovies.add(interstellar);
        localMovies.add(interstellar);

    }

    public List<Movie> getLocalMovies() {
        return localMovies;
    }

    public static DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }
}
