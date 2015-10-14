package com.movie.favorite.api;

import com.movie.favorite.domain.Favorite;
import javassist.NotFoundException;

import java.util.List;

public interface FavoritesService {

    List<Favorite> getAllFavorites() throws NotFoundException;

    Favorite getFavoriteById(Long idFavorite) throws NotFoundException;

    Favorite getFavoriteByTitle(String title);

    Favorite saveFavorite(String title);
}
