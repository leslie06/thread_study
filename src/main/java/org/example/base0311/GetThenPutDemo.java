package org.example.base0311;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class GetThenPutDemo {
    private static final Map<String,Integer> map = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        Runnable task = ()->{
            if(map.get("count") == null){
                map.put("count",1);
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(map);
    }

}
