package com.things.scheduler.business;

import com.things.scheduler.business.dto.TaskResponse;
import com.things.scheduler.business.mapper.TaskMapper;
import com.things.scheduler.infrastructure.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class FindTask {

    private final TaskRepository taskRepository;
    private final TaskMapper  taskMapper;


    public Page<TaskResponse> findAllByDateExecutedBetween(LocalDateTime start, LocalDateTime end, Pageable pageable ) {
        return taskMapper.taskEntityToResponsePage(
                taskRepository.findAllByDateExecutedBetween(start, end, pageable)
        );
    }

    public Page<TaskResponse> findAllTaskByEmail(String token, Pageable pageable) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        var result = taskRepository.findTaskEntitiesByEmail(email, pageable);
        return taskMapper.taskEntityToResponsePage(result);
    }
}
