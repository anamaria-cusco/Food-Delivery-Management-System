package DataLayer;

import BusinessLayer.MenuItem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class User implements  Serializable{
    private static final long serialVersionUID = -4180180019764033466L;

    private int id;
    private String username;
    private String password;
    private final UserRole userRole;

    public User(int id,String username, String password, UserRole userRole) {
        this.id=id;
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }

    public User(int id,String username, String password) {
        this.id=id;
        this.username = username;
        this.password = password;
        this.userRole = UserRole.CLIENT;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getUserRole() {
        return userRole;
    }



    @Override
    public String toString() {
        return "User:\n" +
                "id:" + id + "\n"+
                "username:" + username + "\n\n";

    }
}
