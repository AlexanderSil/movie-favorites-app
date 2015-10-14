package com.movie.favorite.impl;

import com.movie.favorite.api.FavoritesService;
import com.movie.favorite.dao.api.FavoriteDao;
import com.movie.favorite.domain.Favorite;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoritesServiceImpl implements FavoritesService {

    @Autowired
    private FavoriteDao favoriteDao;

    @Override
    public List<Favorite> getAllFavorites() throws NotFoundException{
        List<Favorite> favoriteList = (List<Favorite>) favoriteDao.findAll();
        if (favoriteList == null || favoriteList.isEmpty()) {
            throw new NotFoundException("Favorites not found.");
        }
        return favoriteList;
    }

    @Override
    public Favorite getFavoriteById(Long idFavorite) throws NotFoundException {
        Favorite favorite = favoriteDao.findById(idFavorite);
        if (favorite == null) {
            throw new NotFoundException("Favorite by id not found.");
        }
        return favorite;
    }

    @Override
    public Favorite getFavoriteByTitle(String title) {
        return favoriteDao.findByTitle(title);
    }

    @Override
    public Favorite saveFavorite(String title) {
        Favorite favorite = new Favorite();
            favorite.setTitle(title);
            favorite = favoriteDao.saveOrUpdate(favorite);
        return favorite;
    }
}
