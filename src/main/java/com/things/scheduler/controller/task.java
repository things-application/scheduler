package com.things.scheduler.controller;


import com.things.scheduler.business.CreateTaskService;
import com.things.scheduler.business.FindTask;
import com.things.scheduler.business.dto.TaskRequest;
import com.things.scheduler.business.dto.TaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class task {

    private final CreateTaskService createTaskService;
    private final FindTask findTask;

    @PostMapping
    public ResponseEntity<TaskResponse> createTask( @RequestHeader("Authorization") String token, @RequestBody TaskRequest taskRequest) {
        return ResponseEntity.ok(
                createTaskService.create(token, taskRequest)
        );
    }

    @GetMapping
    public ResponseEntity<Page<TaskResponse>> getTasksByEmail(
            @RequestHeader("Authorization") String token,
            Pageable pageable
    ){
        return ResponseEntity.ok(findTask.findAllTaskByEmail(token, pageable));
    }

    @GetMapping("/events")
    public ResponseEntity<Page<TaskResponse>> getEvents(@RequestParam LocalDateTime start,
                                                              @RequestParam LocalDateTime end,
                                                              Pageable pageable
                                                         ) {
//        ?page=1&size=5&sort=createdAt,asc
        return ResponseEntity.ok(
                findTask.findAllByDateExecutedBetween(start, end, pageable)
        );
    }

}
