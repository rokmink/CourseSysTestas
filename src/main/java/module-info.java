module com.example.lab1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.java;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.persistence;
requires spring.web;
requires spring.context;
requires spring.core;
requires com.google.gson;


    opens com.example.lab1 to javafx.fxml;
    exports com.example.lab1;

    exports com.example.lab1.dataStructures;
    opens com.example.lab1.dataStructures to javafx.fxml, org.hibernate.orm.core, java.persistence;
    exports com.example.lab1.controllers;
    opens com.example.lab1.controllers to javafx.fxml;
    //exports com.example.lab1.dataStructures;
}