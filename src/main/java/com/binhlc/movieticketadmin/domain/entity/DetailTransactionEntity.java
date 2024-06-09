package com.binhlc.movieticketadmin.domain.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "detail_transaction", schema = "movie_ticket")
public class DetailTransactionEntity {
    private int id;
    private Integer idTransaction, idTicket;

    public DetailTransactionEntity() {
    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "id_transaction")
    public Integer getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(Integer idTransaction) {
        this.idTransaction = idTransaction;
    }

    @Column(name = "id_ticket")
    public Integer getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idTransaction, idTicket);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        DetailTransactionEntity other = (DetailTransactionEntity) obj;
        return id == other.id &&
                Objects.equals(idTransaction, other.idTransaction)
                && Objects.equals(idTicket, other.idTicket);
    }
}
