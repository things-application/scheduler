package com.things.scheduler.business.mapper;


import com.things.scheduler.business.dto.TaskRequest;
import com.things.scheduler.domain.entity.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TaskUpdateMapper {

    void updateTask(TaskRequest taskRequest, @MappingTarget TaskEntity taskEntity);
}
