package com.example.gestion_cinemas.metier;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Salle {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int nombrePlace;

    /*@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)*/
    @OneToMany(mappedBy = "salle")
    private Collection<Place> places;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "salle")
    private Collection<ProjectionFilm> projections;

    @ManyToOne
    @JoinColumn(name = "id_cinema")
    /*Empecher la boucle infinie*/
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Cinema cinema;
}
