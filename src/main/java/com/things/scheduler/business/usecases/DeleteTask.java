package com.things.scheduler.business.usecases;


import com.things.scheduler.infrastructure.expection.ResourceNotFoundException;
import com.things.scheduler.infrastructure.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteTask {

    private final TaskRepository taskRepository;

    public void delete(String taskId) {
        try{
            var email = SecurityContextHolder.getContext().getAuthentication().getName();
            taskRepository.deleteByIdAndEmail(taskId,  email);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(
                    "Resource not found with name " + taskId, e.getCause()
            );
        }
    }
}
