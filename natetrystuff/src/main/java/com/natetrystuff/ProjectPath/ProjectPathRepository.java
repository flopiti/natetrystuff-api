package com.natetrystuff.ProjectPath;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectPathRepository extends JpaRepository<ProjectPath, Long> {
    ProjectPath findByPath(String path);
}
