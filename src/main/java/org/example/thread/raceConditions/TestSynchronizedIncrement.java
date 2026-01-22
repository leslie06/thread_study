package org.example.thread.raceConditions;

public class TestSynchronizedIncrement {

    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }

}
