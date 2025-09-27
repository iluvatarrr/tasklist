package com.example.tasklist.web.controller;

import com.example.tasklist.service.TaskService;
import com.example.tasklist.service.UserService;
import com.example.tasklist.web.dto.task.TaskDto;
import com.example.tasklist.web.dto.user.UserDto;
import com.example.tasklist.web.dto.validation.OnCreate;
import com.example.tasklist.web.dto.validation.OnUpdate;
import com.example.tasklist.web.mappers.TaskMapper;
import com.example.tasklist.web.mappers.UserMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Validated
@RequestMapping("/api/v1/users")
@Tag(name = "User Controller", description = "User api")
public class UserController {

    UserService userService;
    TaskService taskService;

    UserMapper userMapper;
    TaskMapper taskMapper;

    @PutMapping
    @MutationMapping
    @Operation(summary = "Update user")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#userDto.id)")
    public UserDto updateUser(@Validated(OnUpdate.class) @RequestBody @Argument(name = "dto") UserDto userDto) {
        var user = userMapper.toEntity(userDto);
        var updatedUser = userService.update(user);
        return userMapper.toDto(updatedUser);
    }

    @GetMapping("/{id}")
    @QueryMapping(name = "userById")
    @Operation(summary = "Get UserDto by id")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    public UserDto getById(@PathVariable @Argument Long id) {
        var user = userService.getById(id);
        return userMapper.toDto(user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete by id")
    @MutationMapping(name = "deleteUserById")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
    public void deleteById(@PathVariable @Argument Long id) {
        userService.delete(id);
    }

    @GetMapping("/{id}/tasks")
    @QueryMapping(name = "tasksByUserId")
    @Operation(summary = "Get list of TaskDto by User id")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#userId)")
    public List<TaskDto> getTasksByUserId(@PathVariable("id") @Argument(name = "id") Long userId) {
        var tasks = taskService.getAllByUserId(userId);
        return taskMapper.toDto(tasks);
    }

    @PostMapping("/{id}/tasks")
    @MutationMapping(name = "createTask")
    @Operation(summary = "Create task for user with id")
    @PreAuthorize("@customSecurityExpression.canAccessUser(#userId)")
    public TaskDto createTask(@Validated(OnCreate.class) @RequestBody @Argument(name = "dto") TaskDto taskDto,
                              @PathVariable("id") @Argument(name = "id") Long userId) {
        var task = taskMapper.toEntity(taskDto);
        var createdTask = taskService.create(task, userId);
        return taskMapper.toDto(createdTask);
    }

}
