package org.example.juc.countDownLatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        //等待3个线程
        CountDownLatch latch = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " 执行任务");
                latch.countDown();
            }).start();
        }
        latch.await();
        System.out.println("所有任务完成");
    }
}
