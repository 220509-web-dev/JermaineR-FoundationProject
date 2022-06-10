package dev.helpDesk.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.helpDesk.dao.UserDAO;
import dev.helpDesk.dao.UserDaoPostgres;
import dev.helpDesk.dto.ErrorResponse;
import dev.helpDesk.dto.ResourceCreationResponse;
import dev.helpDesk.entities.User;
import dev.helpDesk.exceptions.EmailExistsException;
import dev.helpDesk.exceptions.InvalidCredentialsException;
import dev.helpDesk.exceptions.UserNameExistsException;

import java.util.List;

public class UserService {
    private final UserDAO userDAO;
    private String exceptionMsg;
    private ErrorResponse error;
    private List<User> users;
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;

    }

    public ResourceCreationResponse createUser(User user)
    {
        if (
                user == null  || user.getUserName() == null || user.getUserName().equals("") ||
                user.getPassword() == null || user.getPassword().equals("") ||
                user.getEmail() == null || user.getEmail().equals("")) {
            exceptionMsg = "Username and password must not be null.";
            throw new InvalidCredentialsException(exceptionMsg);
        }


//        if (user.equals(user.getUserName())) {
//            exceptionMsg = "This username is already in use.";
//            throw new UserNameExistsException(exceptionMsg);
//        }
//
//        if (user.equals(user.getEmail())) {
//        exceptionMsg = "This email is already in use.";
//        throw new EmailExistsException(exceptionMsg);
//            throw new EmailExistsException("This email is already in use.");
//
//
//        }

        return new ResourceCreationResponse(userDAO.createUser(user).getUserId());
    }


}