package com.example.tasklist.domain.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ExceptionBody {

    String message;
    Map<String, String> errors;

    public ExceptionBody(String message) {
        this.message = message;
    }
}
