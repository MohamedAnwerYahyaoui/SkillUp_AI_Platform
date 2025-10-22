package com.skillup.entities;
import jakarta.persistence.*;
import lombok.*;
import java.time.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Session {
    @Id @GeneratedValue private Long id;
    private Long formationId;
    private Long formateurId;
    private java.time.LocalDateTime dateDebut;
    private java.time.LocalDateTime dateFin;
    private String salle;
    private String mode;
    private Integer capaciteMax;
}