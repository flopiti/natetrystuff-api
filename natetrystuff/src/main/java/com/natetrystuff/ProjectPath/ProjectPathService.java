package com.natetrystuff.ProjectPath;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProjectPathService {

    private final ProjectPathRepository projectPathRepository;

    public ProjectPathService(ProjectPathRepository projectPathRepository) {
        this.projectPathRepository = projectPathRepository;
    }

    public List<ProjectPath> listAllProjectPaths() {
        return projectPathRepository.findAll();
    }

    public ProjectPath createProjectPath(ProjectPath projectPath) {
        return projectPathRepository.save(projectPath);
    }



    public void deleteProjectPath(String path) {
        ProjectPath projectPath = projectPathRepository.findByPath(path);
        projectPathRepository.deleteById(projectPath.getId());
    }

}
