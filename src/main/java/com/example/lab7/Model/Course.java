package com.example.lab7.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Course {

    @NotEmpty(message = "Please enter a unique title")
    private String title;
    @Size(message = "Tutor's name must contain at least 2 characters.")
    private String instructor;
    @NotNull(message = "Please enter hours")
    @Min(value = 1, message = "Minimum hours is 1.")
    private int hours;
    @NotEmpty(message = "Please enter a major")
    private String underMajor;
}
