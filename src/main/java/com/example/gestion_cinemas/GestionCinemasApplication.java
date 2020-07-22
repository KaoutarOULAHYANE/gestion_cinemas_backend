package com.example.gestion_cinemas;

import com.example.gestion_cinemas.metier.*;
import com.example.gestion_cinemas.service.ICinemaInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class GestionCinemasApplication implements CommandLineRunner {

    @Autowired
    private ICinemaInitService iCinemaInitService;

    @Autowired
    private RepositoryRestConfiguration configuration;

    public static void main(String[] args) {
        SpringApplication.run(GestionCinemasApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Gestion des cinemas !");
        configuration.exposeIdsFor(Film.class);
        configuration.exposeIdsFor(Salle.class);
        configuration.exposeIdsFor(Ticket.class);
        configuration.exposeIdsFor(ProjectionFilm.class);
        configuration.exposeIdsFor(Seance.class);
        iCinemaInitService.initVilles();
        iCinemaInitService.initCinemas();
        iCinemaInitService.initSalles();
        iCinemaInitService.initPlaces();
        iCinemaInitService.initSeances();
        iCinemaInitService.initCategories();
        iCinemaInitService.initFilms();
        iCinemaInitService.initProjections();
        iCinemaInitService.initTickets();

        /*Stream.of("Casablance", "Marrakech", "Rabat", "Tanger")
                .forEach(name -> {
                    *//*Math.random() génère un nombre aléatoire entre 0 et 1
                    Math.random()*10 génère un nombre entier aléatoire
                    (int) (Math.random() * 10)  ==   1 + new Random().nextInt(9) génère un nombre entier aléatoire entre 1 et 10*//*
                    villeRepository.save(new Ville(null, name, Math.random(), Math.random(), Math.random(),null));
                });

        List<Ville> villes = villeRepository.findAll();

        *//*Créer une liste*//*
        Stream.of("Megarama", "Rif", "Lynx", "IMAX", "Royal", "Rialto", "Arribat Center", "CINEATLAS", "Renaissance")
                .forEach(name -> {
                    *//*Math.random() génère un nombre aléatoire entre 0 et 1
                    Math.random()*10 génère un nombre entier aléatoire
                    (int) (Math.random() * 10)  ==   1 + new Random().nextInt(9) génère un nombre entier aléatoire entre 1 et 10*//*
                    cinemaRepository.save(new Cinema(
                            null, name, Math.random(), Math.random(), Math.random(), 1 + new Random().nextInt(9),
                            null, villes.get(new Random().nextInt(3))));
                });
        cinemaRepository.findAll().forEach(cinema -> {
            System.out.println(cinema.getName());
        });

        cinemaRepository.findAll().forEach(cinema -> {
            for (int i=0;i<10;i++){
                Salle salle = new Salle();
                salle.setName("Salle "+i);
                salle.setCinema(cinema);
                salle.setNombrePlace(1 + new Random().nextInt(999));
                salleRepository.save(salle);
            }
        });*/
    }
}
