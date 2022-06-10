package dev.helpDesk.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.helpDesk.dao.UserDAO;
import dev.helpDesk.dao.UserDaoPostgres;
import dev.helpDesk.entities.User;
import dev.helpDesk.utils.CustomLogger;
import dev.helpDesk.utils.LogLevel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class UserIdServlet extends HttpServlet {
    private final ObjectMapper mapper;
    private final UserDaoPostgres userDao;

    public UserIdServlet(ObjectMapper mapper, UserDaoPostgres userDao) {
        this.mapper = mapper;
        this.userDao = userDao;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String logString = "UserIdServlet received a get request at - " + LocalDateTime.now();
        CustomLogger.log(logString, LogLevel.INFO);
        List<User> userList = userDao.getAllUsers();
        System.out.println("Get User by Username or Id request. ");

        //Get user by Username
        String username = req.getParameter("username");

        try {
            int userId = Integer.parseInt(req.getParameter("id"));
            userList = userList.stream().filter(user -> user.getUserId() == userId).collect(Collectors.toList());

        } catch (NumberFormatException e) {
            System.out.println("The input was not valid.");
            logString = "Nothing was entered";
            CustomLogger.log(logString, LogLevel.ERROR);
        }


        if (username != null) {
            userList = userList.stream().filter(user -> user.getUserName().equals(username)).collect(Collectors.toList());
        }


        String result = mapper.writeValueAsString(userList);
        resp.setContentType("application/json");
        resp.getWriter().write(result);

    }
}