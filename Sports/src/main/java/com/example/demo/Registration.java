package com.example.demo;

import jakarta.persistence.*;

@Entity
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Event event;

    public Registration() {}

    public Registration(User user, Event event) {
        this.user = user;
        this.event = event;
    }

    public Long getId() { return id; }

    public User getUser() { return user; }

    public Event getEvent() { return event; }
}