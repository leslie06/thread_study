package org.example.liftOff;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixThreadPool {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            exec.execute(new LiftOff(i));
        }
        exec.shutdown();
    }
}
