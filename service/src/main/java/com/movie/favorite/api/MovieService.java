package com.movie.favorite.api;

import com.movie.favorite.domain.Favorite;
import com.movie.favorite.domain.Movie;
import javassist.NotFoundException;

import java.util.List;

/**
 * Created by alex on 06.10.15.
 */
public interface MovieService {

    List<Movie> searchMovies(String query, Integer maxResult) throws NotFoundException;

    void saveMovie(String movieId, Favorite favoriteId) throws NotFoundException;
}
