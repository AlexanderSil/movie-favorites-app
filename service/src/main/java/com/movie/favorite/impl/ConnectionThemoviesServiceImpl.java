package com.movie.favorite.impl;

import com.movie.favorite.api.ConnectionThemoviesService;
import com.movie.favorite.domain.Movie;
import com.movie.favorite.util.Constants;
import javassist.NotFoundException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConnectionThemoviesServiceImpl implements ConnectionThemoviesService {

    /**
     *  The Constant LOG.
     */
    private static final Logger LOG = LoggerFactory.getLogger(ConnectionThemoviesServiceImpl.class);


    @Override
    public List<Movie> searchMovies(String query, Integer maxResult) throws NotFoundException{
        List<Movie> movies = new ArrayList<>();

        String uri = Constants.SEARCH_MOVIE_URL + query;
        RestTemplate restTemplate = new RestTemplate();

        String result = restTemplate.getForObject(uri, String.class);

        if (result != null && !result.isEmpty()) {
            movies = getMoviesFromResponse(result, maxResult);
        } else {
            throw new NotFoundException("Movies not found.");
        }
        if (movies.isEmpty()) {
            throw new JSONException("Can not parse result to JSON");
        }

        return movies;
    }

    private List<Movie> getMoviesFromResponse(String str, Integer maxResult) throws NotFoundException{

            JSONObject obj = new JSONObject(str);

            int totalResult = obj.getInt("total_results");
            if (totalResult == 0) {
                throw new NotFoundException("Movies not found.");
            }
            JSONArray array = obj.getJSONArray("results");
            int returnCount;
            if (totalResult >= maxResult) {
                returnCount = maxResult;
            } else {
                returnCount = totalResult;
            }
            List<Movie> movies = new ArrayList<>();
            for (int i = 0; i < returnCount; i++) {
                try {
                    JSONObject jsonObject = array.getJSONObject(i);
                    Movie movie = new Movie();
                    movie.setMovieId(jsonObject.get("id").toString());
                    movie.setTitle(jsonObject.get("title").toString());

                    String backdropPath = jsonObject.get("poster_path").toString();
                    String thumbnailPath;
                    if (backdropPath.equals("") || backdropPath.equals("null")) {
                        thumbnailPath = "/images/noThumbnail.jpeg";
                    } else {
                        thumbnailPath = Constants.THEMOVIES_THUMBNAIL_URL + backdropPath;
                    }
                    movie.setThumbnail(thumbnailPath);
                    movies.add(movie);
                } catch (JSONException e) {
                    LOG.error("Can not parse result to JSON: " + e.getMessage());
                }
            }
            return movies;
    }
    @Override
    public Movie getMovieById(String id) throws NotFoundException {
        Movie movie = null;
        if (id != null) {
            String uri = Constants.GET_MOVIE_URL + id + Constants.THEMOVIES_API_KEY;

            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(uri, String.class);

            if (result != null && !result.isEmpty()) {
                movie = getMovieFromResponse(result);
            } else {
                throw new NotFoundException("Movie by id not found.");
            }
        }
        return movie;
    }

    private Movie getMovieFromResponse(String result) throws NotFoundException{
        try {
            Movie movie = new Movie();

            JSONObject obj = new JSONObject(result);
            movie.setMovieId(obj.get("id").toString());
            movie.setTitle(obj.get("title").toString());

            String backdropPath = obj.get("poster_path").toString();
            String thumbnailPath;
            if (backdropPath.equals("") || backdropPath.equals("null")) {
                thumbnailPath = "/images/noThumbnail.jpeg";
            } else {
                thumbnailPath = Constants.THEMOVIES_THUMBNAIL_URL + backdropPath;
            }
            movie.setThumbnail(thumbnailPath);
            return movie;
        } catch (JSONException e) {
            LOG.error("Can not parse result to JSON");
            throw e;
        }
    }

}
