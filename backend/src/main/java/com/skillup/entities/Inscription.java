package com.skillup.entities;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inscription {
    @Id @GeneratedValue private Long id;
    private Long apprenantId;
    private Long sessionId;
    private String statut;
    private Boolean paiementOk;
}