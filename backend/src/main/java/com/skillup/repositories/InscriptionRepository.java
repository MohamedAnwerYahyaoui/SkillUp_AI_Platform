package com.skillup.repositories;
import com.skillup.entities.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
public interface InscriptionRepository extends JpaRepository<Inscription, Long> {}