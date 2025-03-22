package com.things.scheduler.business.usecases;

import com.things.scheduler.business.dto.TaskResponse;
import com.things.scheduler.business.mapper.TaskMapper;
import com.things.scheduler.domain.entity.TaskEntity;
import com.things.scheduler.domain.enums.StatusNotification;
import com.things.scheduler.infrastructure.expection.ResourceNotFoundException;
import com.things.scheduler.infrastructure.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateStatusTask {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskResponse updateStatus(StatusNotification statusNotification, String taskId) {
        try {
            var email = SecurityContextHolder.getContext().getAuthentication().getName();
            TaskEntity taskEntity = taskRepository.findByIdAndEmail(taskId, email).orElseThrow(() -> new ResourceNotFoundException(
                    "Task with id " + taskId + " not found"
            ));
            taskEntity.setStatus(statusNotification);
            return taskMapper
                    .taskEntityToResponse(
                            taskRepository.save(taskEntity)
                    );
        }  catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage(), e.getCause());
        }
    }
}
