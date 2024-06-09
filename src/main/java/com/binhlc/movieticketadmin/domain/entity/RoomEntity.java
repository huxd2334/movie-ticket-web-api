package com.binhlc.movieticketadmin.domain.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "room", schema = "movie_ticket")
public class RoomEntity {
    private int idRoom;
    private String name;
    private Integer numRow, numCol, createBy, updateBy;
    private Timestamp timeCreate, timeUpdate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_room")
    public int getIdRoom() {
        return idRoom;
    }
    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }
    @Column(name = "name_room")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Column(name = "num_row")
    public Integer getNumRow() {
        return numRow;
    }
    public void setNumRow(Integer numRow) {
        this.numRow = numRow;
    }
    @Column(name = "num_col")
    public Integer getNumCol() {
        return numCol;
    }
    public void setNumCol(Integer numCol) {
        this.numCol = numCol;
    }
    @Column(name = "created_by")
    public Integer getCreateBy() {
        return createBy;
    }
    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }
    @Column(name = "time_create")
    public Timestamp getTimeCreate() {
        return timeCreate;
    }
    public void setTimeCreate(Timestamp timeCreate) {
        this.timeCreate = timeCreate;
    }
    @Column(name = "updated_by")
    public Integer getUpdateBy() {
        return updateBy;
    }
    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }
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
        RoomEntity that = (RoomEntity) o;
        return idRoom == that.idRoom &&
                Objects.equals(name, that.name) &&
                Objects.equals(numRow, that.numRow) &&
                Objects.equals(numCol, that.numCol) &&
                Objects.equals(createBy, that.createBy) &&
                Objects.equals(timeCreate, that.timeCreate) &&
                Objects.equals(updateBy, that.updateBy) &&
                Objects.equals(timeUpdate, that.timeUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRoom, name, numRow, numCol, createBy, timeCreate, updateBy, timeUpdate);
    }

}
