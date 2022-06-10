package dev.heplDesk.dao;

import dev.heplDesk.entities.User;
import dev.heplDesk.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoPostgres implements UserDAO {

    @Override
    public User createUser(User user) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO helpdesk_app.app_users VALUES (default,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, user.getDepartmentId());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getFirstName());
            ps.setInt(4, user.getPhone());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getUserName());
            ps.setString(7, user.getPassword());
            ps.setInt(8, user.getReportsTo());

            ps.execute();

            // getting the generated primary key value
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int generatedId = rs.getInt("id");

            user.setUserId(generatedId);
            return user;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserById(int id) {
        // try with resources. Automatically closes the connection once the ty block finishes

        try (Connection conn = ConnectionUtil.getConnection()) {
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
            user.setPhone(rs.getInt("phone"));
            user.setEmail(rs.getString("email"));
            user.setUserName(rs.getString("username"));
            user.setPassword((rs.getString("password")));
            user.setReportsTo(rs.getInt("reportsto"));
            return user;


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {

        try (Connection conn = ConnectionUtil.getConnection()) {
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
                user.setPhone(rs.getInt("phone"));
                user.setEmail(rs.getString("email"));
                user.setUserName(rs.getString("username"));
                user.setPassword((rs.getString("password")));
                user.setReportsTo(rs.getInt("reportsto"));
                users.add(user);
            }
            return users;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public User updateUser(User user) {

        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "update helpdesk_app.app_users set department_id = ?, last_name = ?, first_name = ?, phone = ?,  email = ?, username = ?, password = ?, reportsto = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getDepartmentId());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getFirstName());
            ps.setInt(4, user.getPhone());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getUserName());
            ps.setString(7, user.getPassword());
            ps.setInt(8, user.getReportsTo());
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

        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "delete from helpdesk_app.app_users where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }
}

