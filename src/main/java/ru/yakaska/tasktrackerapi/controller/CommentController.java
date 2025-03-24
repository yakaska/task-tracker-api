package ru.yakaska.tasktrackerapi.controller;

import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @GetMapping("/task/{taskId}")
    public RequestEntity<?> showTaskComments(@PathVariable Long taskId) {
        throw new UnsupportedOperationException();
    }

    @PostMapping("/task/{taskId}")
    public RequestEntity<?> addComment(@PathVariable Long taskId) {
        throw new UnsupportedOperationException();
    }

}
