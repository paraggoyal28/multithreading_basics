/*
 * Copyright (c) 2024. Michael Pogrebinsky - Top Developer Academy
 * https://topdeveloperacademy.com
 * All rights reserved
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit; /**
 * Michael Pogrebinsky - Top Developer Academy
 * Thread Per Task Threading Model
 * https://www.udemy.com/java-multithreading-concurrency-performance-optimization
 */
public class ExecutorServiceCloseable implements AutoCloseable {
    private final ExecutorService executorService;

    public ExecutorServiceCloseable(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public void close() throws Exception {
        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
    }
}
