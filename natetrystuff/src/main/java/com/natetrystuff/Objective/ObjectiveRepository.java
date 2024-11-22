package com.natetrystuff.Objective;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjectiveRepository extends JpaRepository<Objective, Long> {
    @Query(value = "SELECT o FROM Objective o ORDER BY o.objectiveId ASC LIMIT 1")
    Objective findTopByOrderByObjectiveIdAsc();
}