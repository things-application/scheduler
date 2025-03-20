package com.things.scheduler.business.dto;


import com.things.scheduler.infrastructure.enums.StatusNotification;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskResponse {
    private String id;
    private String taskName;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime dateExecuted;
    private StatusNotification status;
}
