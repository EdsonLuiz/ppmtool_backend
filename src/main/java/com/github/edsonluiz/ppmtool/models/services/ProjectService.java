package com.github.edsonluiz.ppmtool.models.services;

import com.github.edsonluiz.ppmtool.exceptions.ProjectIdException;
import com.github.edsonluiz.ppmtool.models.entities.Project;
import com.github.edsonluiz.ppmtool.models.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);

        } catch (Exception e) {
            throw new ProjectIdException("Project id '"+project.getProjectIdentifier()+"' already exists.");
        }

    }
}
