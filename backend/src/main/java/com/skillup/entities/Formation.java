package com.skillup.entities;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Formation {
    @Id @GeneratedValue private Long id;
    private String titre;
    private String objectifs;
    private String syllabus;
    private String niveau;
    private String tags;
    private Integer capacite;
    private Double cout;
    private Boolean actif;
}