package ru.yakaska.tasktrackerapi.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateTaskRequest {

    @Size(max = 255, message = "Title must be at most 255 characters")
    private String title;

    private String description;

    @Pattern(regexp = "PENDING|IN_PROGRESS|COMPLETED", message = "Invalid status value")
    private String status;

    @Pattern(regexp = "HIGH|MEDIUM|LOW", message = "Invalid priority value")
    private String priority;

    @Email
    private String author;

    @Email
    private String assignee;
}