package org.example.base0311;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 线程不安全的get和put
 */
public class ProcessorGetPutDemo {
    private static final Map<String,Processor> map = new ConcurrentHashMap<>();

    public static Processor getOrCreate(String callId){
        Processor processor = map.get(callId);
        if(processor == null){
            processor = new Processor(callId);
            map.put(callId,processor);
        }
        return processor;
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
