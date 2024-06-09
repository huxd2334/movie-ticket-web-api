package com.binhlc.movieticketadmin.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "ticket", schema = "movie_cinema")
public class TicketEntity {
    private int idTicket;
    private Integer status, price, idMovieTime, idRoomDetail, createBy, updateBy;
    private Timestamp timeCreate, timeUpdate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ticket")
    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    @Column(name = "status") // 1: chưa chọn, 2: đã thanh toán, 3: đang chọn
    public Integer getStatus() {
        return status;
    }  //

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "price")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Column(name = "id_movie_time")
    public Integer getIdMovieTime() {
        return idMovieTime;
    }

    public void setIdMovieTime(Integer idMovieTime) {
        this.idMovieTime = idMovieTime;
    }

    @Column(name = "id_room_detail")
    public Integer getIdRoomDetail() {
        return idRoomDetail;
    }

    public void setIdRoomDetail(Integer idRoomDetail) {
        this.idRoomDetail = idRoomDetail;
    }

    @Column(name = "created_by")
    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    @Column(name = "updated_by")
    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @Column(name = "time_create")
    public Timestamp getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Timestamp timeCreate) {
        this.timeCreate = timeCreate;
    }

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
        TicketEntity that = (TicketEntity) o;
        return idTicket == that.idTicket &&
                Objects.equals(status, that.status) &&
                Objects.equals(price, that.price) &&
                Objects.equals(idMovieTime, that.idMovieTime) &&
                Objects.equals(idRoomDetail, that.idRoomDetail) &&
                Objects.equals(createBy, that.createBy) &&
                Objects.equals(updateBy, that.updateBy) &&
                Objects.equals(timeCreate, that.timeCreate) &&
                Objects.equals(timeUpdate, that.timeUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTicket, status, price, idMovieTime, idRoomDetail, createBy, updateBy, timeCreate, timeUpdate);
    }
}
