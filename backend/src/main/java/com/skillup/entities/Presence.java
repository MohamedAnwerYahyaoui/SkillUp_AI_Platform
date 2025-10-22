package com.skillup.entities;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Presence {
    @Id @GeneratedValue private Long id;
    private Long sessionId;
    private Long apprenantId;
    private Boolean present;
    private Integer retardMinutes;
    private String justification;
    private String motifAbsence;
}