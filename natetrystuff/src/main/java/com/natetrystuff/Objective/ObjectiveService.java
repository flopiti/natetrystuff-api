package com.natetrystuff.Objective;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ObjectiveService {

    private final ObjectiveRepository objectiveRepository;

    @Autowired
    public ObjectiveService(ObjectiveRepository objectiveRepository) {
        this.objectiveRepository = objectiveRepository;
    }

    public Objective createObjective(Objective objective) {
        return objectiveRepository.save(objective);
    }

    public Optional<Objective> getObjectiveWithLowestId() {
        return objectiveRepository.findAll().stream()
                .filter(objective -> !objective.isFinished())
                .min(Comparator.comparingLong(Objective::getObjectiveId));
    }

    public boolean completeObjective(Long objectiveId) {
        Optional<Objective> objective = objectiveRepository.findById(objectiveId);
        if (objective.isPresent()) {
            Objective obj = objective.get();
            obj.setFinished(true);
            obj.setFinishedState("COMPLETED");
            objectiveRepository.save(obj);
            return true;
        }
        return false;
    }

    public void deleteObjective(Long objectiveId) {
        objectiveRepository.deleteById(objectiveId);
    }
}