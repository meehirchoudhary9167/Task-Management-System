package com.todo.todo.Backend.repository;

import com.todo.todo.Backend.model.Task;
import com.todo.todo.Backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
}
