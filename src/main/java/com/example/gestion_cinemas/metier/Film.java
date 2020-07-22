package com.example.gestion_cinemas.metier;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Film {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private double duree;
    @Temporal(TemporalType.DATE)
    private Date dateSortie;
    private String description;
    private String photo;
    private String realisateur;
    @OneToMany(mappedBy = "film")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    Collection<ProjectionFilm> projections;
    @ManyToOne
    @JoinColumn(name = "id_categorie")
    private Categorie categorie;

}
