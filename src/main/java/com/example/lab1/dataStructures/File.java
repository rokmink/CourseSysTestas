package com.example.lab1.dataStructures;

import javax.persistence.*;

@Entity
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String location;
    @ManyToOne
    private Folder folder;

    public File(int id, String name, String location, Folder folder) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.folder = folder;
    }
public File(String name, String location, Folder folder){
    this.name = name;
    this.location = location;
    this.folder = folder;
}
    public File() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }
}
