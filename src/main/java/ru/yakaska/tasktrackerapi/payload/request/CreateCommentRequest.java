package ru.yakaska.tasktrackerapi.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateCommentRequest {

    @NotBlank
    @Email(message = "Author email is required")
    private String author;

    @NotBlank
    @Size(min = 10, message = "Minimum comment size is 10 symbols.")
    private String content;
}
