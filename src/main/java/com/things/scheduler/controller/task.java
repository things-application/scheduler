package com.things.scheduler.controller;


import com.things.scheduler.business.CreateTaskService;
import com.things.scheduler.business.dto.TaskRequest;
import com.things.scheduler.business.dto.TaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class task {

    private final CreateTaskService createTaskService;

    @PostMapping
    public ResponseEntity<TaskResponse> createTask( @RequestHeader("Authorization") String token, @RequestBody TaskRequest taskRequest) {
        return ResponseEntity.ok(
                createTaskService.execute(token, taskRequest)
        );
    }

}
