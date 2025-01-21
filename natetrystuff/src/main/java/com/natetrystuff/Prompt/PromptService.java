package com.natetrystuff.Prompt;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class PromptService {
    private final PromptRepository promptRepository;

    public PromptService(PromptRepository promptRepository) {
        this.promptRepository = promptRepository;
    }

    public Prompt getLatestPrompt() {
        return promptRepository.findTopByOrderByCreationDateDesc();
    }

    public Prompt addPrompt(String promptText) {
        Prompt prompt = new Prompt();
        prompt.setPromptText(promptText);
        prompt.setCreationDate(LocalDateTime.now());
        return promptRepository.save(prompt);
    }
}