package com.github.edsonluiz.ppmtool.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.edsonluiz.ppmtool.exceptions.ProjectIdException;
import com.github.edsonluiz.ppmtool.models.entities.Project;
import com.github.edsonluiz.ppmtool.models.repositories.ProjectRepository;

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

    public Project findProjectByIdentifier(String projectId) {
    	Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase()); 
        
    	if(project == null) {
    		throw new ProjectIdException("Project id does not exists");
    	}
    	
    	return project;
    }
}
