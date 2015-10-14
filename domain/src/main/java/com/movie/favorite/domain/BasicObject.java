package com.movie.favorite.domain;

import javax.persistence.MappedSuperclass;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Basic;
import javax.persistence.FetchType;
import java.io.Serializable;

@MappedSuperclass
public abstract class BasicObject implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    @Basic(fetch = FetchType.EAGER)
    protected Long id;

    public BasicObject() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
