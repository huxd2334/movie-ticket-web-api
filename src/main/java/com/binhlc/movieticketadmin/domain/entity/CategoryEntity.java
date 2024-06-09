package com.binhlc.movieticketadmin.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "category", schema = "movie_ticket")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    @Column(name = "created_by")
    private int createBy;
    @Basic
    @Column(name = "time_create")
    private Timestamp timeCreate;
    @Basic
    @Column(name = "updated_by")
    private int updateBy;

    @Basic
    @Column(name = "time_update")
    private Timestamp timeUpdate;

    public void setId(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }




    public String getName() {
        return name;
    }


    @Override
    public boolean equals(Object obj) {
        if (this==obj) return true;
        if (obj==null || getClass()!=obj.getClass()) return false;

        CategoryEntity other = (CategoryEntity)obj;
        return id== other.id &&
                Objects.equals(name, other.name) &&
                Objects.equals(timeCreate, other.timeCreate) &&
                Objects.equals(timeUpdate, other.timeUpdate) &&
                Objects.equals(createBy, other.createBy) &&
                Objects.equals(updateBy, other.updateBy);

    }


    public int getCreateBy() {
        return createBy;
    }


    public Timestamp getTimeCreate() {
        return timeCreate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, createBy, timeCreate, updateBy, timeUpdate);
    }


    public int getUpdateBy() {
        return updateBy;
    }


    public Timestamp getTimeUpdate() {
        return timeUpdate;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public void setTimeCreate(Timestamp timeCreate) {
        this.timeCreate = timeCreate;
    }

    public void setUpdateBy(int updateBy) {
        this.updateBy = updateBy;
    }

    public void setTimeUpdate(Timestamp timeUpdate) {
        this.timeUpdate = timeUpdate;
    }
}
