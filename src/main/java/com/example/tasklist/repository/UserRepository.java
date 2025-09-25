package com.example.tasklist.repository;

import com.example.tasklist.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query(value = """
            SELECT exists
                (SELECT 1 FROM users_tasks WHERE user_id = :user_id AND task_id = :task_id)
            """, nativeQuery = true)
    boolean isTaskOwner(@Param("user_id") Long user_id, @Param("task_id") Long taskId);
}
