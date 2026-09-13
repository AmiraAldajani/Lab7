package com.example.lab7.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    @NotEmpty(message = "ID must be filled")
    @Size(min= 8, max=8, message = "ID must contain of 8 characters")
    private String ID;
    @Size(min = 2, message = "name must contain of at least 2 characters")
    @NotEmpty(message = "Name must be filled")
    private String name;
    @NotEmpty(message = "Major must be filled")
    private String major;
    @NotEmpty(message = "GPA must be filled")
    @Pattern(regexp = "^0$", message = "GPA must be initiated with the number 0")
    private double gpa;
    @NotEmpty(message = "Please enter email.")
    @Email(message = "Email format was not correct!")
    private String email;
    @NotNull(message = "Age must be filled")
    @Min(value =16, message = "Age can't be less than 16")
    private int age;
}