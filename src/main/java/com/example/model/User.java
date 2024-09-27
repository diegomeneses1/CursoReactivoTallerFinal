package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String name;
    private Double balance;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBalance(Double precio) {
        this.balance = precio;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}


