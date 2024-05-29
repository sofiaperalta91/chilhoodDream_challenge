package org.example.childhooddream.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "train")
@Getter
@Setter
@NoArgsConstructor
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String distanceBetweenStop;
    private String maxSpeed;
    private Boolean sharingTracks;
    private Boolean gradeCrossing;
    private String trainFrequency;
    private String amenities;
}