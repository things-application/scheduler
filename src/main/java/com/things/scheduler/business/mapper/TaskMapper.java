package com.things.scheduler.business.mapper;


import com.things.scheduler.business.dto.TaskRequest;
import com.things.scheduler.business.dto.TaskResponse;
import com.things.scheduler.infrastructure.entity.TaskEntity;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {


    TaskEntity taskRequestToEntity(TaskRequest taskRequest);
    TaskResponse taskEntityToResponse(TaskEntity taskEntity);
    default Page<TaskResponse> taskEntityToResponsePage(Page<TaskEntity> taskEntityPage){
        return taskEntityPage.map(this::taskEntityToResponse);
    }

}
