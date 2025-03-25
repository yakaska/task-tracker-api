package ru.yakaska.tasktrackerapi.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateTaskRequest {

    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title must be at most 255 characters")
    private String title;

    private String description;

    @NotBlank(message = "Priority is required")
    @Pattern(regexp = "PENDING|IN_PROGRESS|COMPLETED", message = "Invalid status value")
    private String status;

    @NotBlank(message = "Status is required")
    @Pattern(regexp = "HIGH|MEDIUM|LOW", message = "Invalid priority value")
    private String priority;

    @NotBlank(message = "Author email is required")
    @Email
    private String author;

    @NotBlank(message = "Assignee email is required")
    @Email
    private String assignee;

}
