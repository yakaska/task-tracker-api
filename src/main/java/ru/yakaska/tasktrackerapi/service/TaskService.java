package ru.yakaska.tasktrackerapi.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.yakaska.tasktrackerapi.mapper.TaskMapper;
import ru.yakaska.tasktrackerapi.model.Task;
import ru.yakaska.tasktrackerapi.model.TaskPriority;
import ru.yakaska.tasktrackerapi.model.TaskStatus;
import ru.yakaska.tasktrackerapi.model.User;
import ru.yakaska.tasktrackerapi.payload.request.CreateTaskRequest;
import ru.yakaska.tasktrackerapi.payload.request.UpdateTaskRequest;
import ru.yakaska.tasktrackerapi.repository.TaskRepository;
import ru.yakaska.tasktrackerapi.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    private final TaskMapper taskMapper;

    public List<Task> find(Specification<Task> specification, Pageable pageable) {
        return taskRepository
                .findAll(specification, pageable)
                .getContent();
    }

    public Task findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));
    }

    public Task create(CreateTaskRequest createTaskRequest) {
        return taskRepository.save(taskMapper.createTaskRequestToTask(createTaskRequest));
    }

    public Task update(Long id, UpdateTaskRequest updateTaskRequest) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));

        if (updateTaskRequest.getTitle() != null) {
            task.setTitle(updateTaskRequest.getTitle());
        }
        if (updateTaskRequest.getDescription() != null) {
            task.setDescription(updateTaskRequest.getDescription());
        }
        if (updateTaskRequest.getStatus() != null) {
            task.setStatus(TaskStatus.valueOf(updateTaskRequest.getStatus()));
        }
        if (updateTaskRequest.getPriority() != null) {
            task.setPriority(TaskPriority.valueOf(updateTaskRequest.getPriority()));
        }
        if (updateTaskRequest.getAuthor() != null) {
            User author = userRepository.findByEmail(updateTaskRequest.getAuthor())
                    .orElseThrow(() -> new EntityNotFoundException("Author not found"));
            task.setAuthor(author);
        }
        if (updateTaskRequest.getAssignee() != null) {
            User assignee = userRepository.findByEmail(updateTaskRequest.getAssignee())
                    .orElseThrow(() -> new EntityNotFoundException("Assignee not found"));
            task.setAssignee(assignee);
        }

        task.setUpdatedAt(LocalDateTime.now());

        return taskRepository.save(task);
    }

    public boolean delete(Long id) {
        taskRepository.existsById(id);
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
