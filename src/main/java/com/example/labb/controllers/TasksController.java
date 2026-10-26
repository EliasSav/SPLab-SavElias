package com.example.labb.controllers;

import com.example.labb.commands.AsynchronousCommandExecutor;
import com.example.labb.commands.TaskStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    private final AsynchronousCommandExecutor asyncExecutor;

    public TasksController(AsynchronousCommandExecutor asyncExecutor) {
        this.asyncExecutor = asyncExecutor;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskStatus<?>> status(@PathVariable String id) {
        TaskStatus<?> status = asyncExecutor.getStatus(id);
        return status == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(status);
    }
}