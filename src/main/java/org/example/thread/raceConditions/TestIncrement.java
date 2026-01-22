package org.example.thread.raceConditions;

/**
 * 不安全的i++
 */
public class TestIncrement {
    private int count = 0;

    public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}
