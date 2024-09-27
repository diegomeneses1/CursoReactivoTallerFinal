package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cashout")
public class Cashout {
    @Id
    private String id;
    private String userId;
    private Double amount;

    public String getUserId() {
        return userId;
    }

    public String getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setUserId(String nombre) {
        this.userId = nombre;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAmount(Double precio) {
        this.amount = precio;
    }
}


