package com.example.lab1.webControllers;

import com.example.lab1.dataStructures.Course;
import com.example.lab1.hibernateControllers.CourseHibController;
import com.example.lab1.hibernateControllers.UserHibController;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Properties;

@Controller
public class CourseWebController {
    EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("ProjectCourseSys");
    CourseHibController courseHibController=new CourseHibController(entityManagerFactory);

    @RequestMapping(value = "/course/allCourses", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getAllCourses() {
        Gson gson = new Gson();
        return gson.toJson(courseHibController.getAllCourses(true, -1, -1).toString());
    }

    @RequestMapping(value = "/course/updateCourse/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updateCourse(@RequestBody String request, @PathVariable(name = "id") int id) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);

        Course course = courseHibController.getCourseById(id);

        //pabaigsim

        courseHibController.editCourse(course);
        return "Success";
    }

    @RequestMapping(value = "/course/addCourse", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String addNewCourse(@RequestBody String request) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        Course course = new Course("pav","addasdasdasadsdasdasdas"); //Pabaigti
        courseHibController.createCourse(course);
        return "Success";
    }

    @RequestMapping(value = "/course/deleteCourse/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updateCourse(@PathVariable(name = "id") int id) {
        courseHibController.removeCourse(id);
        return "Success";
    }
}
