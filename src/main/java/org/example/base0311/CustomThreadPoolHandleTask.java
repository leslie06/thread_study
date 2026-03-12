package org.example.base0311;

import java.util.concurrent.*;

/**
 * 自定义线程池处理 100 个任务
 * 核心线程数：2个 队列：10个 最大线程数：5个 最多容纳15个线程，到第16个任务触发拒绝策略
 * 拒绝策略：CallerRunsPolicy 直接在提交任务的线程中执行任务，适合短时间执行的任务，避免丢失任务
 */
public class CustomThreadPoolHandleTask {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                2,//核心线程数
                5,//最大线程数
                60L,//线程空闲时间
                TimeUnit.SECONDS,//线程空闲时间单位
                new ArrayBlockingQueue<>(10),//任务队列
                Executors.defaultThreadFactory(),//线程工厂
                new ThreadPoolExecutor.CallerRunsPolicy()//拒绝策略
        );
        for (int i = 0; i < 100; i++) {
            final int taskId = i + 1;
            pool.submit(() -> {
                System.out.println(Thread.currentThread().getName() + "执行任务" + taskId);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        pool.shutdown();
        pool.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        System.out.println("所有任务完成");
    }
}
