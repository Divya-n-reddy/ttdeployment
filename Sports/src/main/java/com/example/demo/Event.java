package com.example.demo;

import jakarta.persistence.*;
import java.time.LocalDate; // ✅ MUST be here (top)
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @ManyToOne
    private User createdBy;

    private String venue;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate eventDate;

    private int totalSeats;

    private int availableSeats;

    public Event() {}

    // ✅ FIXED CONSTRUCTOR
    public Event(String title, String description, String venue, LocalDate eventDate, int totalSeats) {
        this.title = title;
        this.description = description;
        this.venue = venue;
        this.eventDate = eventDate;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }

    // Getters & Setters

    public Long getId() { return id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getVenue() { return venue; }

    public void setVenue(String venue) { this.venue = venue; }

    // ✅ ONLY THIS ONE (correct)
    public LocalDate getEventDate() { return eventDate; }

    public void setEventDate(LocalDate eventDate) { this.eventDate = eventDate; }

    public int getTotalSeats() { return totalSeats; }

    public void setTotalSeats(int totalSeats) { this.totalSeats = totalSeats; }

    public int getAvailableSeats() { return availableSeats; }

    public void setAvailableSeats(int availableSeats) { this.availableSeats = availableSeats; }

    public User getCreatedBy() { return createdBy; }

    public void setCreatedBy(User createdBy) { this.createdBy = createdBy; }
}