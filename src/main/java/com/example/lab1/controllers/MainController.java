package com.example.lab1.controllers;

import com.example.lab1.Start;
import com.example.lab1.dataStructures.CourseSystem;
import com.example.lab1.dataStructures.User;
import com.example.lab1.hibernateControllers.UserHibController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class MainController  {
    public PasswordField pswF;
    @FXML
public TextField loginData;
private CourseSystem courseSystem;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private PreparedStatement preparedStatements;



    @FXML void onRegButtonClick(ActionEvent event) throws IOException{

    FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("Registration-view.fxml"));
    Parent root = fxmlLoader.load();
    RegistrationController registrationController = fxmlLoader.getController();
    registrationController.setCourseSystem(courseSystem);

    Scene scene = new Scene(root);
    Stage stage = (Stage) loginData.getScene().getWindow();
    stage.setScene(scene);
    stage.show();
 }

    @FXML
    protected void onLoginButtonClick(ActionEvent event) throws IOException, SQLException {
        connection = DbUtills.connectToDb();
        String sql = "SELECT count(*) FROM user AS u WHERE u.login = ? AND u.password = ?";
        String userData = "SELECT id FROM user AS o WHERE o.login= ? and o.password=?";
        preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, loginData.getText());
        preparedStatement.setString(2, pswF.getText());
        ResultSet rs = preparedStatement.executeQuery();

        preparedStatements = connection.prepareStatement(userData);
        preparedStatements.setString(1, loginData.getText());
        preparedStatements.setString(2, pswF.getText());
        ResultSet rss = preparedStatements.executeQuery();
        while (rs.next()) {
            if (rs.getInt(1) > 0) {
                FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("User-view.fxml"));
                Parent root = fxmlLoader.load();
                rss.next();
                int val = ((Number) rss.getObject(1)).intValue();
                UserViewController userViewController = fxmlLoader.getController();

                Scene scene = new Scene(root);
                userViewController.initData(val);
                Stage stage = (Stage) loginData.getScene().getWindow();

                stage.setTitle("Project Course System");
                stage.setScene(scene);

            } else {
                alertMessage("Neteisingas slapyvardis arba slaptažodis");
            }


        }
    }

   public void alertMessage (String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacija");
        alert.setHeaderText("Pranešimas:");
        alert.setContentText(message);
        alert.initModality(Modality.APPLICATION_MODAL);

        alert.showAndWait();
    }
}



