package org.example.base0311;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 用 Callable返回一个计算结果
 * Thread 只认识 Runnable，不认识 Callable，所以需要 FutureTask 包一层
 *
 */
public class CallableDemo {
    public static void main(String[] args) throws Exception {
        Callable<Integer> task = () -> {
            System.out.println("子线程计算中...");
            Thread.sleep(1000);
            return 42;
        };

        FutureTask<Integer> futureTask = new FutureTask<>(task);
        Thread t = new Thread(futureTask);
        t.start();

        System.out.println("主线程干其他事...");
        Integer result = futureTask.get(); // 阻塞等待结果
        System.out.println("计算结果: " + result);
    }
}
