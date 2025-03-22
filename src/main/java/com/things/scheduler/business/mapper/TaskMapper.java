package com.things.scheduler.business.mapper;


import com.things.scheduler.business.dto.TaskRequest;
import com.things.scheduler.business.dto.TaskResponse;
import com.things.scheduler.domain.entity.TaskEntity;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface TaskMapper {


    TaskEntity taskRequestToEntity(TaskRequest taskRequest);
    TaskResponse taskEntityToResponse(TaskEntity taskEntity);
    default Page<TaskResponse> taskEntityToResponsePage(Page<TaskEntity> taskEntityPage){
        return taskEntityPage.map(this::taskEntityToResponse);
    }

}
