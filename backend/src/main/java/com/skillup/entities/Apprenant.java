package com.skillup.entities;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Apprenant {
    @Id
    private Long id;

    private String nom;
    private String prenom;
    private String email;
    private int age;

    private double absRate30d;
    private double absRate90d;
    private double lateAvg30d;
    private int streakAbs;
    private double weekdayMon;
    private double weekdayTue;
    private double weekdayWed;
    private double weekdayThu;
    private double weekdayFri;

    // For AI recommendation
    private String objectifs;
    private String competences;
    private String cvText;
}