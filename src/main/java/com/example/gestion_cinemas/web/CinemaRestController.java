package com.example.gestion_cinemas.web;

import com.example.gestion_cinemas.dao.FilmRepository;
import com.example.gestion_cinemas.dao.ImageRepository;
import com.example.gestion_cinemas.dao.TicketRepository;
import com.example.gestion_cinemas.metier.Film;
import com.example.gestion_cinemas.metier.Image;
import com.example.gestion_cinemas.metier.Ticket;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Transactional
@CrossOrigin("*")
public class CinemaRestController {

    private static final String UPLOADED_FOLDER = System.getProperty("user.home") +
            "/Films/";

    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ImageRepository imageRepository;

    @GetMapping(path = "/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] image(@PathVariable(name = "id") Long id) throws IOException {
        Film film = filmRepository.findById(id).get();
        String photoName = film.getPhoto().toString();
        File file = new File(System.getProperty("user.home") + "/Films/" + photoName + ".jpg");
        Path path = Paths.get(file.toURI());
        return Files.readAllBytes(path);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile uploadfile,
                                        @RequestParam("imageName") String imageName) {
        Image image = saveImage(uploadfile, imageName);
        if (image != null) {
            image = imageRepository.save(image);
            return new ResponseEntity(image, new HttpHeaders(), HttpStatus.OK);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Problems saving the image"
            );
        }

    }

    private Image saveImage(MultipartFile uploadfile, String imageName) throws ResponseStatusException {
        if (uploadfile.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "please select a file!"
            );
        }
        try {
            if (!uploadfile.isEmpty() && imageName != null) {
                byte[] bytes = uploadfile.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + "/" + imageName +".jpg");
                Files.write(path, bytes);
                return new Image(null, imageName);
            }

        } catch (IOException e) {
            System.out.println(e);
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Problems saving the file"
            );
        }
        return null;
    }

    @PostMapping("/payerTickets")
    @Transactional
    public List<Ticket> payerTickets(@RequestBody TicketForm ticketForm) {
        List<Ticket> tickets = new ArrayList<>();
        ticketForm.getTickets().forEach((id) -> {
            Ticket ticket = ticketRepository.findById(id).get();
            ticket.setNomClient(ticketForm.getNomClient());
            ticket.setReserved(true);
            ticket.setCodePaiement(ticketForm.getCodePaiement());
            ticketRepository.save(ticket);
            tickets.add(ticket);
        });
        return tickets;
    }
}

@Data
class TicketForm {
    private String nomClient;
    private int codePaiement;
    private List<Long> tickets = new ArrayList<>();
}
