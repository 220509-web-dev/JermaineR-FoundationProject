package dev.helpDesk.dao;

import dev.helpDesk.entities.User;

import java.util.List;

public interface UserDAO {

    // Create
    User createUser(User user);

    // Read
    User getUserById(int id);
    List<User> getAllUsers();
    User getUserByUser(String username);
    User getUserByEmail(String email);

    // Update
    User updateUser(User user);

    //Delete
    void deleteUserByInt(int id);
}
