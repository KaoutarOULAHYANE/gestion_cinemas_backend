package com.example.gestion_cinemas.metier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Ticket {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@Column(unique = true,nullable = true)
    private Integer codePaiement;
    private String nomClient;
    @ManyToOne
    @JoinColumn(name = "id_place")
    private Place place;
    private double prix;
    @ManyToOne
    @JoinColumn(name = "id_projection_film")
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ProjectionFilm projection;

    private boolean reserved = false;
}
