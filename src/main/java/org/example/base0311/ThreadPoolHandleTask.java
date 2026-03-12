package org.example.base0311;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 固定线程池并发处理 100 个任务
 */
public class ThreadPoolHandleTask {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 100; i++) {
            final int taskId = i + 1;
            executorService.submit(()->{
                System.out.println(Thread.currentThread().getName() + "执行任务" + taskId);
            });
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        executorService.shutdown();

        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);

        System.out.println("等待所有任务完成");

    }
}
