package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public MyEntity() {}

    public MyEntity(String name) {
        this.name = name;
    }

    // Getter/setter
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "MyEntity{id=" + id + ", name='" + name + "'}";
    }
}
