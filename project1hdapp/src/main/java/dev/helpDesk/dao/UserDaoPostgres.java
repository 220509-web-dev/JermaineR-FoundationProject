package dev.helpDesk.dao;

import dev.helpDesk.entities.User;
import dev.helpDesk.exceptions.UserNameExistsException;
import dev.helpDesk.utils.ConnectionFactory;
//import dev.helpDesk.utils.ConnectionUtil;

import dev.helpDesk.utils.CustomLogger;
import dev.helpDesk.utils.LogLevel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoPostgres implements UserDAO {

    @Override
    public User createUser(User user) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String logString = "Initiating create method.";
            CustomLogger.log(logString, LogLevel.INFO);
            String sql = "INSERT INTO helpdesk_app.app_users (department_id,last_name,first_name,phone,email,username,password,reportsTo,isadmin) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, 2);
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getPhone());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getUserName());
            ps.setString(7, user.getPassword());
            ps.setInt(8, 5);
            ps.setBoolean(9, false);

            ps.execute();

            // getting the generated primary key value
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int generatedId = rs.getInt("id");

            user.setUserId(generatedId);
            logString = "Created user.";
            CustomLogger.log(logString, LogLevel.INFO);
            return user;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserById(int id) {
        // try with resources. Automatically closes the connection once the ty block finishes

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "select * from helpdesk_app.app_users where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery(); //JDBC actually interacts with the DB

            // Get first record
            rs.next();

            User user = new User();
            user.setUserId(id);
            user.setDepartmentId(rs.getInt("department_id"));
            user.setLastName(rs.getString("last_name"));
            user.setFirstName(rs.getString("first_name"));
            user.setPhone(rs.getString("phone"));
            user.setEmail(rs.getString("email"));
            user.setUserName(rs.getString("username"));
            user.setPassword((rs.getString("password")));
            user.setReportsTo(rs.getInt("reportsto"));
            user.setIsadmin(rs.getBoolean("isadmin"));
            return user;


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "select * from helpdesk_app.app_users";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<User> users = new ArrayList<User>();

            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("id"));
                user.setDepartmentId(rs.getInt("department_id"));
                user.setLastName(rs.getString("last_name"));
                user.setFirstName(rs.getString("first_name"));
                user.setPhone(rs.getString("phone"));
                user.setEmail(rs.getString("email"));
                user.setUserName(rs.getString("username"));
                user.setPassword((rs.getString("password")));
                user.setReportsTo(rs.getInt("reportsto"));
                user.setIsadmin(rs.getBoolean("isadmin"));

                users.add(user);
            }
            return users;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserByUser(String username) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "select * from forum_app.app_users where username = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            //Get First Record
            rs.next();

            User user = new User();
            user.setUserId(rs.getInt("id"));
            user.setDepartmentId(rs.getInt("department_id"));
            user.setLastName(rs.getString("last_name"));
            user.setFirstName(rs.getString("first_name"));
            user.setPhone(rs.getString("phone"));
            user.setEmail(rs.getString("email"));
            user.setUserName(rs.getString("username"));
            user.setPassword((rs.getString("password")));
            user.setReportsTo(rs.getInt("reportsto"));
            user.setIsadmin(rs.getBoolean("isadmin"));
            return user;

        } catch (SQLException Exception) {
            String logString = String.format("User was not found");
            CustomLogger.log(logString, LogLevel.ERROR);
            CustomLogger.parser();
            System.err.println("Exception: Username: " + username + " not found.");
            Exception.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String logString = "Attempting to retrieve User by Email.";
            CustomLogger.log(logString, LogLevel.INFO);
            String sql = "select * from forum_app.app_users where email = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            //Get First Record
            rs.next();

            User user = new User();
            user.setUserId(rs.getInt("id"));
            user.setDepartmentId(rs.getInt("department_id"));
            user.setLastName(rs.getString("last_name"));
            user.setFirstName(rs.getString("first_name"));
            user.setPhone(rs.getString("phone"));
            user.setEmail(rs.getString("email"));
            user.setUserName(rs.getString("username"));
            user.setPassword((rs.getString("password")));
            user.setReportsTo(rs.getInt("reportsto"));
            user.setIsadmin(rs.getBoolean("isadmin"));
            logString = "Retrieved User successfully!.";
            CustomLogger.log(logString, LogLevel.INFO);
            CustomLogger.parser();
            return user;

        } catch (SQLException exception) {
            String logString = String.format("User was not found... More Information: Username: %s not found.", email);
            CustomLogger.log(logString, LogLevel.ERROR);
            CustomLogger.parser();
            System.err.println("Exception: Username: " + email + " not found.");
        }
        return null;
    }

    @Override
    public User updateUser(User user) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "update helpdesk_app.app_users set department_id = ?, last_name = ?, first_name = ?, phone = ?,  email = ?, username = ?, password = ?, reportsto = ?, isadmin = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getDepartmentId());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getPhone());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getUserName());
            ps.setString(7, user.getPassword());
            ps.setInt(8, user.getReportsTo());
            ps.setBoolean(8, user.isIsadmin());
            ps.setInt(9, user.getUserId());


            ps.execute();

            return user;

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteUserByInt(int id) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "delete from helpdesk_app.app_users where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }
}

