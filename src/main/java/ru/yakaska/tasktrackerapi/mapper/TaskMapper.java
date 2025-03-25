package ru.yakaska.tasktrackerapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.yakaska.tasktrackerapi.model.Task;
import ru.yakaska.tasktrackerapi.payload.dto.TaskDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TaskMapper {

    //    @Mapping(source = "status", target = "status")
//    @Mapping(source = "priority", target = "priority")
//    @Mapping(source = "author.email", target = "authorEmail")
//    @Mapping(source = "assignee.email", target = "assigneeEmail")
    TaskDto taskToTaskDto(Task task);

}
