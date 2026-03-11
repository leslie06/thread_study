package org.example.base0311;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 用 AtomicBoolean 做“只执行一次”
 */
public class OnceDemo {

    private static AtomicBoolean executed = new AtomicBoolean(false);

    public static void doOnce(){
        if(executed.compareAndSet(false,true)){
            System.out.println(Thread.currentThread().getName() + " 执行了任务");
        }else {
            System.out.println(Thread.currentThread().getName() + " 任务已经执行过了");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(()-> doOnce(), "线程" + (i + 1));
            threads[i].start();
        }
        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }
    }

}
