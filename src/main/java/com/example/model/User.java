package com.example.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    @NotBlank(message = "The name is required")
    private String name;
    @Positive(message = "The balance must be positive")
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


