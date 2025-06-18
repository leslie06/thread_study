package org.example.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 添加sleep方法，线程可以依次执行任务
 */
public class SleepingTask extends LiftOff{
    public void run() {
        try{
            while(countDown-- > 0) {
                System.out.println(status());
                TimeUnit.MICROSECONDS.sleep(100);
            }
        }catch (InterruptedException e){
            System.out.println("Interrupted");
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i=0;i<5;i++) {
            exec.execute(new SleepingTask());
        }
        exec.shutdown();
    }

}
