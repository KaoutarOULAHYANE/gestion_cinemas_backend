package com.example.gestion_cinemas.metier;

import org.springframework.data.rest.core.config.Projection;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Collection;
import java.util.Date;

@Projection(name = "p2", types = {Ticket.class})
public interface TicketProj {
    public Long getId();
    public Integer getCodePaiement();
    public String getNomClient();
    public Place getPlace();
    public double getPrix();
    public boolean getReserved();
}
