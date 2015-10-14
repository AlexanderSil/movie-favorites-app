package com.movie.favorite.api;

import com.movie.favorite.MovieDTO;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/movie")
public interface MoviesWebService {

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    List<MovieDTO> searchMovies(@FormParam("query") String query,
                                @FormParam("maxResultStr") Integer maxResult);

    @GET
    @Path("/getMovies")
    @Produces(MediaType.APPLICATION_JSON)
    List<MovieDTO> getFavoriteMovies(@FormParam("favoriteId") Long idFavorite);

    @POST
    @Path("/saveMovie")
    @Produces(MediaType.APPLICATION_JSON)
    void saveMovie(@FormParam("movieId") String movieId,
                   @FormParam("favoriteId") Long favoriteId);
}
