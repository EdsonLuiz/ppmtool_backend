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
    	Project project = getProjectIfExists(projectId);
    	throwCustomExceptionWithMessage(project, "Project id does not exists");
    	
    	return project;
    }

	private Project getProjectIfExists(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase()); 
        
		return project;
	}

	private void throwCustomExceptionWithMessage(Project project, String message) {
		if(project == null) {
    		throw new ProjectIdException(message);
    	}
	}
    
    public Iterable<Project> findAllProjects() {
    	return projectRepository.findAll();
    }
    
    public void deleteProjectByIdentifier(String projectId) {
    	Project project = getProjectIfExists(projectId);
    	throwCustomExceptionWithMessage(project, "Can not delete project with this informations");
    	
    	projectRepository.delete(project);
    }
}















