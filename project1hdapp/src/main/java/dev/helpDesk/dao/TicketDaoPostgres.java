package dev.helpDesk.dao;

import dev.helpDesk.entities.Ticket;
import dev.helpDesk.utils.ConnectionFactory;
//import dev.helpDesk.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDaoPostgres implements TicketDAO {

    @Override
    public Ticket createTicket(Ticket ticket) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "INSERT INTO helpdesk_app.app_tickets VALUES (default,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, ticket.getStatusId());
            ps.setInt(3, ticket.getUserId());
            ps.setInt(2, ticket.getTypeId());
            ps.setString(4, ticket.getDescription());
            ps.setTimestamp(5, ticket.getSubmittedOn());
            ps.setInt(6, ticket.getOpenDateTime());
            ps.setInt(7, ticket.getCloseDate());
            ps.setInt(8, ticket.getExpectedWorkHours());
            ps.setInt(9, ticket.getAssignedTo());

            ps.execute();

            // getting the generated primary key value
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int generatedId = rs.getInt("id");

            ticket.setTicketId(generatedId);
            return ticket;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public Ticket getTicketById(int id) {
        // try with resources. Automatically closes the connection once the ty block finishes

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "select * from helpdesk_app.app_tickets where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery(); //JDBC actually interacts with the DB

            // Get first record
            rs.next();

            Ticket ticket = new Ticket();
            ticket.setTicketId(id);
            ticket.setStatusId(rs.getInt("status_id"));
            ticket.setUserId(rs.getInt("user_id"));
            ticket.setTypeId(rs.getInt("type_id"));
            ticket.setDescription(rs.getString("description"));
            ticket.setSubmittedOn(rs.getTimestamp("submitted_on"));
            ticket.setOpenDateTime(rs.getInt("open_date_time"));
            ticket.setCloseDate((rs.getInt("close_date_time")));
            ticket.setExpectedWorkHours(rs.getInt("expected_work_hours"));
            ticket.setAssignedTo(rs.getInt("assigned_to"));
            return ticket;


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Ticket> getAllTickets() {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "select * from helpdesk_app.app_tickets";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Ticket> tickets = new ArrayList<Ticket>();

            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setTicketId(rs.getInt("id"));
                ticket.setStatusId(rs.getInt("status_id"));
                ticket.setUserId(rs.getInt("user_id"));
                ticket.setTypeId(rs.getInt("type_id"));
                ticket.setDescription(rs.getString("description"));
                ticket.setSubmittedOn(rs.getTimestamp("submitted_on"));
                ticket.setOpenDateTime(rs.getInt("open_date_time"));
                ticket.setCloseDate((rs.getInt("close_date_time")));
                ticket.setExpectedWorkHours(rs.getInt("expected_work_hours"));
                ticket.setAssignedTo(rs.getInt("assigned_to"));
                tickets.add(ticket);
            }
            return tickets;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {

        try(Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "update helpdesk_app.app_tickets set status_id = ?, user_id = ?, type_id = ?, description = ?, submitted_on = ?,  open_date_time = ?, close_date_time = ?, expected_work_hours = ?, assigned_to = ? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ticket.getStatusId());
            ps.setInt(2, ticket.getUserId());
            ps.setInt(3, ticket.getTypeId());
            ps.setString(4, ticket.getDescription());
            ps.setTimestamp(5, ticket.getSubmittedOn());
            ps.setInt(6, ticket.getOpenDateTime());
            ps.setInt(7, ticket.getCloseDate());
            ps.setInt(8, ticket.getExpectedWorkHours());
            ps.setInt(9, ticket.getAssignedTo());
            ps.setInt(10, ticket.getTicketId());


            ps.execute();

            return ticket;

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteTicketByInt(int id) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            String sql = "delete from helpdesk_app.app_tickets where id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }
}
