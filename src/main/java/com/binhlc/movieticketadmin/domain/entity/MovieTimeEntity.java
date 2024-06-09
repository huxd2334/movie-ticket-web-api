package com.binhlc.movieticketadmin.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "movie_time", schema = "movie_ticket")
public class MovieTimeEntity {
    private int id;
    private Integer timeStart, dateStart, price, idMovie, idRoom, createBy, updateBy;
    private Timestamp timeCreate, timeUpdate;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "time_start")
    public Integer getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Integer timeStart) {
        this.timeStart = timeStart;
    }

    @Basic
    @Column(name = "date_start")
    public Integer getDateStart() {
        return dateStart;
    }

    public void setDateStart(Integer dateStart) {
        this.dateStart = dateStart;
    }

    @Basic
    @Column(name = "price")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Basic
    @Column(name = "id_movie")
    public Integer getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(Integer idMovie) {
        this.idMovie = idMovie;
    }

    @Basic
    @Column(name = "id_room")
    public Integer getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(Integer idRoom) {
        this.idRoom = idRoom;
    }

    @Basic
    @Column(name = "created_by")
    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    @Basic
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @Column(name = "time_create")
    public Timestamp getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Timestamp timeCreate) {
        this.timeCreate = timeCreate;
    }

    @Basic
    @Column(name = "updated_by")
    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    @Basic
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @Column(name = "time_update")
    public Timestamp getTimeUpdate() {
        return timeUpdate;
    }

    public void setTimeUpdate(Timestamp timeUpdate) {
        this.timeUpdate = timeUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieTimeEntity that = (MovieTimeEntity) o;
        return id == that.id &&
                Objects.equals(timeStart, that.timeStart) &&
                Objects.equals(dateStart, that.dateStart) &&
                Objects.equals(price, that.price) &&
                Objects.equals(idMovie, that.idMovie) &&
                Objects.equals(idRoom, that.idRoom) &&
                Objects.equals(createBy, that.createBy) &&
                Objects.equals(timeCreate, that.timeCreate) &&
                Objects.equals(updateBy, that.updateBy) &&
                Objects.equals(timeUpdate, that.timeUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timeStart, dateStart, price, idMovie, idRoom, createBy, timeCreate, updateBy, timeUpdate);
    }
}
