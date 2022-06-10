package dev.helpDesk.dao;

import dev.helpDesk.entities.Ticket;

import java.util.List;

public interface TicketDAO {

    // Create
    Ticket createTicket(Ticket ticket);

    // Read
    Ticket getTicketById(int id);
    List<Ticket> getAllTickets();

    // Update
    Ticket updateTicket(Ticket ticket);

    //Delete
    void deleteTicketByInt(int id);
}
