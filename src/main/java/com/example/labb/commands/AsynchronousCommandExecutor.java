package com.example.labb.commands;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component("asyncExecutor")
public class AsynchronousCommandExecutor implements CommandExecutor, DisposableBean {
    private final ExecutorService pool = Executors.newFixedThreadPool(4);
    private final Map<String, TaskStatus<?>> tasks = new ConcurrentHashMap<>();

    @Override
    public <T> TaskStatus<T> execute(Command<T> command) {
        String id = UUID.randomUUID().toString();
        TaskStatus<T> pending = new TaskStatus<>(id, TaskStatus.State.PENDING, null, null);
        tasks.put(id, pending);

        pool.submit(() -> {
            try {
                T result = command.execute();
                tasks.put(id, new TaskStatus<>(id, TaskStatus.State.DONE, result, null));
            } catch (Exception e) {
                tasks.put(id, new TaskStatus<>(id, TaskStatus.State.FAILED, null, e.getMessage()));
            }
        });
        return pending;
    }

    public TaskStatus<?> getStatus(String id) {
        return tasks.get(id);
    }

    @Override
    public void destroy() {
        pool.shutdown();
    }
}