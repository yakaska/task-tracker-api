package ru.yakaska.tasktrackerapi.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.yakaska.tasktrackerapi.model.Task;

public class TaskSpecification {
    public static Specification<Task> hasStatus(String status) {
        return (root, query, builder) -> {
            if (status == null) return null;
            return builder.equal(root.get("status"), status);
        };
    }

    public static Specification<Task> hasPriority(String priority) {
        return (root, query, cb) -> {
            if (priority == null) return null;
            return cb.equal(root.get("priority"), priority);
        };
    }

    public static Specification<Task> hasAuthorId(Long authorId) {
        return (root, query, cb) -> {
            if (authorId == null) return null;
            return cb.equal(root.get("author").get("id"), authorId);
        };
    }

}
