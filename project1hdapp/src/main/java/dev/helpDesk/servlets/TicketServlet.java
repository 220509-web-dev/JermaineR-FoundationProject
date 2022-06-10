package dev.helpDesk.servlets;


import com.fasterxml.jackson.databind.ObjectMapper;
import dev.helpDesk.dao.TicketDaoPostgres;
import dev.helpDesk.entities.Ticket;
//import dev.helpDesk.models.Tickets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet(
//        urlPatterns = "/tickets",
//        loadOnStartup = 2,
//        initParams = {
//                @WebInitParam(name = "tickets-servlet-key", value = "tickets-servlet-value"),
//                @WebInitParam(name = "another-param", value = "another-value")
//
//        }
//)
// annotation-based servlet registration
// bad practice will come back and fix
public class TicketServlet extends HttpServlet {

    private final ObjectMapper mapper;
    private final TicketDaoPostgres ticketsDao;


    public TicketServlet(ObjectMapper mapper, TicketDaoPostgres ticketsDao) {
        this.mapper = mapper;
        this.ticketsDao = ticketsDao;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Ticket> tickets =  ticketsDao.getAllTickets();
//        Tickets tickets = new Tickets(4,3,2,3,"broken mouse",20220, 0, 0, 24, 3);

        String respPayload = mapper.writeValueAsString(tickets);
        resp.setContentType("application/json");
        resp.getWriter().write(respPayload);
    }
}
