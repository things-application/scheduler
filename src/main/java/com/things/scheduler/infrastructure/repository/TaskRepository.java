package com.things.scheduler.infrastructure.repository;

import com.things.scheduler.domain.entity.TaskEntity;
import com.things.scheduler.infrastructure.expection.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface TaskRepository  extends MongoRepository<TaskEntity,String> {

    Page<TaskEntity> findAllByDateExecutedBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);
    Page<TaskEntity> findTaskEntitiesByEmail(String email, Pageable pageable);
    void deleteByIdAndEmail(String id, String email);
    Optional<TaskEntity> findByIdAndEmail(String id, String email);
}
