package com.example.gestion_cinemas.metier;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@Entity
/*Getters & Setters*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double longitude;
    private double latitude;
    private double altitude;
    private int nombreSalle;

    @OneToMany(mappedBy = "cinema")
    private Collection<Salle> salles;
    @ManyToOne
    @JoinColumn(name = "id_ville")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Ville ville;
}
