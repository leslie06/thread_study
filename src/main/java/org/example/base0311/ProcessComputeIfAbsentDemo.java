package org.example.base0311;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * computeIfAbsent保证了线程安全，并且可以确保Processor的唯一性
 */
public class ProcessComputeIfAbsentDemo {
    private static final Map<String,Processor> map = new ConcurrentHashMap<>();

    public static Processor getOrCreate(String callId){
        return map.computeIfAbsent(callId, Processor::new);
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable task = ()->{
            Processor p = getOrCreate("call-1001");
            System.out.println(Thread.currentThread().getName() + "->" + p.getCallId());
        };

        Thread t1 = new Thread(task,"T1");
        Thread t2 = new Thread(task,"T2");
        Thread t3 = new Thread(task,"T3");

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

    }

}
