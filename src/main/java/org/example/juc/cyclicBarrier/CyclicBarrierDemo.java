package org.example.juc.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) throws Exception {
        CyclicBarrier barrier = new CyclicBarrier(3, () -> {
            System.out.println("所有线程到达栅栏！");
        });

        CyclicBarrier barrier2 = new CyclicBarrier(3, () -> {
            System.out.println("所有线程到达栅栏2！");
        });
        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " 正在等待栅栏！");
                try {
                    barrier.await();
                    barrier2.await();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }
}
