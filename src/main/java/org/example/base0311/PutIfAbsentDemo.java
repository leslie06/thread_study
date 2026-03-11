package org.example.base0311;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * putIfAbsent保证get和put是原子性
 */
public class PutIfAbsentDemo {
    private static final Map<String,Integer> map = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        Runnable task = ()->map.putIfAbsent("count",1);

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(map);
    }
}
