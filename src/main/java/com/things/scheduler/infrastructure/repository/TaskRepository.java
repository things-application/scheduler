package com.things.scheduler.infrastructure.repository;

import com.things.scheduler.infrastructure.entity.TaskEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository  extends MongoRepository<TaskEntity,String> {
}
