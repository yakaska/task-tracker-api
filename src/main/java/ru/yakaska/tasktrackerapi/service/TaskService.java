package ru.yakaska.tasktrackerapi.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.yakaska.tasktrackerapi.model.Task;
import ru.yakaska.tasktrackerapi.repository.TaskRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> find(Specification<Task> specification, Pageable pageable) {
        return taskRepository
                .findAll(specification, pageable)
                .getContent();
    }



}
