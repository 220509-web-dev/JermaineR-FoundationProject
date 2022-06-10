package dev.heplDesk.dao;

import dev.heplDesk.entities.User;

import java.util.List;

public interface UserDAO {

    // Create
    User createUser(User user);

    // Read
    User getUserById(int id);
    List<User> getAllUsers();

    // Update
    User updateUser(User user);

    //Delete
    void deleteUserByInt(int id);
}
