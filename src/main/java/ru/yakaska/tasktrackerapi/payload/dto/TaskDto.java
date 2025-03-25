package ru.yakaska.tasktrackerapi.payload.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDto {

    private Long id;
    private String title;
    private String description;
    private String status;
    private String priority;
    private String author;
    private String assignee;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime updatedAt;

}
