package com.example.lab1.webControllers;

import com.example.lab1.dataStructures.File;
import com.example.lab1.dataStructures.Folder;
import com.example.lab1.hibernateControllers.FileHibController;
import com.example.lab1.hibernateControllers.FolderHibController;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Properties;

@Controller
public class FolderWebController {
    EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("ProjectCourseSys");
    FolderHibController folderHibController=new FolderHibController(entityManagerFactory);

    @RequestMapping(value = "/folder/allFolders",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getAllFolders(){
        return folderHibController.getAllFolders(true, -1, -1).toString();
    }

    @RequestMapping(value = "/folder/updateFolder/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updateFolder(@RequestBody String request, @PathVariable(name = "id") int id) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);

        Folder folder = folderHibController.getFolderById(id);



        folderHibController.editFolder(folder);
        return "Success";
    }
    @RequestMapping(value = "/folder/addFolder", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String addNewFolder(@RequestBody String request) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        Folder folder= new Folder();//Testui
        folderHibController.createFolder(folder);

        return "Success new file added";
    }

    @RequestMapping(value = "/folder/deleteFolder/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String deleteFolder(@PathVariable(name = "id") int id) {
        folderHibController.removeFolder(id);
        return "Success";
    }
}
