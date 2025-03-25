package ru.yakaska.tasktrackerapi.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yakaska.tasktrackerapi.mapper.TaskMapper;
import ru.yakaska.tasktrackerapi.model.Task;
import ru.yakaska.tasktrackerapi.payload.dto.TaskDto;
import ru.yakaska.tasktrackerapi.service.TaskService;
import ru.yakaska.tasktrackerapi.specification.TaskSpecification;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TaskDto>> index(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "createdAt") String sort,
            @RequestParam(value = "order", defaultValue = "desc") String order,
            @RequestParam(value = "author", required = false) Long authorId,
            @RequestParam(value = "assignee", required = false) Long assignee,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "priority", required = false) String priority
    ) {

        Sort.Direction sortOrder = "asc".equalsIgnoreCase(order) ? Sort.Direction.ASC : Sort.Direction.DESC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortOrder, sort));

        Specification<Task> specification = Specification
                .where(TaskSpecification.hasStatus(status))
                .and(TaskSpecification.hasPriority(priority))
                .and(TaskSpecification.hasAuthorId(authorId));

        List<TaskDto> tasks = taskService.find(specification, pageable)
                .stream()
                .map(taskMapper::taskToTaskDto)
                .toList();

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> showTask(@PathVariable Long id) {
        throw new UnsupportedOperationException();
    }

    @PostMapping("/")
    public ResponseEntity<?> createTask() {
        throw new UnsupportedOperationException();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id) {
        throw new UnsupportedOperationException();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        throw new UnsupportedOperationException();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateTaskStatus(@PathVariable Long id) {
        throw new UnsupportedOperationException();
    }

    @PatchMapping("/{id}/priority")
    public ResponseEntity<?> updateTaskPriority(@PathVariable Long id) {
        throw new UnsupportedOperationException();
    }

    @PatchMapping("/{id}/assign")
    public ResponseEntity<?> assignTask(@PathVariable Long id) {
        throw new UnsupportedOperationException();
    }

}
