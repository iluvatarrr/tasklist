package com.example.tasklist.web.dto.task;


import com.example.tasklist.domain.task.Status;
import com.example.tasklist.web.dto.validation.OnCreate;
import com.example.tasklist.web.dto.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskDto {

    @NotNull(message = "Id Can't be NULL", groups = OnUpdate.class)
    Long id;

    @NotNull(message = "Title Can't be NULL", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255, message = "Title Can't be more than 255 character", groups = {OnCreate.class, OnUpdate.class})
    String title;

    @Length(max = 255, message = "Description Can't be more than 255 character", groups = {OnCreate.class, OnUpdate.class})
    String description;

    Status status;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime expirationDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    List<String> images;
}
