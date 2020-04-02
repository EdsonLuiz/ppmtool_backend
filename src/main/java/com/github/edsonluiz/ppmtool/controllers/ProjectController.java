package com.github.edsonluiz.ppmtool.controllers;

import com.github.edsonluiz.ppmtool.models.entities.Project;
import com.github.edsonluiz.ppmtool.models.services.MapValidationErrorService;
import com.github.edsonluiz.ppmtool.models.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping()
    public ResponseEntity<?> store(@Valid @RequestBody Project project, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
        if (errorMap != null) return errorMap;
        Project returnOfSaveOperation = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<>(returnOfSaveOperation, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> show(@PathVariable String projectId) {
        Project project = projectService.findProjectByIdentifier(projectId);
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }


}
