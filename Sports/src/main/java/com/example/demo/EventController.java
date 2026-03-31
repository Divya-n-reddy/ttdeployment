package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "*")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private UserRepo userRepo;

    // Create event (ADMIN ONLY)
    @PostMapping("/create/{userId}")
    public Event createEvent(@RequestBody Event event, @PathVariable Long userId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getRole().equals("ROLE_ADMIN")) {
            throw new RuntimeException("Only admin can create events");
        }

        event.setCreatedBy(user);
        return eventService.createEvent(event);
    }

    // Book ticket
    @PostMapping("/{eventId}/book/{userId}")
    public String bookTicket(@PathVariable Long eventId,
                             @PathVariable Long userId) {

        return eventService.bookTicket(eventId, userId);
    }

    // View bookings of a user
    @GetMapping("/bookings/user/{userId}")
    public List<Registration> getUserBookings(@PathVariable Long userId) {
        return eventService.getUserBookings(userId);
    }
    @GetMapping("/admin/{userId}")
    public List<Event> getEventsByAdmin(@PathVariable Long userId) {
        return eventService.getEventsByAdmin(userId);
    }
    @DeleteMapping("/delete/{eventId}/{userId}")
    public String deleteEvent(@PathVariable Long eventId,
                              @PathVariable Long userId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getRole().equals("ROLE_ADMIN")) {
            throw new RuntimeException("Only admin can delete events");
        }

        eventService.deleteEvent(eventId);

        return "Event deleted successfully";
    }
    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }
 // Get ALL registrations (ADMIN)
    @GetMapping("/admin/bookings/{userId}")
    public List<Registration> getAllBookings(@PathVariable Long userId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getRole().equals("ROLE_ADMIN")) {
            throw new RuntimeException("Only admin can view bookings");
        }

        return eventService.getAllBookings();
    }
    
}