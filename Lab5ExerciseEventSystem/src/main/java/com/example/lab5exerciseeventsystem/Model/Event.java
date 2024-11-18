package com.example.lab5exerciseeventsystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Event {
   @NotEmpty(message = "ID Cannot Be Empty")
   @Size(min = 2, max = 10, message = "ID size must be between 2 to 10")
    private String id;

   @NotEmpty(message = "Description Cannot Be Empty")
   @Size(min = 15, message = "Description must be longer than 15 letters")
    private String description;

   @NotNull(message = "Capacity Cannot Be Empty")
   @Min(value = 25, message = "Capacity has to be more than 25")
    private int capacity;

   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;
}
