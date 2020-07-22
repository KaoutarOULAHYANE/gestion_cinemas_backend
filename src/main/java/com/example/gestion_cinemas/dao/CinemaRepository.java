package com.example.gestion_cinemas.dao;


import com.example.gestion_cinemas.metier.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

/*il remplace un RestController qui gère une entité (toutes les méthodes de la classe CinemaRestController) */
/*RestController est utiliser pour ajouter d'autres fonctionalités*/
@RepositoryRestResource
@CrossOrigin("*")
public interface CinemaRepository extends JpaRepository<Cinema,Long> {
}
