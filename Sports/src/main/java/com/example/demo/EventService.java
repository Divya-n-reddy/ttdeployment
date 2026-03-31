package com.example.demo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    private EventRepo eventRepo;

    @Autowired
    private RegistrationRepo registrationRepo;

    @Autowired
    private UserRepo userRepo;

    // Create Event
    public Event createEvent(Event event) {
        event.setAvailableSeats(event.getTotalSeats());
        return eventRepo.save(event);
    }

    public String bookTicket(Long eventId, Long userId) {

        Event event = eventRepo.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ✅ 1. Check if event is past
        if (event.getEventDate() == null || event.getEventDate().isBefore(LocalDate.now())) {
            return "⛔ Booking closed! Event already finished.";
        }

        // ✅ 2. Already booked check
        boolean alreadyBooked = registrationRepo.existsByUserAndEvent(user, event);

        if (alreadyBooked) {
            return "⚠️ You have already booked this event!";
        }

        // ✅ 3. Seat check
        if (event.getAvailableSeats() <= 0) {
            return "❌ Sorry! All seats are booked.";
        }

        // ✅ 4. Book ticket
        event.setAvailableSeats(event.getAvailableSeats() - 1);
        eventRepo.save(event);

        Registration registration = new Registration(user, event);
        registrationRepo.save(registration);

        return "✅ Ticket booked successfully!";
    }

    public List<Registration> getAllBookings() {
        return registrationRepo.findAll();
    }

    public List<Registration> getUserBookings(Long userId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return registrationRepo.findByUser(user);
    }

    public List<Event> getEventsByAdmin(Long userId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return eventRepo.findByCreatedBy(user);
    }

    public void deleteEvent(Long eventId) {

        Event event = eventRepo.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        eventRepo.delete(event);
    }

    public List<Event> getAllEvents() {
        return eventRepo.findAll();
    }
}