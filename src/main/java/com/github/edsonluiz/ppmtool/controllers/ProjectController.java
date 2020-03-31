package com.github.edsonluiz.ppmtool.controllers;

import com.github.edsonluiz.ppmtool.models.entities.Project;
import com.github.edsonluiz.ppmtool.models.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PostMapping()
    public ResponseEntity<Project> store(@RequestBody Project project) {
        Project returnOfSaveOperation = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<>(returnOfSaveOperation, HttpStatus.CREATED);
    }
}
