package ru.yakaska.tasktrackerapi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yakaska.tasktrackerapi.model.Comment;
import ru.yakaska.tasktrackerapi.model.Task;
import ru.yakaska.tasktrackerapi.model.User;
import ru.yakaska.tasktrackerapi.payload.request.CreateCommentRequest;
import ru.yakaska.tasktrackerapi.repository.CommentRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {

    private final TaskService taskService;
    private final UserService userService;

    private final CommentRepository commentRepository;

    public List<Comment> show(Long taskId) {
        return commentRepository.findByTaskId(taskId);
    }

    public Comment create(Long taskId, CreateCommentRequest createCommentRequest) {
        Task task = taskService.findById(taskId);
        User author = userService.findByEmail(createCommentRequest.getAuthor());

        Comment comment = Comment.builder()
                .task(task)
                .user(author)
                .content(createCommentRequest.getContent())
                .build();

        return commentRepository.save(comment);
    }

}
