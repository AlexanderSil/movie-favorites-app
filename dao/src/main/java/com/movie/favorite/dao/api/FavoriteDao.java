package com.movie.favorite.dao.api;

import com.movie.favorite.domain.Favorite;

public interface FavoriteDao extends BasicDao<Favorite> {

    Favorite findByTitle(String title);
}
