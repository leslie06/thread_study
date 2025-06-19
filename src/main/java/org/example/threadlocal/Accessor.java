package org.example.threadlocal;

import java.util.Random;

public class Accessor implements Runnable {
    private final int id;
    public Accessor(int idn) {
        id = idn;
    }
    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            ThreadLocalVariableHolder.increment();
            System.out.println(this);
            Thread.yield();
        }
    }
    public String toString() {
        return "#" + id + ": " + ThreadLocalVariableHolder.get();
    }

}
