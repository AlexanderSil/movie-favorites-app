package com.movie.favorite.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import java.util.List;

@Entity
@Table(name = "favorites")
public class Favorite extends BasicObject {

    @OneToMany(mappedBy="favorites", targetEntity = Movie.class, fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Movie> movies;

    @Column(name = "title")
    private String title;

    @Column(name = "link")
    private String link;

    public Favorite() {
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
