package com.binhlc.movieticketadmin.domain.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "movie_category", schema = "movie_ticket")
public class MovieCategoryEntity {
    private int id;
    private Integer idCategory, idMovie;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "id_category")
    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }

    @Column(name = "id_movie")
    public Integer getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(Integer idMovie) {
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idCategory, idMovie);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieCategoryEntity that = (MovieCategoryEntity) o;
        return id == that.id &&
                Objects.equals(idCategory, that.idCategory) &&
                Objects.equals(idMovie, that.idMovie);
    }
}
