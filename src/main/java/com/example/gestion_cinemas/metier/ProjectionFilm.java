package com.example.gestion_cinemas.metier;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity(name = "projection")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProjectionFilm {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date dateProjection;
    @ManyToOne
    @JoinColumn(name = "id_film")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Film film;
    private double prix;
    @ManyToOne
    @JoinColumn(name = "id_salle")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Salle salle;
    @ManyToOne
    @JoinColumn(name = "id_seance")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Seance seance;

    @OneToMany(mappedBy = "projection")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Ticket> tickets;
}
