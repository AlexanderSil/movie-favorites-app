package com.movie.favorite.api;

import com.movie.favorite.FavoriteDTO;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/favorites")
public interface FavoritesWebService {

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    List<FavoriteDTO> getAllFavorites();

    @POST
    @Path("/saveFavorite")
    @Produces(MediaType.APPLICATION_JSON)
    FavoriteDTO saveFavorite(@FormParam("title") String title);
}
