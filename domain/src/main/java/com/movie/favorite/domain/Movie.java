package com.movie.favorite.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Column;

@Entity
@Table(name = "movies")
public class Movie extends BasicObject {

    @ManyToOne
    @JoinColumn(name="favorite_group_id")
    private Favorite favorites;

    @Column(name = "id_movie")
    private String movieId;

    @Column(name = "title")
    private String title;

    @Column(name = "thumbnail_url")
    private String thumbnail;

    @Column(name = "link")
    private String link;

    public Movie() {
    }

    public Favorite getFavorites() {
        return favorites;
    }

    public void setFavorites(Favorite favorites) {
        this.favorites = favorites;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
