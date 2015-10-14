package com.movie.favorite.impl;

import com.movie.favorite.api.ConnectionThemoviesService;
import com.movie.favorite.dao.api.FavoriteDao;
import com.movie.favorite.domain.Favorite;
import com.movie.favorite.domain.Movie;
import com.movie.favorite.api.MovieService;

import com.movie.favorite.dao.api.MovieDao;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private ConnectionThemoviesService connectionThemoviesService;

    @Autowired
    private MovieDao movieDao;

    @Autowired
    private FavoriteDao favoriteDao;

    @Override
    public List<Movie> searchMovies(final String query, final Integer maxResult)  throws NotFoundException {
        return connectionThemoviesService.searchMovies(query, maxResult);
    }

    @Override
    public void saveMovie(final String movieId, final Favorite favorite)  throws NotFoundException {
        Movie movie = connectionThemoviesService.getMovieById(movieId);

        if (movie != null) {
            movie.setFavorites(favorite);
            movie.setLink("https://www.themoviedb.org/movie/" + movie.getMovieId());
            movieDao.saveOrUpdate(movie);
        } else {
            throw new NotFoundException("Movie not found");
        }
    }
}