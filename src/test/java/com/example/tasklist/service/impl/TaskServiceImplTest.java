package com.example.tasklist.service.impl;

import com.example.tasklist.domain.exception.ResourceNotFoundException;
import com.example.tasklist.domain.task.Status;
import com.example.tasklist.domain.task.Task;
import com.example.tasklist.domain.task.TaskImage;
import com.example.tasklist.domain.user.User;
import com.example.tasklist.repository.TaskRepository;
import com.example.tasklist.service.ImageService;
import com.example.tasklist.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    void getById() {
        Long id = 1L;
        Task task = new Task();
        task.setId(id);

        Mockito.when(taskRepository.findById(id))
                .thenReturn(Optional.of(task));

        Task testTask = taskService.getById(id);
        Mockito.verify(taskRepository).findById(id);
        assertEquals(task, testTask);
    }

    @Test
    void getByNotExistingId() {
        Long id = 1L;
        Mockito.when(taskRepository.findById(id))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> taskService.getById(id));
        Mockito.verify(taskRepository).findById(id);
    }

    @Test
    void getAllByUserId() {
        Long userId = 1L;
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            tasks.add(new Task());
        }
        Mockito.when(taskRepository.findAllByUserId(userId))
                .thenReturn(tasks);
        List<Task> testTasks = taskService.getAllByUserId(userId);
        Mockito.verify(taskRepository).findAllByUserId(userId);
        Assertions.assertEquals(tasks, testTasks);
    }

    @Test
    void update() {
        Long id = 1L;
        Task task = new Task();
        task.setId(id);
        task.setTitle("Test Title");
        task.setDescription("Test Description");
        task.setStatus(Status.DONE);

        Mockito.when(taskRepository.save(task)).thenReturn(task);

        Task testTask = taskService.update(task);

        Mockito.verify(taskRepository).save(task);
        Assertions.assertEquals(task, testTask);
        Assertions.assertEquals(task.getTitle(), testTask.getTitle());
        Assertions.assertEquals(task.getDescription(), testTask.getDescription());
        Assertions.assertEquals(Status.DONE, testTask.getStatus());
    }

    @Test
    void updateWithEmptyStatus() {
        Long id = 1L;
        Task task = new Task();
        task.setId(id);
        task.setTitle("Test Title");
        task.setDescription("Test Description");
        Mockito.when(taskRepository.save(task)).thenReturn(task);
        Task testTask = taskService.update(task);

        Mockito.verify(taskRepository).save(task);
        Assertions.assertEquals(Status.TODO, testTask.getStatus());
    }

    @Test
    void updateWithExistingStatus() {
        Long id = 1L;
        Task task = new Task();
        task.setId(id);
        task.setTitle("Test Title");
        task.setDescription("Test Description");
        task.setStatus(Status.IN_PROGRESS);

        Mockito.when(taskRepository.save(task)).thenReturn(task);

        Task testTask = taskService.update(task);

        Mockito.verify(taskRepository).save(task);
        Assertions.assertEquals(Status.IN_PROGRESS, testTask.getStatus());
    }

    @Test
    void create() {
        Long userId = 1L;
        Long taskId = 1L;

        Task task = new Task();
        task.setTitle("Test Task");
        task.setDescription("Test Description");

        User user = new User();
        user.setId(userId);
        user.setTasks(new ArrayList<>());

        Mockito.when(userService.getById(userId)).thenReturn(user);
        Mockito.when(userService.update(user)).thenReturn(user);
        Mockito.when(taskRepository.save(Mockito.any(Task.class))).thenAnswer(invocation -> {
            Task savedTask = invocation.getArgument(0);
            savedTask.setId(taskId);
            return savedTask;
        });

        Task testTask = taskService.create(task, userId);
        Mockito.verify(taskRepository).save(task);
        Mockito.verify(userService).getById(userId);
        Mockito.verify(userService).update(user);
        Assertions.assertEquals(taskId, testTask.getId());
        Assertions.assertEquals(Status.TODO, testTask.getStatus());
        Assertions.assertTrue(user.getTasks().contains(testTask));
    }

    @Test
    void delete() {
        Long id = 1L;
        taskService.delete(id);
        Mockito.verify(taskRepository).deleteById(id);
    }

}