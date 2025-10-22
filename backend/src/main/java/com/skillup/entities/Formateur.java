package com.skillup.entities;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Formateur {
    @Id @GeneratedValue private Long id;
    private String nom;
    private String email;
    private String competences;
    private String disponibilites;
    private Double note;
}