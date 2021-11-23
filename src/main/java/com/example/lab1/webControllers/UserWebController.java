package com.example.lab1.webControllers;

import com.example.lab1.dataStructures.Course;
import com.example.lab1.dataStructures.Person;
import com.example.lab1.dataStructures.User;
import com.example.lab1.hibernateControllers.UserHibController;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Properties;

@Controller
public class UserWebController {
EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("ProjectCourseSys");
UserHibController userHibController=new UserHibController(entityManagerFactory);

@RequestMapping(value = "/user/allUsers",method = RequestMethod.GET)
@ResponseStatus (value = HttpStatus.OK)
@ResponseBody
    public String getAllUsers(){
        return userHibController.getAllUsers(true, -1, -1).toString();
    }

    @RequestMapping(value = "/user/updateUser/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updatePerson(@RequestBody String request, @PathVariable(name = "id") int id) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);



        //course.setCourseName(properties.getProperty("cName"));
        //cia redaguot reik viska ir tada updatinti

        Person person = new Person(properties.getProperty("login"), properties.getProperty("psw"), properties.getProperty("name"), properties.getProperty("surname"), properties.getProperty("email"));
        userHibController.editUser(person);// atkeist i user
        return "Success";
    }
    @RequestMapping(value = "/user/addUser", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String addNewUser(@RequestBody String request) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        Person person = new Person("loginTest","pass123","Algis","Dalgis","email");//Testui
        userHibController.createUser(person);

        return "Success new user added";
    }

    @RequestMapping(value = "/user/deleteUser/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String deleteUser(@PathVariable(name = "id") int id) {
        userHibController.removeUser(id);
        return "Success user deleted";
    }
}
