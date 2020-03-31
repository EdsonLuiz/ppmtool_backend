package com.github.edsonluiz.ppmtool.models.repositories;

import com.github.edsonluiz.ppmtool.models.entities.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
}
