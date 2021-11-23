package com.example.lab1.dataStructures;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
public abstract class User implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String password;
    private LocalDate dateCreated;
    private LocalDate dateModified;
    @Enumerated(EnumType.ORDINAL)
    private UserType userType;
    @ManyToMany
    private List<Course> myModeratedCourses;
    @ManyToMany List <Folder> myFolders;


    public User(String login, String password) {
        this.login = login;
        this.password = password;

    }

    public User() {
    }

    public User(int id, String login, String password, LocalDate dateCreated, LocalDate dateModified) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDate dateModified) {
        this.dateModified = dateModified;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}