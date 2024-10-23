package com.natetrystuff.Process;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessService {

    private final ProcessRepository processRepository;

    @Autowired
    public ProcessService(ProcessRepository processRepository) {
        this.processRepository = processRepository;
    }

    public List<Process> listAllProcesses() {
        return processRepository.findAll();
    }

    public Process getProcessById(Long id) {
        return processRepository.findById(id).orElse(null);
    }

    public Process createProcess(Process process) {
        return processRepository.save(process);
    }

    public Process updateProcess(Long id, Process processDetails) {
        Process existingProcess = processRepository.findById(id).orElse(null);
        if (existingProcess != null) {
            existingProcess.setProcess(processDetails.getProcess());
            existingProcess.setConversation(processDetails.getConversation());
            existingProcess.setSuccess(processDetails.isSuccess());
            return processRepository.save(existingProcess);
        }
        throw new RuntimeException("Process not found with id " + id);
    }

    public void deleteProcess(Long id) {
        processRepository.deleteById(id);
    }
}