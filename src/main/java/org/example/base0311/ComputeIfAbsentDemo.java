package org.example.base0311;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 等价于下面代码，但保证了线程安全
 * if (map.get(key) == null) {
 *     map.put(key, new Value());
 * }
 * return map.get(key);
 */
public class ComputeIfAbsentDemo {
    private static final Map<String,StringBuilder> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        Runnable task = ()->{
            StringBuilder sb = map.computeIfAbsent("call-1", k -> new StringBuilder());
//            if(map.get("call-1") == null){
//                map.put("call-1",new StringBuilder());
//            }
//            StringBuilder sb = map.get("call-1");
            synchronized (sb) {
                sb.append(Thread.currentThread().getName()).append(" ");
            }
        };

        Thread t1 = new Thread(task,"t1");
        Thread t2 = new Thread(task,"t2");
        Thread t3 = new Thread(task,"t3");

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(map.get("call-1"));
    }



}
