package com.natetrystuff.Prompt;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prompts")
public class PromptController {
    private final PromptService promptService;

    public PromptController(PromptService promptService) {
        this.promptService = promptService;
    }

    @GetMapping("/latest")
    public Prompt getLatestPrompt() {
        return promptService.getLatestPrompt();
    }

    @PostMapping
    public ResponseEntity<Prompt> addPrompt(@RequestBody String promptText) {
        Prompt newPrompt = promptService.addPrompt(promptText);
        return new ResponseEntity<>(newPrompt, HttpStatus.CREATED);
    }
}
