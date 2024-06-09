package com.binhlc.movieticketadmin.domain.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "room_detail", schema = "movie_ticket")
public class RoomDetailEntity {
    private int id;
    private Integer roww, col, type, position, room;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @Column(name = "roww")
    public Integer getRoww() {
        return roww;
    }
    public void setRoww(Integer roww) {
        this.roww = roww;
    }
    @Column(name = "col")
    public Integer getCol() {
        return col;
    }
    public void setCol(Integer col) {
        this.col = col;
    }
    @Column(name = "type")
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    @Column(name = "position")
    public Integer getPosition() {
        return position;
    }
    public void setPosition(Integer position) {
        this.position = position;
    }
    @Column(name = "room")
    public Integer getRoom() {
        return room;
    }
    public void setRoom(Integer room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomDetailEntity that = (RoomDetailEntity) o;
        return id == that.id &&
                Objects.equals(roww, that.roww) &&
                Objects.equals(col, that.col) &&
                Objects.equals(type, that.type) &&
                Objects.equals(position, that.position) &&
                Objects.equals(room, that.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roww, col, type, position, room);
    }

}
