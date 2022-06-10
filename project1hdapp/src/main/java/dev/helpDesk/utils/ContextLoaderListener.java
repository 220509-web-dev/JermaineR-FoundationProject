package dev.helpDesk.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.helpDesk.dao.TicketDaoPostgres;
import dev.helpDesk.dao.UserDAO;
import dev.helpDesk.dao.UserDaoPostgres;
import dev.helpDesk.entities.User;
import dev.helpDesk.filters.CustomFilter;
import dev.helpDesk.services.UserService;
import dev.helpDesk.servlets.AuthServlet;
import dev.helpDesk.servlets.TicketServlet;
import dev.helpDesk.servlets.UserIdServlet;
import dev.helpDesk.servlets.UserServlet;

import javax.servlet.*;
import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.List;

public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        System.out.println("[LOG] - The servlet context was initialized at " + LocalDateTime.now());

        ObjectMapper mapper = new ObjectMapper();
        TicketDaoPostgres ticketsDao = new TicketDaoPostgres();
        UserDaoPostgres userDao = new UserDaoPostgres();
        UserService service = new UserService(userDao);
        UserIdServlet userIdServlet = new UserIdServlet(mapper, userDao);
        UserServlet userServlet = new UserServlet(mapper, userDao, service);
        TicketServlet ticketServlet = new TicketServlet(mapper, ticketsDao);
        AuthServlet authServlet = new AuthServlet(mapper, userDao);

        ServletContext context = sce.getServletContext();

        CustomFilter customFilter = new CustomFilter();
        context.addFilter("CustomFilter", customFilter)
                .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST),true,"/*");
        context.addServlet("UserIdServlet", userIdServlet).addMapping("/id");
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");
        context.addServlet("TicketServlet", ticketServlet).addMapping("/tickets/*");
        //context.addServlet("UserServlet", userServlet).addMapping("/users/*");
        ServletRegistration.Dynamic registeredServlet = context.addServlet("UserServlet", userServlet);
        registeredServlet.setLoadOnStartup(3);
        registeredServlet.setInitParameter("user-servlet-key", "user-servlet-value");
        registeredServlet.setInitParameter("another-param", "another-value");
        registeredServlet.addMapping("/users/*");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("[LOG] - The servlet context was destroyed at " + LocalDateTime.now());

    }
}
