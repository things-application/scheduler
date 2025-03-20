package com.things.scheduler.business.mapper;


import com.things.scheduler.business.dto.TaskRequest;
import com.things.scheduler.business.dto.TaskResponse;
import com.things.scheduler.infrastructure.entity.TaskEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {


    TaskEntity taskRequestToEntity(TaskRequest taskRequest);
    TaskResponse taskEntityToResponse(TaskEntity taskEntity);
}
