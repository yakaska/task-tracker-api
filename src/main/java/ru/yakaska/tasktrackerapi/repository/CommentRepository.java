package ru.yakaska.tasktrackerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yakaska.tasktrackerapi.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
