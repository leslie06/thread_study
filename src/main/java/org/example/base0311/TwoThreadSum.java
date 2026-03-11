package org.example.base0311;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个线程分别对同一个变量进行累加，最终结果应该是 20000，但如果不加锁，可能会得到小于 20000 的结果
 * 因为 num++ 不是原子操作，可能会发生竞态条件
 * 处理方法有三种：
 * 1. 使用 synchronized 关键字
 * 2. 使用 Lock 接口
 * 3. 使用 AtomicInteger 类
 */
public class TwoThreadSum {
    private static int num = 0;
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                lock.lock();
                try {
                    num++;
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                lock.lock();
                try {
                    num++;
                } finally {
                    lock.unlock();
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(num); // 稳定输出 20000
    }

}
