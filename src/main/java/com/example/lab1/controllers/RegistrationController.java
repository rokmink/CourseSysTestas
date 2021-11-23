package com.example.lab1.controllers;


import com.example.lab1.Start;
import com.example.lab1.dataStructures.*;
import com.example.lab1.hibernateControllers.UserHibController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.io.Serializable;


public class RegistrationController implements Serializable {
    @FXML
    public TextField personNameF;
    @FXML
    public TextField companyNameF;
    @FXML
    public TextField personSurnameF;
    @FXML
    public TextField personEmailF;
    @FXML
    public TextField companyRepF;
    @FXML
    public TextField companyNumF;
    @FXML
    public RadioButton btCompany;
    @FXML
    public RadioButton btPerson;
    @FXML
    public ToggleGroup tgType;

    @FXML
    public TextField pswF;
    @FXML
    public Button regButton;
    @FXML
    public TextField loginData;


    private CourseSystem courseSystem;
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ProjectCourseSys");
    UserHibController userHibController = new UserHibController(entityManagerFactory);
    public void setCourseSystem(CourseSystem courseSystem) {
        this.courseSystem = courseSystem;
    }
    public void createUser(ActionEvent actionEvent) throws IOException {

        if(btCompany.isSelected()  ){

            Company company=new Company(loginData.getText(), pswF.getText(),companyNameF.getText(),companyRepF.getText(), companyNameF.getText() );
            courseSystem.getAllUsers().add(company);
            userHibController.createUser(company);

        }

        else{

            Person person= new Person(loginData.getText(), pswF.getText(),personNameF.getText(),personSurnameF.getText(),personEmailF.getText());
            courseSystem.getAllUsers().add(person);
            userHibController.createUser(person);
        }
        returnToPrevious();
    }
    public void checkBoxes(ActionEvent event){
        if(btCompany.isSelected()){
            personNameF.setDisable(true);
            personEmailF.setDisable(true);
            personSurnameF.setDisable(true);
            companyNameF.setDisable(false);
            companyNumF.setDisable(false);
            companyRepF.setDisable(false);
        }
        if(btPerson.isSelected()){
            personNameF.setDisable(false);
            personEmailF.setDisable(false);
            personSurnameF.setDisable(false);
            companyNameF.setDisable(true);
            companyNumF.setDisable(true);
            companyRepF.setDisable(true);

        }
    }
    public void returnToPrevious() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("Main-view.fxml"));
        Parent root=fxmlLoader.load();
        Scene scene = new Scene(root);

        Stage stage = (Stage) loginData.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }



}
