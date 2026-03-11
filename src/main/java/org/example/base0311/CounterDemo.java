package org.example.base0311;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 100 个线程同时给计数器加 1
 */
public class CounterDemo {
    private static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[100];

        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(() -> {
                counter.incrementAndGet();
            });
            threads[i].start();
        }

        for (int i = 0; i < 100; i++) {
            threads[i].join();
        }

        System.out.println(counter.get()); // 稳定输出 100
    }
}
