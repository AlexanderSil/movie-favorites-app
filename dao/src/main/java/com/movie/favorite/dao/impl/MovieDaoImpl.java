package com.movie.favorite.dao.impl;

import com.movie.favorite.domain.Movie;
import com.movie.favorite.dao.api.MovieDao;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDaoImpl extends BasicJpaDao<Movie> implements MovieDao {

    public MovieDaoImpl () {
        super (Movie.class);
    }
}
