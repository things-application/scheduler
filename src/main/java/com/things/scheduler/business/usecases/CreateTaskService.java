package com.things.scheduler.business.usecases;

import com.things.scheduler.business.dto.TaskRequest;
import com.things.scheduler.business.dto.TaskResponse;
import com.things.scheduler.business.mapper.TaskMapper;
import com.things.scheduler.domain.entity.TaskEntity;
import com.things.scheduler.domain.enums.StatusNotification;
import com.things.scheduler.infrastructure.repository.TaskRepository;
import com.things.scheduler.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateTaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final JwtUtil jwtUtil;

    public TaskResponse create(String token, TaskRequest  taskRequest) {

        String email = jwtUtil.extrairEmailToken(token.substring(7));

        taskRequest.setCreatedAt(LocalDateTime.now());
        taskRequest.setUpdatedAt(LocalDateTime.now());
        taskRequest.setEmail(email);
        taskRequest.setStatus(StatusNotification.PENDING);

        TaskEntity taskEntity = taskRepository.save(
                taskMapper.taskRequestToEntity(taskRequest)
        );

        return taskMapper.taskEntityToResponse(taskEntity);
    }

}
