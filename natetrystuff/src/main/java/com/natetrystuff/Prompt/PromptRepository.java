package com.natetrystuff.Prompt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromptRepository extends JpaRepository<Prompt, Long> {
    Prompt findTopByOrderByCreationDateDesc();
}
