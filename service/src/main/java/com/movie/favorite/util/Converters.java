package com.movie.favorite.util;

import com.movie.favorite.FavoriteDTO;
import com.movie.favorite.domain.Favorite;
import com.movie.favorite.domain.Movie;
import com.movie.favorite.MovieDTO;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class Converters {

    public List<MovieDTO> getMoviesDTOList(List<Movie> movies) {
        List<MovieDTO> movieDTOs = new LinkedList<>();
        if (movies == null || movies.isEmpty()) {
            return movieDTOs;
        }
        for (Movie movie : movies) {
            movieDTOs.add(getMovieDTO(movie));
        }
        return movieDTOs;
    }

    public MovieDTO getMovieDTO(Movie movie) {
        MovieDTO movieDTO = new MovieDTO();
        if (movie != null) {
            movieDTO.setId(movie.getId());
            movieDTO.setMovieId(movie.getMovieId());
            if (movie.getFavorites() != null) {
                movieDTO.setFavoritesId(movie.getFavorites().getId());
            }
            movieDTO.setTitle(movie.getTitle());
            movieDTO.setThumbnail(movie.getThumbnail());
            movieDTO.setLink(movie.getLink());
        }
        return movieDTO;
    }

    public List<FavoriteDTO> getFavoritesDTOList(List<Favorite> favorites) {
        List<FavoriteDTO> favoriteDTOs = new LinkedList<>();
        if (favorites == null || favorites.isEmpty()) {
            return favoriteDTOs;
        }
        for (Favorite favorite : favorites) {
            favoriteDTOs.add(getFavoriteDTO(favorite));
        }
        return favoriteDTOs;
    }

    public FavoriteDTO getFavoriteDTO(Favorite favorite) {
        FavoriteDTO favoriteDTO = new FavoriteDTO();
        if (favorite != null) {
            favoriteDTO.setId(favorite.getId());
            favoriteDTO.setMovies(getMoviesDTOList(favorite.getMovies()));
            favoriteDTO.setTitle(favorite.getTitle());
            favoriteDTO.setLink(favorite.getLink());
        }
        return favoriteDTO;
    }
}
