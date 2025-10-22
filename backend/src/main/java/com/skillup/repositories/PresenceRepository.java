package com.skillup.repositories;
import com.skillup.entities.Presence;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PresenceRepository extends JpaRepository<Presence, Long> {}