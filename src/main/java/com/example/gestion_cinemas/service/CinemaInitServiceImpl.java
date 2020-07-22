package com.example.gestion_cinemas.service;

import com.example.gestion_cinemas.dao.*;
import com.example.gestion_cinemas.metier.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Service
@Transactional
public class CinemaInitServiceImpl implements ICinemaInitService {

    @Autowired
    private VilleRepository villeRepository;

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private SalleRepository salleRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private SeanceRepository seanceRepository;

    @Autowired
    private CategorieRepository categorieRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private ProjectionFilmRepository projectionFilmRepository;

    @Autowired
    private TicketRepository ticketRepository;


    @Override
    public void initVilles() {
        Stream.of("Casablance", "Marrakech", "Rabat", "Tanger").forEach(name -> {
                    /*Math.random() génère un nombre aléatoire entre 0 et 1
                    Math.random()*10 génère un nombre entier aléatoire
                    (int) (Math.random() * 10)  ==   1 + new Random().nextInt(9) génère un nombre entier aléatoire entre 1 et 10*/
                    /*villeRepository.save(new Ville(null, name, Math.random(), Math.random(), Math.random(), null));*/
                    Ville ville = new Ville();
                    ville.setName(name);
                    villeRepository.save(ville);
                }
        );
    }

    @Override
    public void initCinemas() {
        villeRepository.findAll().forEach(ville -> {
            Stream.of("Megarama", "IMAX", "FOUNOUN", "CHAHRAZAD", "DAOULIZ").forEach(name -> {
                    /*Math.random() génère un nombre aléatoire entre 0 et 1
                    Math.random()*10 génère un nombre entier aléatoire
                    (int) (Math.random() * 10)  ==   1 + new Random().nextInt(9) génère un nombre entier aléatoire entre 1 et 10*/
                        /*cinemaRepository.save(new Cinema(
                                null, name, Math.random(), Math.random(), Math.random(), 1 + new Random().nextInt(9),
                                null, villes.get(new Random().nextInt(3))));*/
                        Cinema cinema = new Cinema();
                        cinema.setName(name);
                        cinema.setVille(ville);
                        cinema.setNombreSalle(3 + new Random().nextInt(5));
                        cinemaRepository.save(cinema);
                    }
            );
        });
    }

    @Override
    public void initSalles() {
        cinemaRepository.findAll().forEach(cinema -> {
            for (int i = 0; i < cinema.getNombreSalle(); i++) {
                    /*Math.random() génère un nombre aléatoire entre 0 et 1
                    Math.random()*10 génère un nombre entier aléatoire
                    (int) (Math.random() * 10)  ==   1 + new Random().nextInt(9) génère un nombre entier aléatoire entre 1 et 10*/
                        /*cinemaRepository.save(new Cinema(
                                null, name, Math.random(), Math.random(), Math.random(), 1 + new Random().nextInt(9),
                                null, villes.get(new Random().nextInt(3))));*/
                Salle salle = new Salle();
                salle.setName("Salle " + (i + 1));
                salle.setCinema(cinema);
                salle.setNombrePlace(15 + new Random().nextInt(20));
                salleRepository.save(salle);
            }
        });
    }

    @Override
    public void initPlaces() {
        salleRepository.findAll().forEach(salle -> {
            for (int i = 0; i < salle.getNombrePlace(); i++) {
                Place place = new Place();
                place.setNumero(i+1);
                place.setName("Place " + (i + 1));
                place.setSalle(salle);
                placeRepository.save(place);
            }
        });
    }

    @Override
    public void initSeances() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Stream.of("12:00", "15:00", "17:00", "19:00", "21:00").forEach(heure -> {
            Seance seance = new Seance();
            try {
                seance.setHeureDebut(dateFormat.parse(heure));
                seanceRepository.save(seance);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void initCategories() {
        Stream.of("Histoire", "Action", "Fiction", "Drama").forEach(name -> {
            Categorie categorie = new Categorie();
            categorie.setName(name);
            categorieRepository.save(categorie);
        });
    }

    @Override
    public void initFilms() {
        double[] durees = new double[]{1, 1.5, 2, 2.5};
        List<Categorie> categories = categorieRepository.findAll();
        Stream.of("Dora", "L'appel de la foret", "Money Heist", "War").forEach(name -> {
            Film film = new Film();
            film.setTitre(name);
            film.setDuree(durees[new Random().nextInt(durees.length)]);
            film.setPhoto(name.replaceAll(" ", ""));
            film.setCategorie(categories.get(new Random().nextInt(categories.size())));
            filmRepository.save(film);
        });
    }

    @Override
    public void initProjections() {
        double[] prix = new double[]{30, 50, 60, 70, 80, 90, 100};
        List<Film> films = filmRepository.findAll();
        villeRepository.findAll().forEach(ville -> {
            ville.getCinemas().forEach(cinema -> {
                cinema.getSalles().forEach(salle -> {
                    int index = new Random().nextInt(films.size());
                    Film film = films.get(index);
                    seanceRepository.findAll().forEach(seance -> {
                        ProjectionFilm projection = new ProjectionFilm();
                        projection.setDateProjection(new Date());
                        projection.setFilm(film);
                        projection.setPrix(prix[new Random().nextInt(prix.length)]);
                        projection.setSalle(salle);
                        projection.setSeance(seance);
                        projectionFilmRepository.save(projection);
                    });
                });
            });
        });
    }

    @Override
    public void initTickets() {
        projectionFilmRepository.findAll().forEach(projection -> {
            projection.getSalle().getPlaces().forEach(place -> {
                Ticket ticket = new Ticket();
                ticket.setPlace(place);
                ticket.setProjection(projection);
                ticket.setPrix(projection.getPrix());
                ticket.setReserved(new Random().nextBoolean());
                ticketRepository.save(ticket);
            });
        });
    }
}
