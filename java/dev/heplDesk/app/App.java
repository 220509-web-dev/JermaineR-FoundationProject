package dev.heplDesk.app;

import dev.heplDesk.dao.TicketDAO;
import dev.heplDesk.dao.TicketDaoPostgres;
import dev.heplDesk.dao.UserDAO;
import dev.heplDesk.dao.UserDaoPostgres;
import dev.heplDesk.entities.Ticket;
import dev.heplDesk.entities.User;

public class App {
    public static void main(String[] args) {

       UserDAO userDAO = new UserDaoPostgres();
//        User user = userDAO.getUserById(4);
//        System.out.println(user);
        System.out.println(userDAO.getAllUsers());

        TicketDAO ticketDAO = new TicketDaoPostgres();
//        Ticket ticket = ticketDAO.getTicketById(4);
//        System.out.println(ticket);
        System.out.println(ticketDAO.getAllTickets());
    }
}
