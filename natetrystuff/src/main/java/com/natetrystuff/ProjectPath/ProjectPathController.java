package com.natetrystuff.ProjectPath;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project-paths")
public class ProjectPathController {

    ProjectPathService projectPathService;

    public ProjectPathController(ProjectPathService projectPathService) {
        this.projectPathService = projectPathService;
    }

    @GetMapping
    public List<ProjectPath> getAllProjectPaths() {
        return projectPathService.listAllProjectPaths();
    }

    @PostMapping
    public ResponseEntity<ProjectPath> addProjectPath(@RequestBody ProjectPath projectPath) {
        ProjectPath newProjectPath = projectPathService.createProjectPath(projectPath);
        return new ResponseEntity<>(newProjectPath, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProjectPath(@PathVariable Long id) {
        try {
            projectPathService.deleteProjectPath(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
