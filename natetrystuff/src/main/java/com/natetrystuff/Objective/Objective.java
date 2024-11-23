package com.natetrystuff.Objective;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.natetrystuff.Task.Task;

import java.util.ArrayList;

@Data
@Entity
@NoArgsConstructor
public class Objective {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long objectiveId;

    @Column
    private String finishedState;

    @Column
    private boolean finished;

    @OneToMany(mappedBy = "objective")
    @JsonManagedReference
    private List<Task> tasks = new ArrayList<>();
}