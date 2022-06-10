package dev.helpDesk.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.helpDesk.dao.UserDAO;
import dev.helpDesk.dao.UserDaoPostgres;
import dev.helpDesk.entities.Ticket;
import dev.helpDesk.entities.User;
import dev.helpDesk.exceptions.EmailExistsException;
import dev.helpDesk.exceptions.UserNameExistsException;
import dev.helpDesk.services.UserService;
import dev.helpDesk.utils.CustomLogger;
import dev.helpDesk.utils.LogLevel;
//import dev.helpDesk.models.AppUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class UserServlet extends HttpServlet {

    private final ObjectMapper mapper;
    private final UserDaoPostgres userDao;
    private final UserService userService;
    private String exceptionMsg;
    private String exceptionMsg2;


    public UserServlet(ObjectMapper mapper, UserDaoPostgres userDao, UserService userService) {
        this.mapper = mapper;
        this.userDao = userDao;
        this.userService= userService;
    }


    @Override
    public void init() throws ServletException {
        System.out.println("[LOG] - UserServlet instantiated!");
        System.out.println("[LOG] - Init param, test-init-key: " + this.getServletConfig().getInitParameter("test-init-key"));
        System.out.println("[LOG] - Init param, user-servlet-key: " + this.getServletConfig().getInitParameter("user-servlet-key"));
        System.out.println("[LOG] - Init param, another-param: " + this.getServletConfig().getInitParameter("another-param"));
        System.out.println("[LOG] - Context param, test-context-key: " + this.getServletContext().getInitParameter("test-context-key"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> user =  userDao.getAllUsers(); //getAllUsers(req, resp);

        System.out.println("[LOG] - Was request filtered? " + req.getAttribute("was-filtered"));
//        String respPayload = mapper.writeValueAsString(user);
        resp.setContentType("application/json");
        resp.getWriter().write(mapper.writeValueAsString(user));
    }

//    private void getAllUsers(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
////        UserDAO userDao = new UserDaoPostgres();
//
//        try {
//            UserDAO userDao = new UserDaoPostgres();
//            List<User> users = userDao.getAllUsers();
//            req.setAttribute("users", users);
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new ServletException(e);
//        }
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String logString = "UserServlet received a post request at - " + LocalDateTime.now();
        CustomLogger.log(logString, LogLevel.INFO);





        try {

            User newUser = mapper.readValue(req.getInputStream(), User.class);



            UserDAO userDao = new UserDaoPostgres();
            List<User> users = userDao.getAllUsers();
//            HashMap rejectedUser = mapper.readValue(req.getInputStream(), HashMap.class);

            String providedUsername = (String) newUser.getUserName();
            String providedEmail = (String) newUser.getEmail();
            System.out.println("This is the new user to added to the database " + newUser);
            for (User user : users) {
                if (providedUsername.equals(user.getUserName())) {
                    logString = String.format("Duplicate username was rejected! - " + LocalDateTime.now());
                    CustomLogger.log(logString, LogLevel.ERROR);
                    resp.setStatus(400);
                    resp.setContentType("application/json");

                    HashMap<String, Object> errorMessage = new HashMap<>();
                    errorMessage.put("code", 400);
                    errorMessage.put("message", "[ERROR] - Username taken, please insert a different username.");
                    errorMessage.put("timestamp", LocalDateTime.now().toString());

                    resp.getWriter().write(mapper.writeValueAsString(errorMessage));



                    System.out.println("[ERROR] - Username taken, please insert a different username.");
                    return;
                } else if (providedEmail.equals(user.getEmail())) {
                    logString = String.format("Duplicate email was rejected! - " + LocalDateTime.now());
                    CustomLogger.log(logString, LogLevel.ERROR);

                    resp.setStatus(400);
                    resp.setContentType("application/json");

                    HashMap<String, Object> errorMessage = new HashMap<>();
                    errorMessage.put("code", 400);
                    errorMessage.put("message", "[ERROR] - Email taken, please insert a different username.");
                    errorMessage.put("timestamp", LocalDateTime.now().toString());

                    resp.getWriter().write(mapper.writeValueAsString(errorMessage));


                    System.out.println("[ERROR] - Email taken, please insert a different email.");
                    return;
                }

            }

            userService.createUser(newUser);




        } catch (Exception e) {
            logString = String.format("An error occurred while creating a User. More Information: %s", (Object) e.getStackTrace());
            CustomLogger.log(logString, LogLevel.INFO);
            e.printStackTrace();
        }
        resp.setStatus(204);

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
