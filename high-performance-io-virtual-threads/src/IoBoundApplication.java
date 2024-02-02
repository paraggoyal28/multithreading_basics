/*
 * Copyright (c) 2023. Michael Pogrebinsky - Top Developer Academy
 * https://topdeveloperacademy.com
 * All rights reserved
 */

import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Michael Pogrebinsky - Top Developer Academy
 * High Performance IO with Virtual Threads
 * https://www.udemy.com/java-multithreading-concurrency-performance-optimization
 */
class CloseableExecutorService implements AutoCloseable {
    private ExecutorService service;

    public CloseableExecutorService(ExecutorService service) {
        super();
        this.service = service;
    }

    public void execute(Runnable runnable) {
        if (service == null) {
            return;
        }
        service.execute(runnable);
    }

    @Override
    public void close() {
        if (service != null) {
            service.shutdown();
        }
    }
}
public class IoBoundApplication {
    private static final int NUMBER_OF_TASKS = 10000;

    public static void main(String[] args) {
        System.out.printf("Running %d tasks\n", NUMBER_OF_TASKS);

        long start = System.currentTimeMillis();
        performTasks();
        System.out.printf("Tasks took %dms to complete\n", System.currentTimeMillis() - start);
    }

    private static void performTasks() {
        ExecutorService executorService = Executors.newFixedThreadPool(100);

        try (CloseableExecutorService closeableExecutorService = new CloseableExecutorService(executorService)) {

            for (int i = 0; i < NUMBER_OF_TASKS; i++) {
                executorService.submit(() ->  blockingIoOperation());
            }
        }
    }

    // Simulates a long blocking IO
    private static void blockingIoOperation() {
        System.out.println("Executing a blocking task from thread: " + Thread.currentThread());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}