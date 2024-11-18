package com.example.lab5exercisetrackersystem.Controller;

import com.example.lab5exercisetrackersystem.ApiResponse.ApiResponse;
import com.example.lab5exercisetrackersystem.Model.Project;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/project")
public class ProjectController {
    ArrayList<Project> projects = new ArrayList<>();

    @GetMapping("/display")
    public ResponseEntity displayProjects() {
        return ResponseEntity.status(200).body(projects);
    }

    @PostMapping("/add")
    public ResponseEntity addProject(@RequestBody @Valid Project project, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(200).body(message);
        }
        projects.add(project);
        return ResponseEntity.status(200).body(new ApiResponse("Project Added Successfully"));
    }

    @PutMapping("/update/{index}")
    public ResponseEntity updateProject(@PathVariable int index, @RequestBody @Valid Project project, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        projects.set(index, project);
        return ResponseEntity.status(200).body(new ApiResponse("Project Updated Successfully"));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteProject(@PathVariable int index) {
        if (index >= projects.size())
            return ResponseEntity.status(400).body(new ApiResponse("Incorrect Project Index"));

        projects.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("Project Deleted Successfully"));
    }

    @PutMapping("/change/status")
    public ResponseEntity changeStatus(){
        for(Project project : projects){
            if(project.getStatus().equalsIgnoreCase("not started")){
                project.setStatus("in progress");
            }

            else if(project.getStatus().equalsIgnoreCase("in progress")){
                project.setStatus("completed");
            }
        }
        return ResponseEntity.status(200).body(new ApiResponse("Projects Status Changed Successfully"));
    }

    @GetMapping("/search/{title}")
    public ResponseEntity getProjectByTitle(@PathVariable String title){
        for(Project project : projects){
            if(project.getTitle().equalsIgnoreCase(title))
                return ResponseEntity.status(200).body(new ApiResponse(project.getTitle() + " Project Is Found"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Project Not Found"));
    }

    @GetMapping("/same/company/{companyName}")
    public ResponseEntity displaySameProjectCompany(@PathVariable String companyName){
        ArrayList<Project> sameCompanyProjects = new ArrayList<>();
        for(Project project : projects){
            if(project.getCompanyName().equalsIgnoreCase(companyName)){
                sameCompanyProjects.add(project);
            }
        }
        return ResponseEntity.status(200).body(sameCompanyProjects);
    }
}
