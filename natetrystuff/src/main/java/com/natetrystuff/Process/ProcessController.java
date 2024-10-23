package com.natetrystuff.Process;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/processes")
public class ProcessController {

    private final ProcessService processService;

    public ProcessController(ProcessService processService) {
        this.processService = processService;
    }

    @GetMapping
    public List<Process> getAllProcesses() {
        return processService.listAllProcesses();
    }

    @PostMapping
    public ResponseEntity<Process> addProcess(@RequestBody Process process) {
        Process newProcess = processService.createProcess(process);
        return new ResponseEntity<>(newProcess, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Process> getProcessById(@PathVariable Long id) {
        Process process = processService.getProcessById(id);
        if (process != null) {
            return new ResponseEntity<>(process, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Process> updateProcess(@PathVariable Long id, @RequestBody Process processDetails) {
        try {
            Process updatedProcess = processService.updateProcess(id, processDetails);
            return new ResponseEntity<>(updatedProcess, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProcess(@PathVariable Long id) {
        processService.deleteProcess(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
