package org.example.base0311;

/**
 * 安全的计数器
 */
public class SafeCounter {
    private int count  = 0;

    public synchronized void increment() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }

}
