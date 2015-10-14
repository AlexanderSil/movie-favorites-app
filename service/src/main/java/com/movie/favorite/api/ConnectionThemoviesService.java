package com.movie.favorite.api;

import com.movie.favorite.domain.Movie;
import javassist.NotFoundException;

import java.util.List;

public interface ConnectionThemoviesService {

    List<Movie> searchMovies(String query, Integer maxResult) throws NotFoundException;

    Movie getMovieById(String id) throws NotFoundException;
}
