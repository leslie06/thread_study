package org.example.juc.semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore sem = new Semaphore(2); // 允许2个线程同时访问
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                try {
                    sem.acquire();
                    System.out.println(Thread.currentThread().getName() + " 尝试获取许可");
                    Thread.sleep(2000); // 模拟任务执行
                    sem.release();
                    System.out.println(Thread.currentThread().getName() + " 释放许可");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }
}
