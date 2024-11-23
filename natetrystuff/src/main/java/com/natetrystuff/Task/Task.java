package com.natetrystuff.Task;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.natetrystuff.Objective.Objective;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Data
@Entity
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    @Column
    private String description;

    @Column
    private boolean isFinished;

    @ManyToOne
    @JsonBackReference // Add this annotation
    @JoinColumn(name = "objective_id")
    private Objective objective;
}