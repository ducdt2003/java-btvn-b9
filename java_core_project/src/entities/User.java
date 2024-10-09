package entities;

import java.util.ArrayList;

public class User {
    public static int autoId = 0;
    private int id;
    private String username;
    private String email;
    private String password;
    private String role;
    private ArrayList<String> notifications;
    public User() {
    }

    public User(String username, String email, String password, String role) {
        this.id = ++autoId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.notifications = new ArrayList<>();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<String> getNotifications() {
        return notifications;
    }

    public void setNotifications(ArrayList<String> notifications) {
        this.notifications = notifications;
    }

    public void displayUser() {
        System.out.println("Infomation:");
        System.out.println("---------------------");
        System.out.println("Name        : " + username);
        System.out.println("Email       : " + email);
        System.out.println("Role          : " + role);
        System.out.println("---------------------");
    }
}
