package com.example.lab5exercisetrackersystem.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {
    @NotEmpty(message = "Enter Cannot Be Empty")
    @Size(min = 2, max = 10, message = "ID length must be longer than 2 and less than 10")
    private String id;

    @NotEmpty(message = "Title Cannot Be Empty")
    @Size(min = 8, message = "Title must be longer than 8 letters")
    private String title;

    @NotEmpty(message = "Description Cannot Be Empty")
    @Size(min = 15, message = "Description must be longer than 15 letter")
    private String description;

    @NotEmpty(message = "Status Cannot Be Empty")
    @Pattern(regexp = "^(not started|in progress|completed)$", message = "Status must be only one of the three: [not started - in progress - completed]")
    private String status;

    @NotEmpty(message = "Company Name Cannot Be Empty")
    @Size(min = 6, max = 20, message = "Company name must be longer than 6  and less than 20 letters")
    private String companyName;
}
