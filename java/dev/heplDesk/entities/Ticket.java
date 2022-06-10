package dev.heplDesk.entities;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ticket {
    private int ticketId;
    private int statusId;
    private int userId;
    private int typeId;
    private String description;
    private Timestamp submittedOn;
    private int openDateTime;
    private int closeDate;
    private int expectedWorkHours;
    private int assignedTo;

    public Ticket() {

    }

    public Ticket(int ticketId, int statusId, int userId, int typeId, String description, Timestamp submittedOn, int openDateTime, int closeDate, int expectedWorkHours, int assignedTo) {
        this.ticketId = ticketId;
        this.statusId = statusId;
        this.userId = userId;
        this.typeId = typeId;
        this.description = description;
        this.submittedOn = submittedOn;
        this.openDateTime = openDateTime;
        this.closeDate = closeDate;
        this.expectedWorkHours = expectedWorkHours;
        this.assignedTo = assignedTo;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getSubmittedOn() {
        return submittedOn;
    }

    public void setSubmittedOn(Timestamp submittedOn) {
        this.submittedOn = submittedOn;
    }

    public int getOpenDateTime() {
        return openDateTime;
    }

    public void setOpenDateTime(int openDateTime) {
        this.openDateTime = openDateTime;
    }

    public int getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(int closeDate) {
        this.closeDate = closeDate;
    }

    public int getExpectedWorkHours() {
        return expectedWorkHours;
    }

    public void setExpectedWorkHours(int expectedWorkHours) {
        this.expectedWorkHours = expectedWorkHours;
    }

    public int getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(int assignedTo) {
        this.assignedTo = assignedTo;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", statusId=" + statusId +
                ", userId=" + userId +
                ", typeId=" + typeId +
                ", description='" + description + '\'' +
                ", submittedOn=" + submittedOn +
                ", openDateTime=" + openDateTime +
                ", closeDate=" + closeDate +
                ", expectedWorkHours=" + expectedWorkHours +
                ", assignedTo=" + assignedTo +
                '}';
    }
}
