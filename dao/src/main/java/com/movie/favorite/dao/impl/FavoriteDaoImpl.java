package com.movie.favorite.dao.impl;

import com.movie.favorite.dao.api.FavoriteDao;
import com.movie.favorite.domain.Favorite;

import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class FavoriteDaoImpl extends BasicJpaDao<Favorite> implements FavoriteDao {

    public FavoriteDaoImpl() {
        super(Favorite.class);
    }

    @Override
    public Favorite findByTitle(String title) {
        Query query = getEntityManager().createQuery("FROM Favorite AS f WHERE f.title=:title")
                .setParameter("title", title);
        return (Favorite) query.getSingleResult();
    }
}