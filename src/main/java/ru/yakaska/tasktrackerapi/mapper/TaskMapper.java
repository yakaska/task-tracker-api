package ru.yakaska.tasktrackerapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import ru.yakaska.tasktrackerapi.model.Task;
import ru.yakaska.tasktrackerapi.model.TaskPriority;
import ru.yakaska.tasktrackerapi.model.TaskStatus;
import ru.yakaska.tasktrackerapi.payload.dto.TaskDto;
import ru.yakaska.tasktrackerapi.payload.request.CreateTaskRequest;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserMapper.class})
public interface TaskMapper {

    @Mapping(target = "author", source = "author", qualifiedByName = "emailToUser")
    @Mapping(target = "assignee", source = "assignee", qualifiedByName = "emailToUser")
    @Mapping(target = "status", source = "status", qualifiedByName = "mapStatus")
    @Mapping(target = "priority", source = "priority", qualifiedByName = "mapPriority")
    Task createTaskRequestToTask(CreateTaskRequest createTaskRequest);

    @Mapping(target = "author", source = "author.email")
    @Mapping(target = "assignee", source = "assignee.email")
    TaskDto taskToTaskDto(Task task);

    @Named("mapStatus")
    default TaskStatus mapStatus(String status) {
        return TaskStatus.valueOf(status);
    }

    @Named("mapPriority")
    default TaskPriority mapPriority(String priority) {
        return TaskPriority.valueOf(priority);
    }

}