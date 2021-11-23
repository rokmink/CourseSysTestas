package com.example.lab1.controllers;

import com.example.lab1.Start;
import com.example.lab1.dataStructures.Person;
import com.example.lab1.dataStructures.User;
import com.example.lab1.hibernateControllers.UserHibController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditUserView {
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
    public TreeView treeUser;
    @FXML
    public TextField pswF;
    @FXML
    public Button regButton;
    @FXML
    public TextField loginData;
    private String idTest;
    private UserHibController userHibControl;


    public String getIdTest() {
        return idTest;
    }
    public void setIdTest(String name) {
        this.idTest = name;
    }
    public void editUser(ActionEvent actionEvent) {

    }

    public void deleteUser(ActionEvent actionEvent) {
    }

    public void returnToPrevious(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("User-view.fxml"));
        Parent root=fxmlLoader.load();
        Scene scene = new Scene(root);

        Stage stage = (Stage) loginData.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    public void loadUserToForms(User selectedUser) {
        /*User selectedUser;
        selectedUser= userHibControl.getUserById(id);*/
loginData.setText(selectedUser.getLogin());
pswF.setText(selectedUser.getPassword());
/*personNameF.setText(selectedUser.getName());
personEmailF.setText(selectedUser.getEmail());
personSurnameF.setText(selectedUser.getSurname());*/

    }
    public void populateData(MouseEvent mouseEvent) {
        String courseId = treeUser.getSelectionModel().getSelectedItem().toString().split(":")[0];
        User employee = UserHibController.getUserById(Integer.parseInt(courseId));
        // User employee = UserHibController.getUserById(1);

        personNameF.setText(employee.getLogin());

    }


}
