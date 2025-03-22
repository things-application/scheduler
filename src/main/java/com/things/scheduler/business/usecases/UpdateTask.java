package com.things.scheduler.business.usecases;

import com.things.scheduler.business.dto.TaskRequest;
import com.things.scheduler.business.dto.TaskResponse;
import com.things.scheduler.business.mapper.TaskMapper;
import com.things.scheduler.business.mapper.TaskUpdateMapper;
import com.things.scheduler.domain.entity.TaskEntity;
import com.things.scheduler.infrastructure.expection.ResourceNotFoundException;
import com.things.scheduler.infrastructure.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.MappingTarget;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateTask {

    private final TaskRepository taskRepository;
    private final TaskUpdateMapper  taskUpdateMapper;
    private final TaskMapper taskMapper;

    public TaskResponse update(TaskRequest taskRequest, String taskId) {
        try {

            var email = SecurityContextHolder.getContext().getAuthentication().getName();
            TaskEntity taskEntity = taskRepository.findByIdAndEmail(taskId, email).orElseThrow(() -> new ResourceNotFoundException(
                    "Task with id " + taskId + " not found"
            ));

            taskUpdateMapper.updateTask(taskRequest, taskEntity);
            return taskMapper
                    .taskEntityToResponse(
                            taskRepository.save(taskEntity)
                    );

        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage(), e.getCause());
        }
    }
}
