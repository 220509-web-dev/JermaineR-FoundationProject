package dev.helpDesk.servlets;


import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.revature.quizzard.models.AppUser;
import com.fasterxml.jackson.databind.util.JSONPObject;
import dev.helpDesk.dao.UserDAO;
import dev.helpDesk.dao.UserDaoPostgres;
import dev.helpDesk.entities.User;
import dev.helpDesk.utils.CustomLogger;
import dev.helpDesk.utils.LogLevel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AuthServlet extends HttpServlet {

    private final ObjectMapper mapper;
    private final UserDaoPostgres userDao;

    public AuthServlet(ObjectMapper mapper, UserDaoPostgres userDao) {
        this.mapper = mapper;
        this.userDao = userDao;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }






    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        resp.setStatus(204);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String logString = "AuthServlet received a post request at - " + LocalDateTime.now();
        CustomLogger.log(logString, LogLevel.INFO);

        try {

        UserDAO userDao = new UserDaoPostgres();
        List<User> users =  userDao.getAllUsers();
        HashMap credentials = mapper.readValue(req.getInputStream(), HashMap.class);
            System.out.println(credentials);
        String providedUsername = (String) credentials.get("userName");
        String providedPassword = (String) credentials.get("password");

        for (User user : users) {
            if (providedUsername.equals(user.getUserName()) && providedPassword.equals(user.getPassword())) {
                System.out.println("[LOG] - found user!");
                System.out.println("7");

                // Because HTTP is a stateless protocol, we need a session to persist data across multiple requests
                HttpSession session = req.getSession(); // use req.getSession(false) to prevent a session from being made
                session.setAttribute("auth-user", user); // this attribute is visible on any requests with this session attached

                resp.setStatus(200);
                resp.setContentType("application/json");
                resp.getWriter().write(mapper.writeValueAsString(user));

                return; // return here otherwise we continue and bad things might happen

            }
        }

    }catch (IOException e) {
        logString = String.format("An error occurred, No user found with provided credentials More Information: %s", (Object) e.getStackTrace());
        CustomLogger.log(logString, LogLevel.INFO);
        e.printStackTrace();
    }

        resp.setStatus(404);
        resp.setContentType("application/json");

        HashMap<String, Object> errorMessage = new HashMap<>();
        errorMessage.put("code", 404);
        errorMessage.put("message", "No user found with provided credentials");
        errorMessage.put("timestamp", LocalDateTime.now().toString());

        resp.getWriter().write(mapper.writeValueAsString(errorMessage));

    }


}