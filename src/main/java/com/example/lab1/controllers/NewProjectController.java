package com.example.lab1.controllers;

import com.example.lab1.Start;
import com.example.lab1.dataStructures.Course;
import com.example.lab1.hibernateControllers.UserHibController;
import com.example.lab1.hibernateControllers.CourseHibController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

public class NewProjectController {
    @FXML
    public TextField courseID;
    @FXML
    public TextField courseTitle;
    @FXML
    public TextArea courseDesc;
    public void createProject(ActionEvent actionEvent) throws IOException {
        DbUtills.connectToDb();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ProjectCourseSys");
        CourseHibController courseHibController = new CourseHibController(entityManagerFactory);
        Course course = new Course(courseTitle.getText(),courseTitle.getText());
        courseHibController.createCourse(course);

        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("User-view.fxml"));

        Parent root=fxmlLoader.load();
        Scene scene = new Scene(root);

        Stage stage = (Stage) courseDesc.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
