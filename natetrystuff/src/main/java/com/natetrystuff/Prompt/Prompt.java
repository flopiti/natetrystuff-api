package com.natetrystuff.Prompt;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class Prompt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long promptId;

    @Lob
    @Column(nullable = false)
    private String promptText;

    @Column(nullable = false)
    private LocalDateTime creationDate;
}