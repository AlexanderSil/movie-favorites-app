package com.movie.favorite.impl;

import com.movie.favorite.MovieDTO;
import com.movie.favorite.api.FavoritesService;
import com.movie.favorite.api.MovieService;
import com.movie.favorite.api.MoviesWebService;
import com.movie.favorite.domain.Favorite;
import com.movie.favorite.domain.Movie;
import com.movie.favorite.util.Converters;
import javassist.NotFoundException;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.jws.WebService;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;

@WebService
public class MovieWebServiceImpl implements MoviesWebService {

    /**
     * The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(ConnectionThemoviesServiceImpl.class);

    @Autowired
    private MovieService movieService;

    @Autowired
    private FavoritesService favoritesService;

    @Autowired
    private Converters converters;

    @Override
    public List<MovieDTO> searchMovies(final String query, final Integer maxResult) {
        if (query == null || query.isEmpty() || maxResult < 1) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        try {
            List<Movie> movies = movieService.searchMovies(query, maxResult);
            return converters.getMoviesDTOList(movies);
        } catch (NotFoundException ex) {
            LOG.debug(ex.getMessage());
            throw new WebApplicationException(ex, Response.Status.NOT_FOUND);
        } catch (JSONException jsonEx) {
            LOG.debug(jsonEx.getMessage());
            throw new WebApplicationException(jsonEx, Response.Status.INTERNAL_SERVER_ERROR);
        } catch (HttpClientErrorException httpEx) {
            LOG.debug(httpEx.getMessage());
            throw new WebApplicationException(httpEx, Response.Status.UNAUTHORIZED);
        }
    }

    @Override
    public List<MovieDTO> getFavoriteMovies(final Long idFavorite) {
        if (idFavorite == null || idFavorite < 1) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        try {
            Favorite favoriteById = favoritesService.getFavoriteById(idFavorite);
            return converters.getMoviesDTOList(favoriteById.getMovies());
        } catch (NotFoundException e) {
            LOG.debug(e.getMessage());
            throw new WebApplicationException(e, Response.Status.NOT_FOUND);
        }

    }

    @Override
    public void saveMovie(final String movieId, final Long favoriteId) {
        if (movieId == null || movieId.isEmpty() || favoriteId == null || favoriteId < 1) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        try {
            Favorite favorite = favoritesService.getFavoriteById(favoriteId);
            for (Movie movie : favorite.getMovies()) {
                if (movie.getMovieId().equals(movieId)) {
                    throw new WebApplicationException(Response.Status.CONFLICT);
                }
            }
            movieService.saveMovie(movieId, favorite);
        } catch (NotFoundException ex) {
            LOG.debug(ex.getMessage());
            throw new WebApplicationException(ex, Response.Status.NOT_FOUND);
        } catch (JSONException jsonEx) {
            LOG.debug(jsonEx.getMessage());
            throw new WebApplicationException(jsonEx, Response.Status.INTERNAL_SERVER_ERROR);
        } catch (HttpClientErrorException httpEx) {
            LOG.debug(httpEx.getMessage());
            throw new WebApplicationException(httpEx, Response.Status.UNAUTHORIZED);
        }
    }
}