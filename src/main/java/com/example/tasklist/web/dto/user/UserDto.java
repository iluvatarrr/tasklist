package com.example.tasklist.web.dto.user;


import com.example.tasklist.web.dto.validation.OnCreate;
import com.example.tasklist.web.dto.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;


@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "User DTO")
public class UserDto {

    @Schema(description = "User id", example = "1")
    @NotNull(message = "Id Can't be NULL", groups = OnUpdate.class)
    Long id;

    @Schema(description = "User name", example = "Jon Doe")
    @NotNull(message = "Name Can't be NULL",  groups = { OnCreate.class, OnUpdate.class } )
    @Length(max = 255, message = "Name Can't be more than 255 character",  groups = { OnCreate.class, OnUpdate.class })
    String name;

    @Schema(description = "User email", example = "mpetr4ova@gmail.com")
    @NotNull(message = "Name Can't be NULL",  groups = { OnCreate.class, OnUpdate.class } )
    @Length(max = 255, message = "Name Can't be more than 255 character",  groups = { OnCreate.class, OnUpdate.class })
    String username;

    @Schema(description = "User crypted password", example = "12345")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password Can't be NULL",  groups = { OnCreate.class, OnUpdate.class } )
    String password;

    @Schema(description = "User password confirmation", example = "12345")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "Password Configuration Can't be NULL",  groups = { OnCreate.class} )
    String passwordConfirmation;

}