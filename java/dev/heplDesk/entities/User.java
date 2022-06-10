package dev.heplDesk.entities;

public class User {

    private int userId;
    private int departmentId;
    private String lastName;
    private String firstName;
    private int phone;
    private String email;
    private String userName;
    private String password;
    private int reportsTo;

    public User(){

    }


    public User(int userId, int departmentId, String lastName, String firstName, int phone, String email, String userName, String password, int reportsTo){
        this.userId = userId;
        this.departmentId = departmentId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.phone = phone;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.reportsTo = reportsTo;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(int reportsTo) {
        this.reportsTo = reportsTo;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", departmentId=" + departmentId +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", reportsTo=" + reportsTo +
                '}';
    }
}
