package com.things.scheduler.infrastructure.repository;

import com.things.scheduler.infrastructure.entity.TaskEntity;
import com.things.scheduler.infrastructure.enums.StatusNotification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository  extends MongoRepository<TaskEntity,String> {

    List<TaskEntity> findByStatus(StatusNotification status);
    Page<TaskEntity> findAllByDateExecutedBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);
    Page<TaskEntity> findTaskEntitiesByEmail(String email, Pageable pageable);
}
