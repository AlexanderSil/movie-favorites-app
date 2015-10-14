package com.movie.favorite.impl;

import com.movie.favorite.FavoriteDTO;
import com.movie.favorite.api.FavoritesService;
import com.movie.favorite.api.FavoritesWebService;
import com.movie.favorite.util.Converters;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;
import javax.persistence.NoResultException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;

@WebService
public class FavoritesWebServiceImpl implements FavoritesWebService {

    private static final Logger LOG = LoggerFactory.getLogger(ConnectionThemoviesServiceImpl.class);

    @Autowired
    private FavoritesService favoritesService;

    @Autowired
    private Converters converters;

    @Override
    public List<FavoriteDTO> getAllFavorites() {
        try {
            return converters.getFavoritesDTOList(favoritesService.getAllFavorites());
        } catch (NotFoundException e) {
            LOG.debug(e.getMessage());
            throw new WebApplicationException(e, Response.Status.NOT_FOUND);
        }
    }

    @Override
    public FavoriteDTO saveFavorite(String title) {
        if (title == null || title.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        try {
            favoritesService.getFavoriteByTitle(title);
            throw new WebApplicationException(Response.Status.CONFLICT);
        } catch (NoResultException e) {
            return  converters.getFavoriteDTO(favoritesService.saveFavorite(title));
        }
    }
}
