package org.example.base0311;

/**
 * 开两个线程分别打印奇数偶数
 */
public class OddEvenPrint {
    private static final Object lock = new Object();
    private static int number = 1;
    private static final int MAX = 10;

    public static void main(String[] args) {
        Thread odd = new Thread(() -> {
            while (number <= MAX) {
                synchronized (lock) {
                    if (number % 2 == 1) {
                        System.out.println("奇数线程: " + number++);
                        lock.notify();
                    } else {
                        try { lock.wait(); } catch (InterruptedException e) {}
                    }
                }
            }
        }, "odd");

        Thread even = new Thread(() -> {
            while (number <= MAX) {
                synchronized (lock) {
                    if (number % 2 == 0) {
                        System.out.println("偶数线程: " + number++);
                        lock.notify();
                    } else {
                        try { lock.wait(); } catch (InterruptedException e) {}
                    }
                }
            }
        }, "even");

        odd.start();
        even.start();
    }
}
