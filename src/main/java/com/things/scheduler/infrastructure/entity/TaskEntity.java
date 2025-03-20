package com.things.scheduler.infrastructure.entity;

import com.things.scheduler.infrastructure.enums.StatusNotification;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("tasks")
public class TaskEntity {
    @Id
    private String id;
    private String taskName;
    private String description;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime dateExecuted;
    private StatusNotification status;
}
