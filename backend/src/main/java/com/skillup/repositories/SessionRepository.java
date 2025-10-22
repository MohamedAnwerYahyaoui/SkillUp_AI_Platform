package com.skillup.repositories;
import com.skillup.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
public interface SessionRepository extends JpaRepository<Session, Long> {}