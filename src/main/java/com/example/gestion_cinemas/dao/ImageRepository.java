package com.example.gestion_cinemas.dao;

import com.example.gestion_cinemas.metier.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface ImageRepository extends JpaRepository<Image,Long> {
}
