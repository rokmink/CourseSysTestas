package com.example.lab1.dataStructures;

import java.io.Serializable;
import java.util.ArrayList;

public class CourseSystem implements Serializable {
private int id;
    private ArrayList<User> allUsers;

    public CourseSystem(ArrayList<User> allUsers) {
        this.allUsers = allUsers;
    }

    public CourseSystem() {
    }

    public ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(ArrayList<User> allUsers) {
        this.allUsers = allUsers;
    }


}
