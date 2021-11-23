package com.example.lab1.controllers;

import com.example.lab1.Start;
import com.example.lab1.dataStructures.Course;
import com.example.lab1.dataStructures.Person;
import com.example.lab1.dataStructures.User;
import com.example.lab1.hibernateControllers.CourseHibController;
import com.example.lab1.hibernateControllers.UserHibController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;

public class UserViewController implements Initializable {
    public TextField ddd;
    private Stage primaryStage;
    public ListView listView;
    private String login;
    @FXML
    private TableView<Person> user_table;

    @FXML
    private TableColumn<User, String> userName;

    @FXML
    private TableColumn<User, String> userLastname;
    @FXML
    private int prId;

    @FXML private TableColumn<Person, String> firstNameColumn;
    @FXML private TableColumn<Person, String> lastNameColumn;



    public ObservableList<User> data;

    public void setId(int id){
        this.prId = id;
    }
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ProjectCourseSys");
    CourseHibController courseHibControl = new CourseHibController(entityManagerFactory);
    UserHibController userHibControl = new UserHibController(entityManagerFactory);
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.getItems().clear();
user_table.getItems().clear();
        /*firstNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));*/
        List<Course> courses = courseHibControl.getAllCourses(true, -1, -1);
        courses.forEach(c -> listView.getItems().add(c.getId() + ":" + c.getDescription()));

        /*ObservableList<Person> people = userHibControl.getAllUsers(true,-1,-1);
        user_table.setItems(people);
        firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());*/
    }


    public void logoff(ActionEvent event)throws IOException {
           /* Parent reg_scene_parent= FXMLLoader.load(getClass().getResource("Main-view.fxml"));
            Scene reg_scene=new Scene(reg_scene_parent);
            Stage app_stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(reg_scene);
            app_stage.show();// neveikia dar*/

        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("Main-view.fxml"));
        Parent root=fxmlLoader.load();
        Scene scene = new Scene(root);

        Stage stage = (Stage) user_table.getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
    public void newProjectForm(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("New-project-form.fxml"));
        Parent root=fxmlLoader.load();
        Scene scene = new Scene(root);

        Stage stage = (Stage) user_table.getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }



    public void editUserForm(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("Edit-user-view.fxml"));
        Parent root=fxmlLoader.load();
        Scene scene = new Scene(root);

        Stage stage = (Stage) user_table.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void editCourse(ActionEvent actionEvent) {

    }
    public void initData(int id) throws IOException {
User selectedUser;
selectedUser=userHibControl.getUserById(id);
ddd.setText(selectedUser.getLogin());
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("Edit-user-view.fxml"));
        Parent root = fxmlLoader.load();
        EditUserView editUserView=fxmlLoader.getController();
        Scene scene = new Scene(root);
        editUserView.loadUserToForms(selectedUser);
        Stage stage = (Stage)user_table.getScene().getWindow();



    }
}
