package com.example.lab1.webControllers;

import com.example.lab1.dataStructures.File;
import com.example.lab1.dataStructures.Person;
import com.example.lab1.dataStructures.User;
import com.example.lab1.hibernateControllers.FileHibController;
import com.example.lab1.hibernateControllers.UserHibController;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Properties;

@Controller
public class FileWebController {
    EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("ProjectCourseSys");
    FileHibController fileHibController=new FileHibController(entityManagerFactory);

    @RequestMapping(value = "/file/allFiles",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getAllFiles(){
        return fileHibController.getAllFiles(true, -1, -1).toString();
    }

    @RequestMapping(value = "/file/updateFile/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updateFile(@RequestBody String request, @PathVariable(name = "id") int id) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);

        File file = fileHibController.getFileById(id);



        fileHibController.editFile(file);
        return "Success";
    }
    @RequestMapping(value = "/file/addFile", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String addNewFile(@RequestBody String request) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        File file= new File();//Testui
        fileHibController.createFile(file);

        return "Success new file added";
    }

    @RequestMapping(value = "/file/deleteFile/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String deleteUser(@PathVariable(name = "id") int id) {
        fileHibController.removeFile(id);
        return "Success";
    }
}
