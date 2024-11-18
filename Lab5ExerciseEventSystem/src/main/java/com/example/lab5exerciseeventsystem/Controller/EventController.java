package com.example.lab5exerciseeventsystem.Controller;

import com.example.lab5exerciseeventsystem.ApiResponse.ApiResponse;
import com.example.lab5exerciseeventsystem.Model.Event;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/event")
public class EventController {
    ArrayList<Event> events = new ArrayList<>();

    @GetMapping("display")
    public ResponseEntity displayEvents() {

        return ResponseEntity.status(200).body(events);
    }

    @PostMapping("/add")
    public ResponseEntity addEvent(@RequestBody @Valid Event event, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        events.add(event);
        return ResponseEntity.status(200).body(new ApiResponse("Event Added Successfully"));
    }

    @PutMapping("/update/{index}")
    public ResponseEntity updateEvent(@PathVariable int index, @RequestBody @Valid Event event, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        events.set(index, event);
        return ResponseEntity.status(200).body(new ApiResponse("Event Updated Successfully"));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteEvent(@PathVariable int index) {
        events.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("Event Deleted Successfully"));
    }

    @PutMapping("/change/capacity/{index}/{capacity}")
    public ResponseEntity changeCapacity(@PathVariable int index, @PathVariable int capacity) {
        events.get(index).setCapacity(capacity);
        return ResponseEntity.status(200).body(new ApiResponse("Capacity changed successfully"));
    }

    @GetMapping("/id/search/{id}")
    public ResponseEntity eventSearchByID(@PathVariable String id) {
        for (Event event : events)
            if (event.getId().equalsIgnoreCase(id))
                return ResponseEntity.status(200).body(new ApiResponse("Event Found"));


        return ResponseEntity.status(400).body(new ApiResponse("Event Not Found"));
    }

}
