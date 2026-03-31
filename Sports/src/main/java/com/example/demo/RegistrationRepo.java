package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepo extends JpaRepository<Registration, Long> {
	List<Registration> findByUser(User user);
	boolean existsByUserAndEvent(User user, Event event);
}
